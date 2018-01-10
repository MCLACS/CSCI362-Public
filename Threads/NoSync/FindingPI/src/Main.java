import java.util.*;
import java.util.concurrent.*;

public class Main
{
	private final static int P = 8000000;
	private final static int N = 4;
	private static int m_id = 0;
	
	public static void main(String[] args) throws Exception
	{
		(new Main()).go();
	}

	private void go() throws Exception
	{
		List<Callable<Double>> tasks = new ArrayList<Callable<Double>>();
		for (int i = 0; i < N; i++)
		{
			Callable<Double> task = new MyThread(i*(P/N), (i*(P/N))+(P/N));
			tasks.add(task);
		}
		
		ExecutorService executor = Executors.newFixedThreadPool(N);
		List<Future<Double>> futures = executor.invokeAll(tasks);

		double pi = 0;
		for (Future<Double> f : futures)
		{
			pi = pi + f.get();
		}
		
		executor.shutdown();

		System.out.printf("PI = %s%n", pi);
	}
	
	public class MyThread implements Callable<Double>
	{
		private int m_code;
		private int m_start;
		private int m_end;
		private List<Integer> m_allPrimes;
		
		public MyThread(int start, int end)
		{
			m_code = m_id++;
			m_start = start;
			m_end = end;
		}
		
		@Override
		public Double call()
		{
			System.out.printf("Thread %s: working on range %s-%s%n", m_code, m_start, m_end);
			int toggle = 0; 
			double total = 0.0;
			for (int i = m_start; i < m_end; i++)
			{
				if (toggle%2 == 0)
					total = total + 4/f(i);
				else
					total = total - 4/f(i);
				toggle++;
			}
			
			System.out.printf("Thread %s: chunk = %s%n", m_code, total);
			
			return total;
		}	
		
		private double f(int n)
		{
			return (n*2)+1;
		}

	}
}
