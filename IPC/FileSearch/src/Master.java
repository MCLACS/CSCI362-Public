import java.io.*;
import java.util.*;

public class Master
{
  private static final char alpha[] =
    "abcdefghijklmnopqrstuvwxyz".toCharArray();

  public static void main(String args[]) throws Exception
  {
    new Master().go(args);
  }

  private void go(String args[]) throws Exception
  {
    List<Process> list = new ArrayList<Process>();
    for (int i = 0; i < 26; i++)
    {
      System.out.printf("Starting process for files starting with %s ...%n",
        alpha[i]+"");
      Process p = new ProcessBuilder("java", "Worker", alpha[i]+"",
        args[0]).start();
      list.add(p);
    }

    for (Process p : list)
    {
      search(p);
    }
  }

  private void search(Process p) throws Exception
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
