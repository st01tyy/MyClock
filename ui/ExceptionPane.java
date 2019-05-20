package ui;

import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;

public class ExceptionPane extends Pane
{
	/*
	 * ������Ϣ����
	 */
	
	private TextArea textArea; //������Ϣ��ʾ����
	
	public ExceptionPane(Exception e) //���캯��
	{
		super();
		super.setPrefSize(600, 400);
		this.textArea = new TextArea(e.getMessage());
		this.textArea.setPrefSize(600, 400);
		this.textArea.setFocusTraversable(false);
		super.getChildren().add(textArea);
	}
}
