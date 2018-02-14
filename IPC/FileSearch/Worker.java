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
    final String startsWith = args[0].toUpperCase();
    File f = new File("./files");

    // FilenameFilter task = new FilenameFilter()
    // {
    //   @Override
    //   public boolean accept(File dir, String name)
    //   {
    //     return name.toUpperCase().startsWith(startsWith);
    //   }
    // };
    
    String[] files = f.list((File dir, String name) -> 
      {return name.toUpperCase().startsWith(startsWith); });

    files.stream().forEach(s -> {
      try {
        BufferedReader in = new BufferedReader(new FileReader("./files/"+s));
        in.lines().forEach(l - > {
          if (line.toUpperCase().contains(args[1].toUpperCase()))
          {
              System.out.printf("Found %s in %s ...%n", args[1], s);
              break;
          }
        });
      } 
      catch(Exception e) { throw new Error(e);}
    });
      

    // for (String s : files)
    // {
    //   BufferedReader in = new BufferedReader(new FileReader("./files/"+s));
    //   String line = in.readLine();
    //   while (line != null)
    //   {
    //     if (line.toUpperCase().contains(args[1].toUpperCase()))
    //     {
    //         System.out.printf("Found %s in %s ...%n", args[1], s);
    //         break;
    //     }
    //     line = in.readLine();
    //   }
    // }
  }
}
