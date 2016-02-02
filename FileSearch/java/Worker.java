import java.io.*;

public class Worker
{
  // can easily be run 'stand alone'...
  public static void main(String args[]) throws Exception
  {
    new Worker().go(args);
  }

  private void go(String args[]) throws Exception
  {
    String startsWith = args[0].toUpperCase();
    File f = new File("./files");

    FilenameFilter task = (File dir, String name) ->
    {
      return name.toUpperCase().startsWith(startsWith);
    };
    String[] files = f.list(task);

    for (String s : files)
    {
      BufferedReader in = new BufferedReader(new FileReader("./files/"+s));
      String line = in.readLine();
      while (line != null)
      {
        if (line.toUpperCase().contains("SNICKERS"))
        {
            System.out.printf("Found snickers in %s ...%n", s);
            break;
        }
        line = in.readLine();
      }
    }
  }
}
