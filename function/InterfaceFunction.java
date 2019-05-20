package function;

import java.io.IOException;

import core.Clock;
import exception.InterfaceLoadException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.Background;
import ui.AlarmPane;
import ui.ExceptionPane;
import ui.MainPane;

public class InterfaceFunction 
{
	/*
	 * ������غ����࣬��Ϊ��̬����
	 */
	
	public static void thorwException(Exception e) //ͼ�λ���ʾ������Ϣ
	{
		Stage stage = new Stage();
		stage.setScene(new Scene(new ExceptionPane(e), 600, 400));
		stage.setTitle("Exception");
		stage.show();
	}
	public static void setMainPane() throws InterfaceLoadException //����������
	{
		FXMLLoader loader = new FXMLLoader(InterfaceFunction.class.getResource("/fxml/MainPane.fxml"));
		try 
		{
			Pane pane = loader.load();
			MainPane controller = loader.getController();
			controller.initialize(Background.clockList);
			Background.mainStage.setScene(new Scene(pane, pane.getPrefWidth(), pane.getPrefHeight()));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
			throw new InterfaceLoadException("Failed to load MainPane");
		}
		
	}
	public static void alarm(Clock clcok) throws InterfaceLoadException
	{
		Stage stage = new Stage();
		FXMLLoader loader = new FXMLLoader(InterfaceFunction.class.getResource("/fxml/AlarmPane.fxml"));
		Pane pane;
		try 
		{
			pane = loader.load();
			AlarmPane controller = loader.getController();
			controller.initialize(stage, clcok);
			stage.setScene(new Scene(pane, pane.getPrefWidth(), pane.getPrefHeight()));
			stage.setTitle("ʱ�䵽��");
			stage.show();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
			throw new InterfaceLoadException("Failed to load AlarmPane");
		}
		
	}
}
