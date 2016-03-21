public class Waiter
{
	public synchronized boolean grabForks(Fork f1, Fork f2)
	{
		if (f1.grab())
		{
			if (f2.grab())
			{
				return true;
			}
		}
		f1.release();
		f2.release();
		return false;
	}

	public synchronized void returnForks(Fork f1, Fork f2)
	{
		f1.release();
		f2.release();
	}

}
