import java.io.*;
import java.util.*;

public class Master
{
  private static final char alpha[] =
    "abcdefghijklmnopqrstuvwxyz".toCharArray();

  public static void main(String args[]) throws Exception
  {
    new Master().go();
  }

  private void go() throws Exception
  {
    Scanner in = new Scanner(System.in);
    System.out.println("Enter keyword to search for:");
    String keyword = in.nextLine();
    
    List<Process> list = new ArrayList<Process>();
    for (int i = 0; i < 26; i++)
    {
      System.out.printf("Starting process for files starting with %s ...%n",
        alpha[i]+"");
      Process p = new ProcessBuilder("java", "Worker", alpha[i]+"",
        keyword).start();
      list.add(p);
    }

    for (Process p : list)
    {
      search(p);
    }
  }

  private void search(Process p) throws Exception
  {
    System.out.printf("Waiting for worker...%n");
    p.waitFor();
    System.out.printf("Worker done ...%n");
    BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF-8"));
    String line = br.readLine();
    while (line != null)
    {
      System.out.println(line);
      line = br.readLine();
    }
  }
}
