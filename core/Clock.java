package core;

import java.io.Serializable;
import java.util.Calendar;
import java.util.TimeZone;

import exception.InterfaceLoadException;
import function.InterfaceFunction;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class Clock extends Service<Void> implements Serializable
{
	/*
	 * ������
	 * �̳���Javafx���߳���Task�����ڶ��߳�ͬ����������
	 */
	
	private static final long serialVersionUID = 1L;
	
	private String clockName; //��������
	private int alarmHour, alarmMinute, alarmDate; //����ʱ��
	private MusicPlayer musicPlayer;  //��������������
	private boolean isEnable; //�����Ƿ�����
	
	public Clock(String clockName, int alarmHour, int alarmMinute, String musicFilePath, boolean isEnable) //���캯��
	{
		this.clockName = clockName;
		this.alarmHour = alarmHour;
		this.alarmMinute = alarmMinute;
		this.alarmDate = Calendar.getInstance(TimeZone.getDefault()).get(Calendar.DAY_OF_YEAR);
		if(Calendar.getInstance(TimeZone.getDefault()).get(Calendar.HOUR_OF_DAY) > alarmHour || Calendar.getInstance(TimeZone.getDefault()).get(Calendar.HOUR_OF_DAY) == alarmHour && Calendar.getInstance(TimeZone.getDefault()).get(Calendar.MINUTE) > alarmMinute)
			this.alarmDate++;
		this.musicPlayer = new MusicPlayer(musicFilePath);
		this.isEnable = isEnable;
	}
	
	@Override
	protected Task<Void> createTask() 
	{
		return new Task<Void>() 
		{
			@Override
			protected Void call() throws Exception //�߳�����ʱִ�иú���������ĺ��Ĺ���
			{
				if(!isEnable) //��ֹ����û�б����ã������ϲ��ᷢ����
				{
					System.out.println("return");
					return null;
				}
				Calendar cal;
				while(true) //�ȴ�����ʱ��
				{
					if(!isEnable)
						return null; //��ǰ���������ӣ���ֹͣ�ȴ�
					cal = Calendar.getInstance(TimeZone.getDefault());
					System.out.println("Waiting");
					if(cal.get(Calendar.DAY_OF_YEAR) == alarmDate && cal.get(Calendar.HOUR_OF_DAY) == alarmHour && cal.get(Calendar.MINUTE) == alarmMinute) //�ж��Ƿ�ʱ
						break;
					Thread.sleep(500); //ÿ����ˢ��
				}
				musicPlayer.play();
				return null;
			}
			@Override
			protected void succeeded()
			{
				if(isEnable)
					alarm();
			}
		};
	}
	public void stopMusic()
	{
		this.musicPlayer.stop();
		this.alarmDate++;
		if(this.alarmDate > 365)
			this.alarmDate = 1;
	}
	public String getClockName() 
	{
		return clockName;
	}
	public int getAlarmHour() 
	{
		return alarmHour;
	}
	public void setAlarmHour(int alarmHour)
	{
		this.alarmHour = alarmHour;
	}
	public int getAlarmMinute() 
	{
		return alarmMinute;
	}
	public void setAlarmMinute(int alarmMinute) 
	{
		this.alarmMinute = alarmMinute;
	}
	public boolean isEnable() 
	{
		return isEnable;
	}
	public void setEnable(boolean isEnable)
	{
		this.isEnable = isEnable;
		if(!isEnable)
			this.musicPlayer.readyToClose();
	}
	private void alarm()
	{
		try 
		{
			InterfaceFunction.alarm(this);
		} 
		catch (InterfaceLoadException e) 
		{
			e.printStackTrace();
			InterfaceFunction.thorwException(e);
		}
	}
}
