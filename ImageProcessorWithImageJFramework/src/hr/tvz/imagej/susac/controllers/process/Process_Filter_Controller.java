package hr.tvz.imagej.susac.controllers.process;

import hr.tvz.imagej.susac.enums.Process_Filter_Types;
import ij.ImagePlus;
import ij.plugin.filter.RankFilters;
import ij.process.ImageProcessor;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class Process_Filter_Controller {

	@FXML
	public Button button_adjust;
	
	@FXML
	public Button button_cancel;
	
	@FXML
	public ComboBox<Process_Filter_Types> comboBox_filter;
	
	@FXML
	public ImageView imageView_preview;
	
	@FXML
	public Spinner<Double> spinner;
	
	public boolean stage_closed_on_exit_status = true;
	
	public ImagePlus image = new ImagePlus();
	public ImagePlus image_preview_ip = new ImagePlus();
	
	@FXML
	public void initialize(){
	    
		comboBox_filter.getItems().addAll(Process_Filter_Types.values());
	    comboBox_filter.getSelectionModel().select(Process_Filter_Types.MEAN);
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
	public void imageView_filter_preview() {
		
		image_preview_ip = new ImagePlus();
		image_preview_ip.setImage(image.getImage());
		
		RankFilters rankFilter = new RankFilters();
		rankFilter.rank(image_preview_ip.getProcessor(), 
				        spinner.getValue(), 
				        comboBox_filter.getValue().getDisplayFilterValue());
		
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
	
	public ImageProcessor getImageProcessor() {
		return image_preview_ip.getProcessor();
	}
}
