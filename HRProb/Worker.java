import java.io.*;

public class Worker
{
  // can easily be run 'stand alone'...
  public static void main(String args[]) throws Exception
  {
    new Worker().go(args);
  }

  private void go(String args[]) throws Exception
  {
    String filename = args[0];
	BufferedReader in = new BufferedReader(new FileReader(filename));
	String line = in.readLine();
	while (line != null)
	{
		String tokens[] = line.split(",");
		int pa = Integer.parseInt(tokens[1].trim());
		int hr = Integer.parseInt(tokens[2].trim());
		double hrpct = hr/(double)pa;
		if (pa > 300 && hrpct > 0.055)
			System.out.printf("Found %s with HR%% %.3f ...%n",tokens[0], hrpct);
		line = in.readLine();
	}
  }
}
