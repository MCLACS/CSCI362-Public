import java.util.concurrent.*;

public class Producer implements Callable<Boolean>
{
	private final Inventory m_inventory;

	public Producer(Inventory i)
	{
		m_inventory = i;
	}

	public Boolean call()
	{
		while (true)
		{
			try
			{
				ThreadLocalRandom rand = ThreadLocalRandom.current();
				int t = rand.nextInt(1000)+1000;
				Thread.sleep(t);
				int c = rand.nextInt(3)+1;
				m_inventory.produce(c);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}

}
