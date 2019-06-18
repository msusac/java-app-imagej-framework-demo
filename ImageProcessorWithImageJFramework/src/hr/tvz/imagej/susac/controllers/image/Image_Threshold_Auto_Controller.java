package hr.tvz.imagej.susac.controllers.image;

import java.awt.List;

import hr.tvz.imagej.susac.enums.ThresholdBackgroundTypes;
import hr.tvz.imagej.susac.enums.ThresholdLutTypes;
import hr.tvz.imagej.susac.enums.ThresholdMethodTypes;
import ij.ImagePlus;
import ij.gui.HistogramWindow;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class Image_Threshold_Auto_Controller {

	@FXML
	public Button button_adjust;
	
	@FXML
	public Button button_cancel;
	
	@FXML
	public ComboBox<ThresholdMethodTypes> comboBox_method;
	
	@FXML
	public ComboBox<ThresholdBackgroundTypes> comboBox_background;
	
	@FXML
	public ComboBox<ThresholdLutTypes> comboBox_lut;
	
	@FXML
	public ImageView imageView_preview;
	
	private String method;
	private Boolean background;
	private Integer lut;
	
	public boolean stage_closed_on_exit_status = true;
	
	public ImagePlus image = new ImagePlus();
	
	@FXML
	public void initialize(){
	    comboBox_method.getItems().addAll(ThresholdMethodTypes.values());
	    comboBox_method.getSelectionModel().select(ThresholdMethodTypes.DEFAULT);
	    
	    comboBox_background.getItems().addAll(ThresholdBackgroundTypes.values());
	    comboBox_background.getSelectionModel().select(ThresholdBackgroundTypes.TRUE);
	    
	    comboBox_lut.getItems().addAll(ThresholdLutTypes.values());
	    comboBox_lut.getSelectionModel().select(ThresholdLutTypes.RED_LUT);
	}
	
	@FXML
	public void button_adjust_action_event(ActionEvent event) {
		
		stage_closed_on_exit_status = false;
		
	    Stage stage = (Stage) button_adjust.getScene().getWindow();
	    stage.close();
	}
	
	
	@FXML
	public void button_cancel_action_event(ActionEvent event) {
		
		stage_closed_on_exit_status = true;
		
		Stage stage = (Stage) button_adjust.getScene().getWindow();
	    stage.close();
	}
	
	@FXML
	public void image_preview_threshold_preview() {
		ImagePlus image_preview_ip = new ImagePlus();
		image_preview_ip.setImage(image.getImage());
		
		method = comboBox_method.getValue().getDisplayMethodName();
		background = comboBox_background.getValue().getDisplayBackgroundName();
		lut = comboBox_lut.getValue().getDisplayLutValue();
		
		image_preview_ip.getProcessor().setAutoThreshold(method, background, lut);
		
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
	
	public String getMethod() {
		return method;
	}
	
	public Boolean getBackground() {
		return background;
	}
	
	public Integer getLut() {
		return lut;
	}
}
