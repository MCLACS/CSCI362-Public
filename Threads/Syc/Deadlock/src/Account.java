public class Account
{
	private int m_balance;

	public Account(int bal)
	{ m_balance = bal; }

	public int getBalance()
	{ return m_balance; }

	synchronized public void withdraw(int amount) throws Exception
	{
		if (amount <= m_balance)
		{
			Thread.sleep(100);
			m_balance -= amount;
		}
	}

	synchronized public void deposit(int amount)
	{ m_balance += amount;}

	synchronized public void transfer(Account a, int amount) throws Exception
	{
		withdraw(amount);
		a.deposit(amount);
	}
}
