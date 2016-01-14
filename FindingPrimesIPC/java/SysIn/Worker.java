import java.util.*;

public class Worker
{
  // can easily be run 'stand alone'...  
  public static void main(String args[]) throws Exception
  {
    new Worker().go();
  }

  private void go() throws Exception
  {
    Scanner scan = new Scanner(System.in);
    int start = scan.nextInt();
    int end = scan.nextInt();

    for (int i = start; i < end; i++)
    {
      if (i == 0 || i == 1)
      {
        continue;
      }
      else
      {
          Thread.sleep(50); // slow things down a bit...
          boolean prime = true;
          for (int j = 2; j < i; j++)
          {
              if (i%j == 0)
              {
                  prime = false;
                  break;
              }
          }
          if (prime)
          {
            System.out.printf("Found prime %d ...%n", i);
          }
      }
    }
  }
}
