import java.util.*;
import java.util.concurrent.*;

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
    int rangeStart = scan.nextInt();
    int rangeEnd = scan.nextInt();
	int range = rangeEnd-rangeStart;
	if (range % 10 > 0)
		throw new Error("search range must be divisible by 10");
	int rangeChunk = range/10;

	List<Integer> allPrimes = Collections.synchronizedList(
		new ArrayList<Integer>());
	List<Callable<Boolean>> tasks = new ArrayList<Callable<Boolean>>();

	int threadStart = rangeStart;
	int threadEnd = threadStart + rangeChunk;

	for (int n = 0; n < 10; n++)
	{
	  final int start = threadStart;
	  final int end = threadEnd;
	  Callable<Boolean> task = () ->
	  {
		  for (int i = start; i < end; i++)
		  {
			if (i == 0 || i == 1)
			{
			  continue;
			}
			else
			{
				//Thread.sleep(50);
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
					allPrimes.add(i);
			}
		  }
		  return true;
	  };

	  tasks.add(task);
	  threadStart = threadStart + rangeChunk;
	  threadEnd = threadEnd + rangeChunk;
	}

	ExecutorService executor = Executors.newFixedThreadPool(10);
	List<Future<Boolean>> futures = executor.invokeAll(tasks);

	for (Future<Boolean> f : futures)
	{
		if (!f.get())
		  throw new Exception("Thread completion error!");
	}
	executor.shutdown();

	for (int i : allPrimes)
	{
		System.out.printf("%s is prime.%n", i);
	}
  }
}
