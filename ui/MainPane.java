package ui;

import java.io.IOException;
import java.util.List;

import com.jfoenix.controls.JFXButton;

import core.Clock;
import exception.InterfaceLoadException;
import function.InterfaceFunction;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainPane 
{
	/*
	 * 主界面FXML控制器
	 */
	
    @FXML
    private JFXButton btn_create;

    @FXML
    private GridPane gridPane;
    
    public void initialize(List<Clock> clockList) throws InterfaceLoadException //初始化函数
    {
    	this.btn_create.setOnMouseClicked
    	(
    			e -> 
    			{
    				try 
    				{
    					this.onCreateClicked();
    				} 
    				catch (InterfaceLoadException e1) 
    				{
    					e1.printStackTrace();
    					InterfaceFunction.thorwException(e1);
    				}
    			}
    	);
    	for(int a = 0; a < clockList.size(); a++)
    	{
    		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/ClockPane.fxml"));
    		try 
    		{
				HBox hbox = loader.load();
				ClockPane controller = loader.getController();
				controller.initialize(clockList.get(a), a);
				gridPane.add(hbox, 0, a);
			} 
    		catch (IOException e1)
    		{
				e1.printStackTrace();
				throw new InterfaceLoadException("Failed to load ClockPane");
			}
    	}
    	this.gridPane.setPrefSize(500, Math.max(300,clockList.size()*100));
    }
    private void onCreateClicked() throws InterfaceLoadException
    {
    	FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/CreatePane.fxml"));
    	try 
    	{
    		//打开新建菜单
			VBox vbox = loader.load();
			CreatePane controller = loader.getController();
			Stage stage = new Stage();
			controller.initialize(stage);
			stage.setScene(new Scene(vbox, vbox.getPrefWidth(), vbox.getPrefHeight()));
			stage.setTitle("新建");
			stage.show();
		} 
    	catch (IOException e) //捕获文件读取异常
    	{
			e.printStackTrace();
			throw new InterfaceLoadException("Failed to load CreatePane"); //抛出界面加载异常
		}
    	
    }

}
