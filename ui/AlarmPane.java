package ui;

import java.util.Calendar;
import java.util.TimeZone;

import com.jfoenix.controls.JFXButton;

import core.Clock;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class AlarmPane 
{
	/*
	 * 响铃界面FXML控制器
	 */
	
    @FXML
    private Label label_time;

    @FXML
    private JFXButton btn_confirm;
    
    private Stage stage;
    
    private Clock clock;

    public void initialize(Stage stage, Clock clock)
    {
    	this.stage = stage;
    	this.clock = clock;
    	this.btn_confirm.setOnMouseClicked(e -> this.onConfirmClicked());
    	this.label_time.setText(formatTime(Calendar.getInstance(TimeZone.getDefault()).get(Calendar.HOUR_OF_DAY), Calendar.getInstance(TimeZone.getDefault()).get(Calendar.MINUTE)));
    }
    private String formatTime(int hour, int minute) //格式化显示时间
    {
    	StringBuffer str = new StringBuffer("");
    	if(hour < 10)
    		str.append("0");
    	str.append(hour);
    	str.append(":");
    	if(minute < 10)
    		str.append("0");
    	str.append(minute);
    	return str.toString();
    }
    private void onConfirmClicked() //停止响铃
    {
    	this.clock.stopMusic();
    	this.stage.close();
    	clock.restart();
    }
}
