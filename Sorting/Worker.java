import java.io.*;
import java.util.*;

public class Worker
{
  // can easily be run 'stand alone'...
  public static void main(String args[]) throws Exception
  {
    new Worker().go(args);
  }

  private void go(String args[]) throws Exception
  {
    List<Integer> numbers = new ArrayList<Integer>();
    Scanner scan = new Scanner(System.in);
    int i = scan.nextInt();
    while (i != -1)
    {
      numbers.add(i);
      i = scan.nextInt();
    }

    // Comparator<Integer> cmp = (Integer obj1, Integer obj2) ->
    // {
    //   return obj1.compareTo(obj2);
    // };
    // Collections.sort(numbers, cmp);
    Collections.sort(numbers);

    for (int n : numbers)
      System.out.println(n);
  }
}
