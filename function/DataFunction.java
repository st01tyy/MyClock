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
	 * ��̨������غ����࣬��Ϊ��̬����
	 */
	
	@SuppressWarnings("unchecked")
	public static void startApp() //��������ʱ����ִ�иú���
	{
		try //��ȡ���ӱ����ļ�
		{
			ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream("./data.ruben")));
			Background.clockList = (List<Clock>) in.readObject();
			in.close();
		}
		catch(Exception e) //�����ļ��쳣����û�б����ļ����ļ����ʱ��
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
	public static void shutDown() //����ر�ʱ�������иú���
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
