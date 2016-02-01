import java.io.*;

public class Streams1
{
  public static void main(String args[]) throws IOException
  {
    if (args.length == 0)
      System.out.println("Bad Command");
    else
      new Streams1().go(args);
  }

  private void go(String args[]) throws IOException
  {
    if (args[0].toUpperCase().equals("ENCRYPT"))
    {
      Writer out = getWriter();
      for (int i = 1; i < args.length; i++)
      {
        out.write(args[i] + "\n");
      }
      out.close();
    }
    else if (args[0].toUpperCase().equals("DECRYPT"))
    {
      BufferedReader in = getReader();
      String line = in.readLine();
      while (line != null)
      {
        System.out.println(line);
        line = in.readLine();
      }
    }
    else
    {
      System.out.println("Bad Command");
    }
  }

  private Writer getWriter() throws IOException
  {
    return new FileWriter("test.txt");
    //return new Encrypt(new FileWriter("test.txt"));
  }

  private BufferedReader getReader() throws IOException
  {
    return new BufferedReader(new FileReader("test.txt"));
    //return new Decrypt(new FileReader("test.txt"));
  }

  public static class Decrypt extends BufferedReader
  {
    public Decrypt(Reader in)
    {
      super(in);
    }

    public String readLine() throws IOException
    {
      String line = super.readLine();
      if (line != null)
      {
        String d = "";
        for (char ch : line.toCharArray())
        {
          d += (char)(ch - 13);
        }
        return d;
      }
      else
      {
        return line;
      }
    }
  }

  public static class Encrypt extends BufferedWriter
  {
    public Encrypt(Writer in)
    {
      super(in);
    }

    public void write(String s) throws IOException
    {
      String e = "";
      for (char ch : s.toCharArray())
      {
        if (ch == '\n')
          e += '\n';
        else
          e += (char)(ch + 13);
      }
      super.write(e);
    }
  }
}
