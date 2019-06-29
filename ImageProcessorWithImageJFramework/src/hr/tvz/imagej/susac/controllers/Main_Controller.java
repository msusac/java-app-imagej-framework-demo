package hr.tvz.imagej.susac.controllers;

import java.lang.reflect.InvocationTargetException;

import hr.tvz.imagej.susac.controllers.file.File_SaveAs_Controller;
import hr.tvz.imagej.susac.controllers.image.Image_BrightnessContrast_Adjust_Controller;
import hr.tvz.imagej.susac.controllers.image.Image_ConvertType_Controller;
import hr.tvz.imagej.susac.controllers.image.Image_Threshold_Adjust_Controller;
import hr.tvz.imagej.susac.controllers.image.Image_Threshold_Auto_Controller;
import hr.tvz.imagej.susac.controllers.process.Process_Add_Noise_Controller;
import hr.tvz.imagej.susac.controllers.process.Process_Filter_Controller;
import hr.tvz.imagej.susac.controllers.process.Process_Shadow_Controller;
import ij.IJ;
import ij.ImagePlus;
import ij.plugin.filter.RankFilters;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main_Controller {

	@FXML
	public MenuItem file_menuItem_openImage;
	
	@FXML
	public MenuItem file_menuItem_closeImage;
	
	@FXML
	public MenuItem image_menuItem_brightness_contrast;
	
	@FXML
	public MenuItem image_menuItem_convert_type;
	
	@FXML
	public Menu image_menu_threshold;
	
	@FXML
	public MenuItem process_menuItem_filters;
	
	@FXML
	public MenuItem process_menuItem_shadow;

	@FXML 
	public MenuItem process_menu_noise;
	
	@FXML
	public MenuItem process_menu_others;
	
	@FXML
	public Text textMessage;
	
	public static ImagePlus current_image;
	
	@FXML
	public void file_openImage() throws InvocationTargetException {
		
		try {
			current_image = IJ.openImage();
			onImageOpened();
			current_image.show();
		}
		catch(NullPointerException e) {
			
		}
	}
	
	public void file_closeImage() {
		
		try {
			current_image.close();
			onImageClosed();
			file_closeSuccessfullMessage(current_image.getTitle());
		}
		catch(RuntimeException e) {
			setTextMessage("Error! There are no images to close!");
		}
	}
	
	@FXML
	public void file_SaveAs() {
		
		try {
	        
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxmls/file/File_SaveAs_Layout.fxml"));
	        Parent root = (Parent) loader.load();
	        
	        File_SaveAs_Controller controller = loader.<File_SaveAs_Controller>getController();
	        controller.setImage(current_image);
	        
	        Stage stage = new Stage();
	        
	        stage.setResizable(false);
	        stage.initModality(Modality.APPLICATION_MODAL);
	        stage.initStyle(StageStyle.UTILITY);
			stage.setTitle("Save option for " + current_image.getTitle());
			stage.setScene(new Scene(root));
			
			stage.showAndWait();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void onImageOpened() {
		
		file_menuItem_openImage.setDisable(true);
		file_menuItem_closeImage.setDisable(false);
		image_menuItem_brightness_contrast.setDisable(false);
		image_menuItem_convert_type.setDisable(false);
		image_menu_threshold.setDisable(false);
		process_menuItem_filters.setDisable(false);
	    process_menuItem_shadow.setDisable(false);
		process_menu_noise.setDisable(false);
		process_menu_others.setDisable(false);
	}
	
	public void onImageClosed() {
		
		file_menuItem_openImage.setDisable(false);
		file_menuItem_closeImage.setDisable(true);
		image_menuItem_brightness_contrast.setDisable(true);
		image_menuItem_convert_type.setDisable(true);
		image_menu_threshold.setDisable(true);
		process_menuItem_filters.setDisable(true);
	    process_menuItem_shadow.setDisable(true);
		process_menu_noise.setDisable(true);
		process_menu_others.setDisable(true);
		
	}
	
	@FXML
	public void image_adjustBrightnessContrast() {
		
		try {
	        
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxmls/image/Image_BrightnessContrast_Adjust_Layout.fxml"));
	        Parent root = (Parent) loader.load();
	        
	        Image_BrightnessContrast_Adjust_Controller controller = loader.<Image_BrightnessContrast_Adjust_Controller>getController();
	        controller.setImage(current_image.getProcessor().getMin(), current_image.getProcessor().getMax(), current_image);
	        
	        Stage stage = new Stage();
	        
	        stage.setResizable(false);
	        stage.initModality(Modality.APPLICATION_MODAL);
	        stage.initStyle(StageStyle.UTILITY);
			stage.setTitle("Adjust Brightness/Contrast for " + current_image.getTitle());
			stage.setScene(new Scene(root));
			
			stage.showAndWait();
			
			if(!controller.getStageClosedOnExit()) {
				current_image.setProcessor(controller.getImageProcessor());
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void image_ConvertType() {
		
		try {
	        
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxmls/image/Image_ConvertType_Layout.fxml"));
	        Parent root = (Parent) loader.load();
	        
	        Image_ConvertType_Controller controller = loader.<Image_ConvertType_Controller>getController();
	        controller.setImage(current_image);
	        
	        Stage stage = new Stage();
	        
	        stage.setResizable(false);
	        stage.initModality(Modality.APPLICATION_MODAL);
	        stage.initStyle(StageStyle.UTILITY);
			stage.setTitle("Convert image type for " + current_image.getTitle());
			stage.setScene(new Scene(root));
			
			stage.showAndWait();
			
			if(!controller.getStageClosedOnExit()) {
				current_image.setImage(controller.getImagePlus());
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void image_threshold_auto() {
		
		if(current_image.getType() != ImagePlus.GRAY8) {
			Alert alert = new Alert(AlertType.WARNING, "You need to have 8-bit image!", ButtonType.OK);
			alert.setTitle("Threshold Warning!");
			alert.showAndWait();
		}
		else {
			try {
		        
		        FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxmls/image/Image_Threshold_Auto_Layout.fxml"));
		        Parent root = (Parent) loader.load();
		        
		        Image_Threshold_Auto_Controller controller = loader.<Image_Threshold_Auto_Controller>getController();
		        controller.setImage(current_image);
		        
		        Stage stage = new Stage();
		        
		        stage.setResizable(false);
		        stage.initModality(Modality.APPLICATION_MODAL);
		        stage.initStyle(StageStyle.UTILITY);
				stage.setTitle("Auto threshold preview for " + current_image.getTitle());
				stage.setScene(new Scene(root));
				
				stage.showAndWait();
				
				if(!controller.getStageClosedOnExit()) {
					current_image.setProcessor(controller.getImageProcessor());
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@FXML
	public void image_threshold_auto_instant() {
		
		if(current_image.getType() != ImagePlus.GRAY8) {
			Alert alert = new Alert(AlertType.WARNING, "You need to have 8-bit image!", ButtonType.OK);
			alert.setTitle("Threshold Warning!");
			alert.showAndWait();
		}
		else {
			ImagePlus image_backup = new ImagePlus();
			image_backup.setImage(current_image.getImage());
		
			image_backup.getProcessor().autoThreshold();
		
			current_image.setProcessor(image_backup.getProcessor());
		}
	}
	
	@FXML
	public void image_threshold_adjust() {
		
		if(current_image.getType() != ImagePlus.GRAY8) {
			Alert alert = new Alert(AlertType.WARNING, "You need to have 8-bit image!", ButtonType.OK);
			alert.setTitle("Threshold Warning!");
			alert.showAndWait();
		}
		else {
			try {
		        
		        FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxmls/image/Image_Threshold_Adjust_Layout.fxml"));
		        Parent root = (Parent) loader.load();
		        
		        Image_Threshold_Adjust_Controller controller = loader.<Image_Threshold_Adjust_Controller>getController();
		        controller.setImage(current_image);
		        
		        Stage stage = new Stage();
		        
		        stage.setResizable(false);
		        stage.initModality(Modality.APPLICATION_MODAL);
		        stage.initStyle(StageStyle.UTILITY);
				stage.setTitle("Adjust threshold preview for " + current_image.getTitle());
				stage.setScene(new Scene(root));
				
				stage.showAndWait();
				
				if(!controller.getStageClosedOnExit()) {
					current_image.setProcessor(controller.getImageProcessor());
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@FXML
	public void process_find_edges() {
		ImagePlus image_backup = new ImagePlus();
		image_backup.setImage(current_image.getImage());
	
		image_backup.getProcessor().findEdges();
	
		current_image.setProcessor(image_backup.getProcessor());
	}
	
	@FXML
	public void process_invert() {
		ImagePlus image_backup = new ImagePlus();
		image_backup.setImage(current_image.getImage());
	
		image_backup.getProcessor().invert();
	
		current_image.setProcessor(image_backup.getProcessor());
	}
	
	@FXML
	public void process_filter() {
		try {
	        
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxmls/process/Process_Filter_Layout.fxml"));
	        Parent root = (Parent) loader.load();
	        
	        Process_Filter_Controller controller = loader.<Process_Filter_Controller>getController();
	        controller.setImage(current_image);
	        
	        Stage stage = new Stage();
	        
	        stage.setResizable(false);
	        stage.initModality(Modality.APPLICATION_MODAL);
	        stage.initStyle(StageStyle.UTILITY);
			stage.setTitle("Shadow preview for " + current_image.getTitle());
			stage.setScene(new Scene(root));
			
			stage.showAndWait();
			
			if(!controller.getStageClosedOnExit()) {
				current_image.setProcessor(controller.getImageProcessor());
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void process_shadow() {
		
		try {
	        
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxmls/process/Process_Shadow_Layout.fxml"));
	        Parent root = (Parent) loader.load();
	        
	        Process_Shadow_Controller controller = loader.<Process_Shadow_Controller>getController();
	        controller.setImage(current_image);
	        
	        Stage stage = new Stage();
	        
	        stage.setResizable(false);
	        stage.initModality(Modality.APPLICATION_MODAL);
	        stage.initStyle(StageStyle.UTILITY);
			stage.setTitle("Shadow preview for " + current_image.getTitle());
			stage.setScene(new Scene(root));
			
			stage.showAndWait();
			
			if(!controller.getStageClosedOnExit()) {
				current_image.setProcessor(controller.getImageProcessor());
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void process_sharpen() {
		ImagePlus image_backup = new ImagePlus();
		image_backup.setImage(current_image.getImage());
	
		image_backup.getProcessor().sharpen();
	
		current_image.setProcessor(image_backup.getProcessor());
	}
	
	@FXML
	public void process_smooth() {
		ImagePlus image_backup = new ImagePlus();
		image_backup.setImage(current_image.getImage());
	
		image_backup.getProcessor().smooth();
	
		current_image.setProcessor(image_backup.getProcessor());
	}
	
	@FXML
	public void process_noise_add() {
		try {
	        
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxmls/process/Process_Add_Noise_Layout.fxml"));
	        Parent root = (Parent) loader.load();
	        
	        Process_Add_Noise_Controller controller = loader.<Process_Add_Noise_Controller>getController();
	        controller.setImage(current_image);
	        
	        Stage stage = new Stage();
	        
	        stage.setResizable(false);
	        stage.initModality(Modality.APPLICATION_MODAL);
	        stage.initStyle(StageStyle.UTILITY);
			stage.setTitle("Add noise preview for " + current_image.getTitle());
			stage.setScene(new Scene(root));
			
			stage.showAndWait();
			
			if(!controller.getStageClosedOnExit()) {
				current_image.setProcessor(controller.getImageProcessor());
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void process_noise_despekle() {
		ImagePlus image_backup = new ImagePlus();
		image_backup.setImage(current_image.getImage());
	
		RankFilters filter = new RankFilters();
		filter.rank(image_backup.getProcessor(), 1.0, RankFilters.MEDIAN);
	
		current_image.setProcessor(image_backup.getProcessor());
	}
	
	/*Messages*/
	
	public void setTextMessage(String message) {
		textMessage.setText(message);
	}
	
	public void file_openSuccessfullMessage(String imageTitle) {
		textMessage.setText(imageTitle + " " + "was successfully opened!");
	}
	
	public void file_closeSuccessfullMessage(String imageTitle) {
		textMessage.setText(imageTitle + " " + "was successfully closed!");
	}
}
