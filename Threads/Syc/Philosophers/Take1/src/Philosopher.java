import java.util.concurrent.*;

public class Philosopher implements Callable<Boolean>
{
	private final Fork m_left;
	private final Fork m_right;
	private final String m_name;

	public Philosopher(String name, Fork left, Fork right)
	{
		m_left = left;
		m_right = right;
		m_name = name;
	}

	public Boolean call()
	{
		int thinkTime = 500;
		int eatTime = 500;
		try
		{
			while (true)
			{
				Thread.sleep(thinkTime);
				while (!m_left.grab())
				{
					System.out.printf("%s is thinking...%n", m_name);
					Thread.sleep(thinkTime);
				}

				System.out.printf("%s has the left fork...%n", m_name);

				while (!m_right.grab())
				{
					System.out.printf("%s is thinking...%n", m_name);
					Thread.sleep(thinkTime);
				}

				System.out.printf("%s has the right fork...%n", m_name);

				System.out.printf("%s is eating...%n", m_name);
				Thread.sleep(eatTime);

				System.out.printf("%s is putting down both forks...%n", m_name);
				m_left.release();
				m_right.release();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return true;
	}
}
