public class Worker
{
  // can easily be run 'stand alone'...
  public static void main(String args[]) throws Exception
  {
    new Worker().go(args);
  }

  private void go(String args[]) throws Exception
  {
    int start = Integer.parseInt(args[0]);
    int end = Integer.parseInt(args[1]);
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
