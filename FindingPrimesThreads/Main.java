import java.util.*;
import java.util.concurrent.*;

public class Main
{
	public static void main(String[] args) throws Exception
	{
		(new Main()).go();
	}

	private void go() throws Exception
	{
		List<Integer> allPrimes = Collections.synchronizedList(
			new ArrayList<Integer>());

		List<Callable<Boolean>> tasks = new ArrayList<Callable<Boolean>>();
		for (int n = 0; n < 10; n++)
		{
		  int start = (n*100);
		  int end = start + 100;
		  System.out.printf("Creating thread for range %d-%d ...%n",start,end);

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
		            Thread.sleep(50);
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
		}

		ExecutorService executor = Executors.newFixedThreadPool(5);
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
