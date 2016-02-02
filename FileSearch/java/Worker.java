import java.io.*;

public class Worker
{
  private String m_startsWith;
  // can easily be run 'stand alone'...
  public static void main(String args[]) throws Exception
  {
    new Worker().go(args);
  }

  private void go(String args[]) throws Exception
  {
    m_startsWith = args[0].toUpperCase();
    File f = new File("./files");

    /*
    FilenameFilter task = (File dir, String name) ->
    {
      return name.toUpperCase().startsWith(m_startsWith);
    };
    */
    //String[] files = f.list(task);
    //String[] files = f.list((File dir, String name) -> { return name.toUpperCase().startsWith(m_startsWith);});

    String[] files = f.list(new Filter());
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

  private class Filter implements FilenameFilter
  {
    @Override
    public boolean accept(File dir, String name)
    {
      return name.toUpperCase().startsWith(m_startsWith);
    }
  }

}
