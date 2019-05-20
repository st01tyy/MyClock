package exception;

public class InterfaceLoadException extends Exception
{
	/*
	 * 界面加载异常（一般为FXML文件读取异常）
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
