package test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import core.Clock;

public class TestMain 
{
	/*
	 * ≤‚ ‘”√÷˜¿‡
	 */

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException 
	{
		List<Clock> list = new ArrayList<Clock>();
		for(int a = 0; a < 5; a++)
		{
			list.add(new Clock("test", 16, 50 + a, "D:\\Files\\test.mp3", false));
		}
		ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("./test.ruben")));
		out.writeObject(list);
		out.close();
		ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream("./test.ruben")));
		list = (ArrayList<Clock>)in.readObject();
		for(int a = 0; a < 10; a++)
		{
			System.out.println(list.get(a).getClockName());
		}
		in.close();
	}
}
