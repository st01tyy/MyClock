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
	 * �����ӽ���FXML������
	 */
	
    @FXML
    private Label label_time;

    @FXML
    private Label label_clockName;

    @FXML
    private JFXButton btn_delete;

    @FXML
    private JFXToggleButton toggleButton;
    
    private int index; //��ǰ�������б��е��±�
    private Clock clock; //��ǰ����
    
    public void initialize(Clock clock, int index) //��ʼ������������������������ÿؼ�����
    {
    	this.index = index;
    	this.label_time.setText(this.formatTime(clock.getAlarmHour(), clock.getAlarmMinute()));
    	this.label_clockName.setText(clock.getClockName());
    	this.clock = clock;
    	this.toggleButton.setOnMouseClicked(e -> this.onToggleButtonClicked());
    	this.btn_delete.setStyle("-fx-background-image: url('/picture/settings_button_background.png')");
    	this.btn_delete.setOnMouseClicked(e -> this.onDeleteClicked());
    }
    private String formatTime(int hour, int minute) //��ʽ����ʾʱ��
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
    private void onToggleButtonClicked() //�������������
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
