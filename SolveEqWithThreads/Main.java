import java.util.*;
import java.util.concurrent.*;

public class Main
{
	private class XY
	{
		double x;
		double y;
	}

	public static void main(String[] args) throws Exception
	{
		(new Main()).go();
	}

	private void go() throws Exception
	{
		List<XY> best = Collections.synchronizedList(
			new ArrayList<XY>());

		List<Callable<Boolean>> tasks = new ArrayList<Callable<Boolean>>();
		int range = 1000;
		int threads = 10;
		int chunk = range/threads;
		for (int n = 0; n < threads; n++)
		{
		  int start = (n*chunk) - (range/2);
		  int end = start + chunk;
		  double inc = 0.1;
		  System.out.printf("Creating thread for range %d to %d ...%n",start,end);

		  Callable<Boolean> task = new Callable()
		  {
			  @Override
			  public Boolean call()
			  {
			      for (double x = start; x < end; x=x+inc)
			      {
					  double y = Math.pow(x,5) + (3.5*Math.pow(x, 4))
					  	- (2.5*Math.pow(x, 3)) - (12.5*Math.pow(x, 2)) + (1.5*x) + 9;

					  if (Math.abs(y) <= 2)
					  {
			  			  XY ans = new XY();
						  ans.x=x;
						  ans.y=y;
						  best.add(ans);
					  }
			      }
			  	  return true;
			  }
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

		for (XY o : best)
		{
			System.out.printf("%.1f = %.1f%n", o.x, o.y);
		}
	}
}
