import java.util.*;
import java.util.concurrent.*;
public class Main
{
	public static void main(String args[]) throws Exception
	{
		Inventory inventory = new Inventory();
		List<Callable<Boolean>> threads = new ArrayList<Callable<Boolean>>();
		threads.add(new Producer(inventory));
		threads.add(new Producer(inventory));
		threads.add(new Producer(inventory));
		threads.add(new Consumer(inventory));
		threads.add(new Consumer(inventory));
		threads.add(new Consumer(inventory));

		ExecutorService executor = Executors.newFixedThreadPool(6);
		executor.invokeAll(threads);
		executor.shutdown();
	}
}
