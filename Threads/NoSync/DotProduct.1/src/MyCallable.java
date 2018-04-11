import java.util.*;
import java.util.concurrent.*;

public class MyCallable implements Callable<Boolean>
{
    private int m_products[];
    private int m_item;
    private List<Integer> m_A;
    private List<Integer> m_B;
    
    public MyCallable(int products[], int item, List<Integer> A, List<Integer> B)
    {
        m_products = products;
        m_item = item;
        m_A = A;
        m_B = B;
    }
    
    @Override    
    public Boolean call()
    {
        m_products[m_item] = m_A.get(m_item) * m_B.get(m_item);
	    return true;
    }
}