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
    List<Process> list = new ArrayList<Process>();
    for (int i = 0; i < 10; i++)
    {
      int start = (i*100);
      int end = start + 100 - 1;
      System.out.printf("Starting process for range %d-%d ...%n",start,end);
      Process p = new ProcessBuilder("java", "Worker", start+"", end+"").start();
      list.add(p);
    }

    for (Process p : list)
    {
      getPrimes(p);
    }
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
