package exception;

public class MusicNotFoundException extends Exception
{
	/*
	 * 找不到音乐文件异常
	 */
	
	private static final long serialVersionUID = 1L;
	
	public MusicNotFoundException() //构造函数
	{
		super();
	}
	
	@Override
	public String getMessage() //返回错误消息函数
	{
		return "The music of this clock can not be found.";
	}
}
