package core;

import java.io.File;
import java.io.Serializable;
import java.net.MalformedURLException;

import exception.MusicNotFoundException;
import javafx.scene.media.AudioClip;

public class MusicPlayer implements Serializable
{
	/*
	 * ���ֲ�����
	 */
	
	private static final long serialVersionUID = 1L;
	
	private String absolutePath; //�����ļ��ľ���·������FileChooser���
	private AudioClip audioClip; //Javafx�ṩ����Ƶ������
	
	public MusicPlayer(String absolutePath) //���캯��
	{
		this.absolutePath = absolutePath;
	}
	
	public void play() throws MusicNotFoundException //��������
	{
		File musicFile = new File(this.absolutePath);
		if(!musicFile.exists()) //��ֹ���ֲ�����
			throw new MusicNotFoundException();
		try 
		{
			audioClip = new AudioClip(musicFile.toURI().toURL().toString());
			audioClip.play();
		} 
		catch (MalformedURLException e) //����URL��ʽ�쳣�������ϲ��ᷢ����
		{
			e.printStackTrace();
			throw new MusicNotFoundException();
		}
	}
	public void readyToClose() //����AudioClip�޷����л�������ڱ���ǰ����ִ�иú���
	{
		this.audioClip = null; 
	}
	public void stop()  //ֹͣ����
	{
		if(this.audioClip != null && this.audioClip.isPlaying())  //��ֹ������û�б������û�в��ţ������ϲ��ᷢ����
			this.audioClip.stop();
	}
	public boolean isPlaying() //�Ƿ��ڲ���
	{
		if(this.audioClip != null && this.audioClip.isPlaying())
			return true;
		else
			return false;
	}
}
