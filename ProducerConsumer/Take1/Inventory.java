import java.util.*;
import java.util.concurrent.*;

public class Inventory
{
	private final Semaphore m_items = new Semaphore(0, true);

	public void produce(int i)
	{
		m_items.release(i);
		System.out.printf("%s produced, %s items avaiable...%n",
			i, m_items.availablePermits());
	}

	public void consume(int i)
	{
		try
		{
			m_items.acquire(i);
			System.out.printf("%s consumed, %s items avaiable...%n",
				i, m_items.availablePermits());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
