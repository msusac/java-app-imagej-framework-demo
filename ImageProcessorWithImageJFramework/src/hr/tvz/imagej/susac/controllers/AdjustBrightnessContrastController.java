package hr.tvz.imagej.susac.controllers;

import ij.ImagePlus;
import ij.gui.HistogramWindow;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class AdjustBrightnessContrastController {

	@FXML 
	public Button button_adjust;
	
	@FXML
	public Button button_reset;
	
	@FXML 
	public Button button_cancel;
	
	@FXML
	public Button button_preview;
	
	@FXML
	public Slider slider_min;
	
	@FXML
	public Slider slider_max;
	
	@FXML
	public Label label_min;
	
	@FXML
	public Label label_max;
	
	@FXML
	public ImageView imageView_preview;
	
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
			label_min.setText(Integer.toString((int) slider_min.getValue()));
		});
        
		slider_max.valueProperty().addListener((observable, oldValue, newValue) -> {
			max_current_value = slider_max.getValue();
			label_max.setText(Integer.toString((int) slider_max.getValue()));
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
	    slider_min.setValue(min_default_value);
	    slider_max.setValue(max_default_value);
	}
	
	@FXML
	public void button_cancel_action_event(ActionEvent event) {
		slider_min.setValue(min_default_value);
		slider_max.setValue(max_default_value);
		
		Stage stage = (Stage) button_adjust.getScene().getWindow();
	    stage.close();
	}
	
	@FXML
	public void button_histogram_action_event(ActionEvent event) {
		HistogramWindow histogram = new HistogramWindow(image);
		histogram.show();
	}
	
	@FXML
	public void imageView_preview_drag_over() throws NullPointerException {
		ImagePlus image_preview_ip = new ImagePlus();
		image_preview_ip.setImage(image.getImage());
		image_preview_ip.getProcessor().setMinAndMax(min_current_value, max_current_value);
		
		Image image_preview_fx = SwingFXUtils.toFXImage(image_preview_ip.getBufferedImage(), null);
		imageView_preview.setImage(image_preview_fx);
	}
	
	public void setImage(Double min_value, Double max_value, ImagePlus ip) {
		image = new ImagePlus();
		
		this.slider_min.setValue(min_value);
		this.slider_max.setValue(max_value);
		this.min_default_value = min_value;
		this.max_default_value = max_value;
		this.image.setImage(ip.getImage());
	}
	
	public Double getCurrentMin() {
		return min_current_value;
	}
	
	public Double getCurrentMax() {
		return max_current_value;
	}
	
	public Boolean getStageClosedOnExit() {
		return stage_closed_on_exit_status;
	}
}
