public class Waiter
{
	public synchronized boolean grabForks(Fork f1, Fork f2)
	{
		boolean ret = false;
		if (f1.grab())
		{
			if (f2.grab())
			{
				ret = true;
			}
			else
			{
				f1.release();
			}
		}
		return ret;
	}

	public synchronized void returnForks(Fork f1, Fork f2)
	{
		f1.release();
		f2.release();
	}

}
