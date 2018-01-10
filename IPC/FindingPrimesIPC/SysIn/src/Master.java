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
      int start = (i*100);
      int end = start + 100;
      sendParameters(p, start, end);
      list.add(p);
    }

    for (Process p : list)
    {
      getPrimes(p);
    }
  }

  private void sendParameters(Process p, int start, int end) throws Exception
  {
    System.out.printf("Sending parameters for range %d-%d to process ...%n",start,end);
    PrintWriter pw = new PrintWriter(new OutputStreamWriter(p.getOutputStream(), "UTF-8"));
    pw.println(start);
    pw.println(end);
    pw.flush();
  }

  private void getPrimes(Process p) throws Exception
  {
    p.waitFor();
    BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF-8"));
    String line = br.readLine();
    while (line != null)
    {
      System.out.println(line);
      line = br.readLine();
    }
  }
}
