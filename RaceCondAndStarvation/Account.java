public class Account
{
	private int m_balance;

	public Account(int bal)
	{ m_balance = bal; }

 	// synchronize this to get starvation of main thread
	public int getBalance()
	{ return m_balance; }

	public synchronized void withdraw(int amount) throws Exception
	{
		if (amount <= m_balance)
		{
			Thread.sleep(100);
			m_balance -= amount;
		}
	}

	public synchronized void deposit(int amount)
	{ m_balance += amount;}
}
