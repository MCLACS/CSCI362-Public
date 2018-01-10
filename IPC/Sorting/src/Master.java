import java.io.*;
import java.util.*;

public class Master
{
  private static final Random m_rand = new Random();
  private static final int NUM_PROC = 3;
  private static final int NUM_NUM = 3;
  private static final int RANGE = 100;

  public static void main(String args[]) throws Exception
  {
    new Master().go();
  }

  private void go() throws Exception
  {
    System.out.println();
    List<Process> list = new ArrayList<Process>();
    for (int i = 0; i < NUM_PROC; i++)
    {
      System.out.printf("Starting process %d ...%n",i);
      Process p = new ProcessBuilder("java", "Worker").start();
      List<Integer> numbers = new ArrayList<Integer>();
      for (int j = 0; j < NUM_NUM; j++)
      {
        int rand = m_rand.nextInt(RANGE);
        numbers.add(rand);
      }
      sendParameters(p, numbers);
      list.add(p);
    }

    List<Integer> result = new ArrayList<Integer>();
    for (Process p : list)
    {
      result = mergeResults(p, result);
    }
  }

  private void sendParameters(Process p, List<Integer> numbers) throws Exception
  {
    System.out.printf("Sending parameters to process ...%n");
    PrintWriter pw = new PrintWriter(new OutputStreamWriter(p.getOutputStream(), "UTF-8"));
    for (int i : numbers)
      pw.println(i);
    pw.println(-1);
    pw.flush();
  }

  private List<Integer> mergeResults(Process p, List<Integer> result) throws Exception
  {
    p.waitFor();
    BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF-8"));
    String line = br.readLine();
    List<Integer> sorted = new ArrayList<Integer>();
    List<Integer> ret = new ArrayList<Integer>();
    while (line != null)
    {
      sorted.add(Integer.parseInt(line));
      line = br.readLine();
    }

    System.out.println("Before Merge");
    System.out.println(sorted);
    System.out.println(result);
    System.out.println(ret);

    while (!sorted.isEmpty() || !result.isEmpty())
    {
      int i1 = sorted.isEmpty() ? 100000 : sorted.get(0);
      int i2 = result.isEmpty() ? 100000 : result.get(0);
      if (i1 <= i2)
      {
          sorted.remove(0);
          ret.add(i1);
      }
      else
      {
          result.remove(0);
          ret.add(i2);
      }
    }

    System.out.println("After Merge");
    System.out.println(sorted);
    System.out.println(result);
    System.out.println(ret);

    return ret;
  }
}
