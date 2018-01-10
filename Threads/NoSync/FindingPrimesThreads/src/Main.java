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
		List<Integer> allPrimes = Collections.synchronizedList(new ArrayList<Integer>());

		List<Callable<Boolean>> tasks = new ArrayList<Callable<Boolean>>();
		for (int n = 0; n < 10; n++)
		{
		  int start = (n*100);
		  int end = start + 100;
		  System.out.printf("Creating thread for range %d-%d ...%n",start,end);

		  Callable<Boolean> task = new MyThread(start, end, allPrimes);
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
	
	public class MyThread implements Callable<Boolean>
	{
		private int m_start;
		private int m_end;
		private List<Integer> m_allPrimes;
		
		public MyThread(int start, int end, List<Integer> allPrimes)
		{
			m_start = start;
			m_end = end;
			m_allPrimes = allPrimes;
		}
		
		@Override
		public 	Boolean call()
		{
		      for (int i = m_start; i < m_end; i++)
		      {
		        if (i == 0 || i == 1)
		        {
		          continue;
		        }
		        else
		        {
		        	try
		        	{
		            	Thread.sleep(10);
		        	}
		        	catch (Exception e)
		        	{
		        		e.printStackTrace();
		        	}
		        	
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
						m_allPrimes.add(i);
		        }
		      }
			  return true;
		}	
	}
}
