package ui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToggleButton;

import core.Clock;
import exception.InterfaceLoadException;
import function.InterfaceFunction;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import main.Background;

public class ClockPane 
{
	/*
	 * 闹钟子界面FXML控制器
	 */
	
    @FXML
    private Label label_time;

    @FXML
    private Label label_clockName;

    @FXML
    private JFXButton btn_delete;

    @FXML
    private JFXToggleButton toggleButton;
    
    private int index; //当前闹钟在列表中的下标
    private Clock clock; //当前闹钟
    
    public void initialize(Clock clock, int index) //初始化函数，根据闹钟类参数配置控件属性
    {
    	this.index = index;
    	this.label_time.setText(this.formatTime(clock.getAlarmHour(), clock.getAlarmMinute()));
    	this.label_clockName.setText(clock.getClockName());
    	this.clock = clock;
    	this.toggleButton.setOnMouseClicked(e -> this.onToggleButtonClicked());
    	this.btn_delete.setStyle("-fx-background-image: url('/picture/settings_button_background.png')");
    	this.btn_delete.setOnMouseClicked(e -> this.onDeleteClicked());
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
    private void onToggleButtonClicked() //启动或禁用闹钟
    {
    	System.out.println(clock.getState());
    	
    	if(this.toggleButton.isSelected())
    	{
    		System.out.println("selected");
    		clock.setEnable(true);
    		clock.restart();
    	}
    	else
    		clock.setEnable(false);
    }
    private void onDeleteClicked()
    {
    	if(!clock.isEnable())
    	{
    		Background.clockList.remove(index);
    		try 
    		{
				InterfaceFunction.setMainPane();
			} 
    		catch (InterfaceLoadException e) 
    		{
				e.printStackTrace();
				InterfaceFunction.thorwException(e);
			}
    	}
    }
}
