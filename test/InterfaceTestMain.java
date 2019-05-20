package test;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.List;

import core.Clock;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ui.MainPane;

public class InterfaceTestMain extends Application
{
	/*
	 * 界面测试主类
	 */
	
	public static void main(String[] args) 
	{
		Application.launch(args);
	}
	@Override
	public void start(Stage arg0) throws Exception 
	{
		ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream("./test.ruben")));
		@SuppressWarnings("unchecked")
		List<Clock> list = (List<Clock>)in.readObject();
		in.close();
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/MainPane.fxml"));
		Pane pane = loader.load();
		MainPane controller = loader.getController();
		controller.initialize(list);
		arg0.setScene(new Scene(pane, pane.getPrefWidth(), pane.getPrefHeight()));
		arg0.show();
//		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/ClockPane.fxml"));
//		HBox hbox = loader.load();
//		ClockPane controller = loader.getController();
//		controller.initialize(new Clock("test", 9, 19, "D:\\Files\\test.mp3", false), 0);
//		arg0.setScene(new Scene(hbox, hbox.getPrefWidth(), hbox.getPrefHeight()));
//		arg0.show();
//		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/CreatePane.fxml"));
//		VBox vbox = loader.load();
//		arg0.setScene(new Scene(vbox, vbox.getPrefWidth(), vbox.getPrefHeight()));
//		arg0.show();
	}
}
