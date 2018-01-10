import java.io.*;
import java.util.*;

public class Master
{
  public static void main(String args[]) throws Exception
  {
    new Master().go();
  }

  private void go() throws Exception
  {
    System.out.println();
    List<Process> list = new ArrayList<Process>();
    for (int i = 0; i < 10; i++)
    {
      System.out.printf("Starting process %d ...%n",i);
      Process p = new ProcessBuilder("java", "Worker").start();
      int start = (i*10000);
      int end = start + 10000;
      sendParameters(p, start, end);
      list.add(p);
    }

	int pNum = 0;
	int totalPrimes = 0;
    for (Process p : list)
    {
		int pCount = getPrimes(p);
		System.out.printf("Received %s primes from process %s...%n",
			pCount, pNum++);
      	totalPrimes += pCount;
    }
	System.out.printf("Master: Found %s primes.%n", totalPrimes);
  }

  private void sendParameters(Process p, int start, int end) throws Exception
  {
    PrintWriter pw = new PrintWriter(new OutputStreamWriter(p.getOutputStream(),
		"UTF-8"));
    pw.println(start);
    pw.println(end);
    pw.flush();
  }

  private int getPrimes(Process p) throws Exception
  {
    p.waitFor();
    BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream(),
		"UTF-8"));
	int primeCount = 0;
    String line = br.readLine();
    while (line != null)
    {
	  primeCount++;
      line = br.readLine();
    }
	return primeCount;
  }
}
