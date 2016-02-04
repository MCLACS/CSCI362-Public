import java.io.*;

public class Lambda
{
  // can easily be run 'stand alone'...
  public static void main(String args[]) throws Exception
  {
    new Lambda().go();
  }

  private void go() throws Exception
  {
    File f = new File(".");

    FilenameFilter filter =
      (File dir, String name) ->
      {
        return name.toUpperCase().endsWith(".CLASS");
      };

    String[] files = f.list(filter);
    for (String s : files)
    {
      System.out.println(s);
    }
  }
}
