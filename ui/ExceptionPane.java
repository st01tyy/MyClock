package ui;

import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;

public class ExceptionPane extends Pane
{
	/*
	 * 报错信息界面
	 */
	
	private TextArea textArea; //报错信息显示区域
	
	public ExceptionPane(Exception e) //构造函数
	{
		super();
		super.setPrefSize(600, 400);
		this.textArea = new TextArea(e.getMessage());
		this.textArea.setPrefSize(600, 400);
		this.textArea.setFocusTraversable(false);
		super.getChildren().add(textArea);
	}
}
