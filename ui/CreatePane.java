package ui;

import com.jfoenix.controls.JFXButton;

import core.Clock;
import exception.InterfaceLoadException;
import function.InterfaceFunction;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import main.Background;

public class CreatePane 
{
	/*
	 * 新建闹铃界面FXML控制器
	 */
	
	@FXML
    private TextField txtfield_name;

    @FXML
    private Button btn_choose;

    @FXML
    private JFXButton btn_confirm;

    @FXML
    private TextField txtfield_minute;
    
    @FXML
    private Label label_error;

    @FXML
    private TextField txtfield_hour;

    @FXML
    private TextField txtfield_path;
    
    private Stage stage;
    
    public void initialize(Stage stage) //初始化
    {
    	this.stage = stage;
    	this.btn_confirm.setOnMouseClicked(e -> this.onConfirmClicked());
    	this.btn_choose.setOnMouseClicked(e -> this.onChooseClicked());
    }
    private void onConfirmClicked() //确认按钮反应器
    {
    	if(isValid())
    	{
    		Background.clockList.add(new Clock(this.txtfield_name.getText(), new Integer(this.txtfield_hour.getText()).intValue(), new Integer(this.txtfield_minute.getText()).intValue(), this.txtfield_path.getText(), false));
    		try 
    		{
				InterfaceFunction.setMainPane();
				this.stage.close();
			} 
    		catch (InterfaceLoadException e) 
    		{
				e.printStackTrace();
				InterfaceFunction.thorwException(e);
			}
    	}
    	else
    		this.label_error.setVisible(true);
    }
    private void onChooseClicked() //选择音乐文件
    {
    	FileChooser fileChooser = new FileChooser();
    	fileChooser.setTitle("选择铃声");
    	this.txtfield_path.setText(fileChooser.showOpenDialog(new Stage()).getAbsolutePath());
    }
    private boolean isValid()
    {
    	Integer hour, minute;
    	try
    	{
    		hour = new Integer(this.txtfield_hour.getText());
    		minute = new Integer(this.txtfield_minute.getText());
    	}
    	catch(Exception e)
    	{
    		return false;
    	}
    	if(hour >=0 && hour <=23 && minute >=0 && minute < 60)
    		return true;
    	else
    		return false;
    }

}
