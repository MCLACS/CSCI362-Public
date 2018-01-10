import java.util.concurrent.*;
public class Philosopher implements Callable<Boolean>
{
	private final static int THINK_TIME = 1000;
	private final static int EAT_TIME = 1000;
	private final Fork m_left;
	private final Fork m_right;
	private final Waiter m_waiter;
	private final String m_name;

	public Philosopher(String name, Waiter waiter, Fork left, Fork right)
	{
		m_left = left;
		m_right = right;
		m_name = name;
		m_waiter = waiter;
	}

	public Boolean call()
	{
		try
		{
			int thinkTime = 500;
			int eatTime = 500;
			while (true)
			{
				Thread.sleep(thinkTime);
				while (!m_waiter.grabForks(m_left, m_right))
				{
					System.out.printf("%s is thinking...%n", m_name);
					Thread.sleep(thinkTime);
				}

				System.out.printf("%s has both forks...%n", m_name);

				System.out.printf("%s is eating...%n", m_name);
				Thread.sleep(eatTime);

				System.out.printf("%s is putting down both forks...%n", m_name);
				m_waiter.returnForks(m_left, m_right);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return true;
	}
}
