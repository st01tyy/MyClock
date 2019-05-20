package core;

import java.io.File;
import java.io.Serializable;
import java.net.MalformedURLException;

import exception.MusicNotFoundException;
import javafx.scene.media.AudioClip;

public class MusicPlayer implements Serializable
{
	/*
	 * 音乐播放器
	 */
	
	private static final long serialVersionUID = 1L;
	
	private String absolutePath; //音乐文件的绝对路径，由FileChooser获得
	private AudioClip audioClip; //Javafx提供的音频播放器
	
	public MusicPlayer(String absolutePath) //构造函数
	{
		this.absolutePath = absolutePath;
	}
	
	public void play() throws MusicNotFoundException //播放音乐
	{
		File musicFile = new File(this.absolutePath);
		if(!musicFile.exists()) //防止音乐不存在
			throw new MusicNotFoundException();
		try 
		{
			audioClip = new AudioClip(musicFile.toURI().toURL().toString());
			audioClip.play();
		} 
		catch (MalformedURLException e) //捕获URL格式异常（理论上不会发生）
		{
			e.printStackTrace();
			throw new MusicNotFoundException();
		}
	}
	public void readyToClose() //由于AudioClip无法序列化，因此在保存前必须执行该函数
	{
		this.audioClip = null; 
	}
	public void stop()  //停止播放
	{
		if(this.audioClip != null && this.audioClip.isPlaying())  //防止播放器没有被构造或没有播放（理论上不会发生）
			this.audioClip.stop();
	}
	public boolean isPlaying() //是否在播放
	{
		if(this.audioClip != null && this.audioClip.isPlaying())
			return true;
		else
			return false;
	}
}
