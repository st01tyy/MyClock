package exception;

public class InterfaceLoadException extends Exception
{
	/*
	 * ��������쳣��һ��ΪFXML�ļ���ȡ�쳣��
	 */
	
	private static final long serialVersionUID = 1L;
	
	private String message;
	
	public InterfaceLoadException(String message)
	{
		super();
		this.message = message;
	}
	
	@Override
	public String getMessage()
	{
		return this.message;
	}
	
}
