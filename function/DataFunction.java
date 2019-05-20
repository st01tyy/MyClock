package function;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import core.Clock;
import exception.InterfaceLoadException;
import main.Background;

public class DataFunction 
{
	/*
	 * 后台数据相关函数类，均为静态函数
	 */
	
	@SuppressWarnings("unchecked")
	public static void startApp() //程序启动时首先执行该函数
	{
		try //读取闹钟本地文件
		{
			ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream("./data.ruben")));
			Background.clockList = (List<Clock>) in.readObject();
			in.close();
		}
		catch(Exception e) //捕获文件异常（当没有本地文件或文件损毁时）
		{
			Background.clockList = new ArrayList<Clock>();
			e.printStackTrace();
		}
		try 
		{
			InterfaceFunction.setMainPane();
		} 
		catch (InterfaceLoadException e) 
		{
			e.printStackTrace();
			InterfaceFunction.thorwException(e);
		}
		Background.mainStage.setTitle("MyClock");
		Background.mainStage.show();
	}
	public static void shutDown() //程序关闭时结束运行该函数
	{
		if(Background.clockList != null)
		{
			for(int a = 0; a < Background.clockList.size(); a++)
			{
				Background.clockList.get(a).setEnable(false);
			}
			try
			{
				ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("./data.ruben")));
				out.writeObject(Background.clockList);
				out.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
}
