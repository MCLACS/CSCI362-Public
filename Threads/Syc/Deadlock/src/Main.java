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
		Account account1 = new Account(1000);
		Account account2 = new Account(1000);
		List<Runnable> people = new ArrayList<Runnable>();
		for (int n = 0; n < 10; n++)
		{
			Runnable person = () ->
			{
				try
				{
					while (true)
					{
						ThreadLocalRandom rand = ThreadLocalRandom.current();
						Thread.sleep(100);
						System.out.println("running...");
				  		int amount = rand.nextInt(10)+1;
				  		int flip = rand.nextInt(100);
						if (flip < 50)
						{
							//synchronized (rand)
						    //{
								//account1.withdraw(amount);
								//account2.deposit(amount);
							//}
				  			account1.transfer(account2, amount);
						}
						else
						{
							//synchronized (rand)
							//{
								//account2.withdraw(amount);
								//account1.deposit(amount);
							//}
							account2.transfer(account1, amount);
						}
					}
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			};
			people.add(person);
		}

		ExecutorService executor = Executors.newFixedThreadPool(10);
		for (Runnable p : people)
			executor.submit(p);

		while (true)
		{
			Thread.sleep(1000);
			System.out.println("Account1: " + account1.getBalance() +
							   " Account2: " + account2.getBalance());
		}
	}
}
