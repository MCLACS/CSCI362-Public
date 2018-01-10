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
		final Account account = new Account(1000);
		List<Runnable> people = new ArrayList<Runnable>();
		for (int n = 0; n < 100; n++)
		{
			Runnable person = new Runnable()
			{
				@Override 
				public void run()
				{
					try
					{
						while (true)
						{
							ThreadLocalRandom rand = ThreadLocalRandom.current();
							Thread.sleep(100);
					  		int amount = rand.nextInt(10)+1;
					  		int flip = rand.nextInt(100);
							if (flip < 20)
					  			account.deposit(amount);
							else
								account.withdraw(amount);
						}
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
				}
			};
			people.add(person);
		}

		ExecutorService executor = Executors.newFixedThreadPool(100);
		for (Runnable p : people)
			executor.submit(p);

		while (true)
		{
			Thread.sleep(1000);
			System.out.println(account.getBalance());
		}
	}
}
