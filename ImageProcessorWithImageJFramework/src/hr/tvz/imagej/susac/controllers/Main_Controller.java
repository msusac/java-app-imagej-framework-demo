package hr.tvz.imagej.susac.controllers;

import java.lang.reflect.InvocationTargetException;

import hr.tvz.imagej.susac.controllers.image.Image_BrightnessContrast_Adjust_Controller;
import hr.tvz.imagej.susac.controllers.image.Image_Threshold_Adjust_Controller;
import hr.tvz.imagej.susac.controllers.image.Image_Threshold_Auto_Controller;
import hr.tvz.imagej.susac.controllers.process.Process_Add_Noise_Controller;
import hr.tvz.imagej.susac.controllers.process.Process_Filter_Controller;
import hr.tvz.imagej.susac.controllers.process.Process_Shadow_Controller;
import ij.IJ;
import ij.ImagePlus;
import ij.io.FileSaver;
import ij.plugin.filter.RankFilters;
import ij.process.ImageConverter;
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

	/*File - Menu items*/
	//Image 
	@FXML
	public MenuItem file_menuItem_openImage;
	
	@FXML
	public MenuItem file_menuItem_closeImage;
	
	@FXML
	public MenuItem file_menuItem_saveImage;
	
	@FXML
	public MenuItem image_menuItem_brightness_contrast;
	
	@FXML
	public Menu file_menu_saveAs;
	
	@FXML
	public Menu image_menu_convert_type;
	
	@FXML
	public Menu image_menu_threshold;
	
	@FXML
	public MenuItem process_menuItem_find_edges;
	
	@FXML
	public MenuItem process_menuItem_filters;
	
	@FXML
	public MenuItem process_menuItem_invert;
	
	@FXML
	public MenuItem process_menuItem_shadow;
	
	@FXML
	public MenuItem process_menuItem_sharpen;
	
	@FXML
	public MenuItem process_menuItem_smooth;
	
	@FXML 
	public MenuItem process_menu_noise;
	
	@FXML
	public Text textMessage;
	
	public static ImagePlus current_image;
	
	/*File*/
	
	//File - Open image
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
	
	//File - Save image (TIFF)
	@FXML
	public void file_saveImage() {
	
		FileSaver filesaver = new FileSaver(current_image);
	
		if(filesaver.save() == true) {
			file_saveSuccessfullMessage(current_image.getTitle());
		}
		else {
			file_saveCanceledMessage(current_image.getTitle());
		}
	}
	
	//File - Save image as BMP
	@FXML
	public void file_saveImageAsBmp() {
		FileSaver filesaver = new FileSaver(current_image);
			
		if(filesaver.saveAsBmp() == true) {
			file_saveSuccessfullMessage(current_image.getTitle());
		}
		else {
			file_saveCanceledMessage(current_image.getTitle());
		}
	}
		
	//File - Save image as FITS
	@FXML
	public void file_saveImageAsFits() {
		FileSaver filesaver = new FileSaver(current_image);
			
		if(filesaver.saveAsFits() == true) {
			file_saveSuccessfullMessage(current_image.getTitle());
		}
		else {
			file_saveAsFitsFailedMessage();
		}
	}
		
	//File - Save image as GIF
	@FXML
	public void file_saveImageAsGif() {
		FileSaver filesaver = new FileSaver(current_image);
			
		if(filesaver.saveAsGif() == true) {
			file_saveSuccessfullMessage(current_image.getTitle());
		}
		else {
			file_saveCanceledMessage(current_image.getTitle());
		}
	}
		
	//File - Save image as JPEG
	@FXML
	public void file_saveImageAsJpeg() {
		FileSaver filesaver = new FileSaver(current_image);
			
		if(filesaver.saveAsJpeg() == true) {
			file_saveSuccessfullMessage(current_image.getTitle());
		}
		else {
			file_saveCanceledMessage(current_image.getTitle());
		}
	}
		
	//File - Save image as LUT
	@FXML
	public void file_saveImageAsLut() {
		FileSaver filesaver = new FileSaver(current_image);
			
		if(filesaver.saveAsLut() == true) {
			file_saveSuccessfullMessage(current_image.getTitle());
		}
		else {
			file_saveAsLutFailedMessage();
		}
	}
		
	//File - Save image as PGM
	@FXML
	public void file_saveImageAsPgm() {
		FileSaver filesaver = new FileSaver(current_image);
			
		if(filesaver.saveAsPgm() == true) {
			file_saveSuccessfullMessage(current_image.getTitle());
		}
		else {
			file_saveCanceledMessage(current_image.getTitle());
		}
	}
		
	//File - Save image as PNG
	@FXML
	public void file_saveImageAsPng() {
		FileSaver filesaver = new FileSaver(current_image);
			
		if(filesaver.saveAsPng() == true) {
			file_saveSuccessfullMessage(current_image.getTitle());
		}
		else {
			file_saveCanceledMessage(current_image.getTitle());
		}
	}
		
	//File - Save image as RAW
	@FXML
	public void file_saveImageAsRaw() {
		FileSaver filesaver = new FileSaver(current_image);
			
		if(filesaver.saveAsRaw() == true) {
			file_saveSuccessfullMessage(current_image.getTitle());
		}
		else {
			file_saveCanceledMessage(current_image.getTitle());
		}
	}
	
	//File - Save image as TEXT
	@FXML
	public void file_saveImageAsText() {
		FileSaver filesaver = new FileSaver(current_image);
			
		if(filesaver.saveAsText() == true) {
			file_saveSuccessfullMessage(current_image.getTitle());
		}
		else {
			file_saveCanceledMessage(current_image.getTitle());
		}
	}
		
	//File - Save image as TIFF
	@FXML
	public void file_saveImageAsTiff() {
		FileSaver filesaver = new FileSaver(current_image);
			
		if(filesaver.saveAsTiff() == true) {
			file_saveSuccessfullMessage(current_image.getTitle());
		}
		else {
			file_saveCanceledMessage(current_image.getTitle());
		}
	}
		
		//File - Save image as ZIP
		public void file_saveImageAsZip() {
			FileSaver filesaver = new FileSaver(current_image);
			
			if(filesaver.saveAsZip() == true) {
				file_saveSuccessfullMessage(current_image.getTitle());
			}
			else {
				file_saveCanceledMessage(current_image.getTitle());
			}
		}
	
	//File - Close image
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
	
	/*Image*/
	
	//Image - Type Convert
	
		//Image - Type - Convert To Gray 8-bit
		public void image_convertToGray8Bit() {
			try {
				ImageConverter imageConverter = new ImageConverter(current_image);
				imageConverter.convertToGray8();
				image_convertSuccessfullMessage("8-bit gray");
			}
			catch(IllegalArgumentException e) {
				image_convertFailedMessage(e.getMessage());
			}
		}
		
		//Image - Type - Convert To Gray 16-bit
		public void image_convertToGray16Bit() {
			try {
				ImageConverter imageConverter = new ImageConverter(current_image);
				imageConverter.convertToGray16();
				image_convertSuccessfullMessage("16-bit gray");
			}
			catch(IllegalArgumentException e) {
				image_convertFailedMessage(e.getMessage());
			}
		}
		
		//Image - Type - Convert To Gray 32-bit
		public void image_convertToGray32Bit() {
			try {
				ImageConverter imageConverter = new ImageConverter(current_image);
				imageConverter.convertToGray32();
				image_convertSuccessfullMessage("32-bit gray");
			}
			catch(IllegalArgumentException e) {
				image_convertFailedMessage(e.getMessage());
			}
		}
		
		//Image - Type - Convert To RGB
		public void image_convertToRGB() {
			ImageConverter imageConverter = new ImageConverter(current_image);
			imageConverter.convertToRGB();
			image_convertSuccessfullMessage("RGB");
		}
		
		//Image - Type - Convert To RGB Stack
		public void image_convertToRGBStack() {
			try {
				ImageConverter imageConverter = new ImageConverter(current_image);
				imageConverter.convertToRGBStack();
				image_convertSuccessfullMessage("RGB Stack");
			}
			catch(IllegalArgumentException e) {
				image_convertFailedMessage(e.getMessage());
			}
		}
		
		//Image - Type - Convert to HSB
		public void image_convertToHSB() {
			try {
				ImageConverter imageConverter = new ImageConverter(current_image);
				imageConverter.convertToHSB();
				image_convertSuccessfullMessage("HSB");
			}
			catch(IllegalArgumentException e) {
				image_convertFailedMessage(e.getMessage());
			}
		}
		
		//Image - Type - Convert to Lab
		public void image_convertToLab() {
			try {
				ImageConverter imageConverter = new ImageConverter(current_image);
				imageConverter.convertToLab();
				image_convertSuccessfullMessage("Lab");
			}
			catch(IllegalArgumentException e) {
				image_convertFailedMessage(e.getMessage());
				}
			}
		
		//Image - Type - Convert RGB stack to Lab
		public void image_convertRGBStackToLab() {
			try {
				ImageConverter imageConverter = new ImageConverter(current_image);
				imageConverter.convertRGBStackToRGB();
				image_convertSuccessfullMessage("Lab");
			}
			catch(IllegalArgumentException e) {
				image_convertFailedMessage(e.getMessage());
				}
			}
		
		//Image - Type - Convert HSB stack to RGB
		public void image_convertHSBToRGB() {
			try {
				ImageConverter imageConverter = new ImageConverter(current_image);
				imageConverter.convertHSBToRGB();
				image_convertSuccessfullMessage("RGB");
			}
			catch(IllegalArgumentException e) {
				image_convertFailedMessage(e.getMessage());
			}
		}
		
		//Image - Type - Convert Lab to RGB
		public void image_convertLabToRGB() {
			try {
				ImageConverter imageConverter = new ImageConverter(current_image);
				imageConverter.convertLabToRGB();
				image_convertSuccessfullMessage("RGB");
			}
			catch(IllegalArgumentException e) {
				image_convertFailedMessage(e.getMessage());
			}
		}
	
	/*Methods*/
	
	public void onImageOpened() {
		file_menuItem_openImage.setDisable(true);
		file_menuItem_closeImage.setDisable(false);
		file_menuItem_saveImage.setDisable(false);
		file_menu_saveAs.setDisable(false);
		image_menuItem_brightness_contrast.setDisable(false);
		image_menu_convert_type.setDisable(false);
		image_menu_threshold.setDisable(false);
		process_menuItem_find_edges.setDisable(false);
		process_menuItem_filters.setDisable(false);
		process_menuItem_invert.setDisable(false);
	    process_menuItem_shadow.setDisable(false);
		process_menuItem_sharpen.setDisable(false);
		process_menuItem_smooth.setDisable(false);
		process_menu_noise.setDisable(false);
	}
	
	public void onImageClosed() {
		file_menuItem_openImage.setDisable(false);
		file_menuItem_closeImage.setDisable(true);
		file_menuItem_saveImage.setDisable(true);
		file_menu_saveAs.setDisable(true);
		image_menuItem_brightness_contrast.setDisable(true);
		image_menu_convert_type.setDisable(true);
		image_menu_threshold.setDisable(true);
		process_menuItem_find_edges.setDisable(true);
		process_menuItem_filters.setDisable(true);
		process_menuItem_invert.setDisable(true);
	    process_menuItem_shadow.setDisable(true);
		process_menuItem_sharpen.setDisable(true);
		process_menuItem_smooth.setDisable(true);
		process_menu_noise.setDisable(true);
		
	}
	
	/*Messages*/
	
	public void setTextMessage(String message) {
		textMessage.setText(message);
	}
	
	public void file_saveSuccessfullMessage(String imageTitle) {
		textMessage.setText(imageTitle + " " + "was successfully saved!");
	}
	
	public void file_saveCanceledMessage(String imageTitle) {
		textMessage.setText("Save for" + " " + imageTitle + " " + "was canceled!");
	}
	
	public void file_saveAsFitsFailedMessage() {
		textMessage.setText("Save was cancelled or The current image is not Greyscale image!");
	}
	
	public void file_saveAsLutFailedMessage() {
		textMessage.setText("Save was cancelled or RGB Image is not allowed!");
	}
	
	public void file_openSuccessfullMessage(String imageTitle) {
		textMessage.setText(imageTitle + " " + "was successfully opened!");
	}
	
	public void file_closeSuccessfullMessage(String imageTitle) {
		textMessage.setText(imageTitle + " " + "was successfully closed!");
	}
	
	public void image_convertSuccessfullMessage(String type) {
		textMessage.setText("Image successfully converted to " + type + "!");
	}
	
	public void image_convertFailedMessage(String errorMessage) {
		textMessage.setText(errorMessage);
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
}
