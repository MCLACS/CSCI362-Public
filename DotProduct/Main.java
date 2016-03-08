import java.util.*;
import java.io.*;
import java.util.concurrent.*;

public class Main
{
  public static void main(String args[]) throws Exception
  {
    new Main().go();
  }

  private void go() throws Exception
  {
    Scanner in = new Scanner(System.in);
    System.out.print("Enter the filename for vector A: ");
    List<Integer> A = readVector(in.nextLine());
    System.out.print("Enter the filename for vector B: ");
	List<Integer> B = readVector(in.nextLine());

    if (A.size() != B.size())
    {
      System.out.println("The size of A must be equal to the size of B!");
      System.exit(-1);
    }

    System.out.printf("A = %s%n", A);
    System.out.printf("B = %s%n", B);

    int product = multiply(A, B);
    System.out.printf("Product = %s%n", product);
  }

  private int multiply(List<Integer> A, List<Integer> B) throws Exception
  {
	  int products[] = new int[A.size()];
	  List<Callable<Boolean>> tasks = new ArrayList<Callable<Boolean>>();
	  for (int i = 0; i < A.size(); i++)
	  {
		  final int item = i;
		  Callable<Boolean> task = () ->
		  {
			  products[item] = A.get(item) * B.get(item);
			  return true;
		  };
		  tasks.add(task);
	  }

	  ExecutorService executor = Executors.newFixedThreadPool(50);
	  List<Future<Boolean>> futures = executor.invokeAll(tasks);

	  for (Future<Boolean> f : futures)
	  {
		  if (!f.get())
		  	throw new Exception("Thread completion error!");
      }

	  executor.shutdown();

	  int result = 0;
	  for (int i : products)
	  	result += i;
	  return result;
  }

  private List<Integer> readVector(String filename) throws Exception
  {
	  List<Integer> ret = new ArrayList<Integer>();
	  Scanner in = new Scanner(new FileReader(filename));
      if (in.hasNextLine())
      {
        String line = in.nextLine();
        for (String c : line.split(","))
        {
          ret.add(Integer.parseInt(c.trim()));
        }
      }
	  return ret;
  }
}
