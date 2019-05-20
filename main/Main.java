package main;

import function.DataFunction;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application
{
	/*
	 * ���࣬�����������
	 */
	
	public static void main(String[] args) 
	{
		Application.launch(args);
	}
	@Override
	public void start(Stage arg0) throws Exception
	{
		arg0.setOnCloseRequest(e -> DataFunction.shutDown());
		Background.mainStage = arg0;
		DataFunction.startApp();
	}
}
