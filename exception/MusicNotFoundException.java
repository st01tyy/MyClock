package exception;

public class MusicNotFoundException extends Exception
{
	/*
	 * �Ҳ��������ļ��쳣
	 */
	
	private static final long serialVersionUID = 1L;
	
	public MusicNotFoundException() //���캯��
	{
		super();
	}
	
	@Override
	public String getMessage() //���ش�����Ϣ����
	{
		return "The music of this clock can not be found.";
	}
}
