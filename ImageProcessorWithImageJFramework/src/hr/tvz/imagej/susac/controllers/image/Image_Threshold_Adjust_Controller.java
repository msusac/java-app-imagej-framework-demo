package hr.tvz.imagej.susac.controllers.image;

import hr.tvz.imagej.susac.enums.Threshold_Lut_Types;
import ij.ImagePlus;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class Image_Threshold_Adjust_Controller {

	@FXML
	public Button button_adjust;
	
	@FXML
	public Button button_reset;
	
	@FXML
	public Button button_cancel;
	
	@FXML
	public ComboBox<Threshold_Lut_Types> comboBox_lut;
	
	@FXML
	public ImageView imageView_preview;
	
	@FXML
	public Label label_minimum_threshold;
	
	@FXML
	public Label label_maximum_threshold;
	
	@FXML
	public Slider slider_min;
	
	@FXML
	public Slider slider_max;
	
	private Integer lut;
	
	public double min_current_value;
	public double max_current_value;
	
	public double min_default_value;
	public double max_default_value;
	
	public double min_return_value;
	public double max_return_value;
	
	public boolean stage_closed_on_exit_status = true;
	
	public ImagePlus image = new ImagePlus();
	
	@FXML
	public void initialize(){

		slider_min.valueProperty().addListener((observable, oldValue, newValue) -> {
			min_current_value = slider_min.getValue();
			label_minimum_threshold.setText(Integer.toString((int) slider_min.getValue()));
			
			if(max_current_value <= min_current_value) {
				slider_max.setValue(min_current_value);
			}
		});
        
		slider_max.valueProperty().addListener((observable, oldValue, newValue) -> {
			max_current_value = slider_max.getValue();
			label_maximum_threshold.setText(Integer.toString((int) slider_max.getValue()));
			
			if(max_current_value <= min_current_value) {
				slider_min.setValue(max_current_value);
			}
		});
		
		comboBox_lut.getItems().addAll(Threshold_Lut_Types.values());
		comboBox_lut.getSelectionModel().select(Threshold_Lut_Types.RED_LUT);
	    comboBox_lut.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
	    	lut = newValue.getDisplayLutValue();
	    });
    }
	
	@FXML
	public void button_adjust_action_event(ActionEvent event) {
		
		min_return_value = min_current_value;
		max_return_value = max_current_value;
		stage_closed_on_exit_status = false;
		
	    Stage stage = (Stage) button_adjust.getScene().getWindow();
	    stage.close();
	}
	
	@FXML
	public void button_reset_action_event(ActionEvent event) {
	    slider_min.setValue(new Double("0"));
	    slider_max.setValue(new Double("0"));
	}
	
	@FXML
	public void button_cancel_action_event(ActionEvent event) {
		slider_min.setValue(new Double("0"));
	    slider_max.setValue(new Double("0"));
		
		Stage stage = (Stage) button_adjust.getScene().getWindow();
	    stage.close();
	}
	
	@FXML
	public void imageView_threshold_preview() {
		ImagePlus image_preview_ip = new ImagePlus();
		image_preview_ip.setImage(image.getImage());
		
		lut = comboBox_lut.getValue().getDisplayLutValue();
		
		image_preview_ip.getProcessor().setThreshold(min_current_value, max_current_value, lut);
		
		Image image_preview_fx = SwingFXUtils.toFXImage(image_preview_ip.getBufferedImage(), null);
		imageView_preview.setImage(image_preview_fx);
	}
	
	public void setImage(ImagePlus ip) {
		image = new ImagePlus();
		
		this.image.setImage(ip.getImage());
	}
	
	public Boolean getStageClosedOnExit() {
		return stage_closed_on_exit_status;
	}
	
	public Double getCurrentMin() {
		return min_current_value;
	}
	
	public Double getCurrentMax() {
		return max_current_value;
	}
	
	public Integer getLut() {
		return lut;
	}
}
