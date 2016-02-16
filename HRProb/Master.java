import java.io.*;
import java.util.*;

public class Master
{
  public static void main(String args[]) throws Exception
  {
    new Master().go(args);
  }

  private void go(String args[]) throws Exception
  {
    List<Process> list = new ArrayList<Process>();
    for (int i = 1; i <= 4; i++)
    {
		String filename = "stats" + i + ".txt";
      	System.out.printf("Starting process for %s ...%n", filename);
      	Process p = new ProcessBuilder("java", "Worker", filename).start();
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
