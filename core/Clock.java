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
	 * 闹钟类
	 * 继承自Javafx多线程类Task，用于多线程同步播放铃声
	 */
	
	private static final long serialVersionUID = 1L;
	
	private String clockName; //闹铃名称
	private int alarmHour, alarmMinute, alarmDate; //响铃时间
	private MusicPlayer musicPlayer;  //闹钟铃声播放器
	private boolean isEnable; //闹铃是否启用
	
	public Clock(String clockName, int alarmHour, int alarmMinute, String musicFilePath, boolean isEnable) //构造函数
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
			protected Void call() throws Exception //线程启动时执行该函数，闹铃的核心功能
			{
				if(!isEnable) //防止闹铃没有被启用（理论上不会发生）
				{
					System.out.println("return");
					return null;
				}
				Calendar cal;
				while(true) //等待响铃时间
				{
					if(!isEnable)
						return null; //提前禁用了闹钟，则停止等待
					cal = Calendar.getInstance(TimeZone.getDefault());
					System.out.println("Waiting");
					if(cal.get(Calendar.DAY_OF_YEAR) == alarmDate && cal.get(Calendar.HOUR_OF_DAY) == alarmHour && cal.get(Calendar.MINUTE) == alarmMinute) //判断是否到时
						break;
					Thread.sleep(500); //每半秒刷新
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
