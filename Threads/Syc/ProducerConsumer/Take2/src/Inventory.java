import java.util.*;
import java.util.concurrent.*;

public class Inventory
{
	private final Semaphore m_items = new Semaphore(0, true);
	private final Semaphore m_mutex = new Semaphore(1, true);
	private class Widget {};
	private List<Widget> m_widgets = new ArrayList<Widget>();

	public void produce(int i)
	{
		try
		{
			m_items.release(i);

			// ******************************
			// mutually exclusive section
			m_mutex.acquire();
			for (int c = 0; c < i; c++)
				m_widgets.add(new Widget());
				System.out.printf("%s produced, %s/%s items avaiable...%n",
					i, m_items.availablePermits(), m_widgets.size());
			m_mutex.release();
			// ******************************
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void consume(int i)
	{
		try
		{
			m_items.acquire(i);

			// ******************************
			// mutually exclusive section
			m_mutex.acquire();
			for (int c = 0; c < i; c++)
				m_widgets.remove(0);
			System.out.printf("%s consumed, %s/%s items avaiable...%n",
				i, m_items.availablePermits(), m_widgets.size());
			m_mutex.release();
			// ******************************
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
