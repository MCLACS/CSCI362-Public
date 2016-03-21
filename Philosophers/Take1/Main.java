import java.util.*;
import java.util.concurrent.*;
public class Main
{
	public static void main(String args[]) throws Exception
	{
		/*
		 			d0
				f4		f0
			d4				d1

		f3						f1

			d3				d2
					f2
		*/

		Fork forks[] =
			{new Fork(),new Fork(), new Fork(),new Fork(),new Fork()};
		List<Philosopher> dudes = new ArrayList<Philosopher>();
		dudes.add(new Philosopher("dude0", forks[0], forks[4]));
		dudes.add(new Philosopher("dude1", forks[1], forks[0]));
		dudes.add(new Philosopher("dude2", forks[2], forks[1]));
		dudes.add(new Philosopher("dude3", forks[3], forks[2]));
		dudes.add(new Philosopher("dude4", forks[4], forks[3]));

		ExecutorService executor = Executors.newFixedThreadPool(5);
		executor.invokeAll(dudes);
		executor.shutdown();
	}
}
