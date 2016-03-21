public class Fork
{
	private boolean m_taken;

	public Fork()
	{
		m_taken = false;
	}

	public synchronized boolean grab()
	{
		if (m_taken)
			return false;
		else
			m_taken = true;
		return m_taken;
	}

	public synchronized void release()
	{
		m_taken = false;
	}

	@Override
	public String toString()
	{ return String.format("Fork: %s", m_taken);}
}
