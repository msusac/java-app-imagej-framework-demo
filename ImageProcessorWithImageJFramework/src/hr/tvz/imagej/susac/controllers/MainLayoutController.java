package hr.tvz.imagej.susac.controllers;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import ij.IJ;
import ij.ImagePlus;
import ij.Undo;
import ij.gui.HistogramWindow;
import ij.io.FileSaver;
import ij.plugin.ContrastEnhancer;
import ij.plugin.Resizer;
import ij.process.ImageConverter;
import ij.process.ImageProcessor;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import jdk.internal.loader.Loader;

public class MainLayoutController {

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
	public MenuItem analyze_menuItem_histogram;
	
	@FXML
	public Menu file_menu_saveAs;
	
	@FXML
	public Menu image_menu_convert_type;
	
	@FXML
	public Menu image_menu_threshold;
	
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
		analyze_menuItem_histogram.setDisable(false);
		image_menu_convert_type.setDisable(false);
		image_menu_threshold.setDisable(false);
	}
	
	public void onImageClosed() {
		file_menuItem_openImage.setDisable(false);
		file_menuItem_closeImage.setDisable(true);
		file_menuItem_saveImage.setDisable(true);
		file_menu_saveAs.setDisable(true);
		image_menuItem_brightness_contrast.setDisable(true);
		analyze_menuItem_histogram.setDisable(true);
		image_menu_convert_type.setDisable(true);
		image_menu_threshold.setDisable(true);
		
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
	        
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("AdjustBrightnessContrastLayout.fxml"));
	        Parent root = (Parent) loader.load();
	        
	        AdjustBrightnessContrastController abc_controller = loader.<AdjustBrightnessContrastController>getController();
	        abc_controller.setImage(current_image.getProcessor().getMin(), current_image.getProcessor().getMax(), current_image);
	        
	        Stage stage = new Stage();
	        
	        stage.setResizable(false);
	        stage.initModality(Modality.APPLICATION_MODAL);
	        stage.initStyle(StageStyle.UTILITY);
			stage.setTitle("Adjust Brightness/Contrast for " + current_image.getTitle());
			stage.setScene(new Scene(root));
			
			stage.showAndWait();
			
			if(!abc_controller.getStageClosedOnExit()) {
				ImagePlus image_backup = new ImagePlus();
				image_backup.setImage(current_image.getImage());
				image_backup.getProcessor().setMinAndMax(abc_controller.getCurrentMin(), abc_controller.getCurrentMax());
		
				current_image.setImage(image_backup.getImage());
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void image_adjustBrightnessContrast_reset() {
		ImagePlus image_backup = new ImagePlus();
		image_backup.setImage(current_image.getImage());
		image_backup.getProcessor().resetMinAndMax();

		current_image.setImage(image_backup.getImage());
	}
	
	@FXML
	public void analyze_histogram() {
		HistogramWindow histogram = new HistogramWindow(current_image);
		histogram.show();
	}
	
	@FXML
	public void image_threshold() {
		ImagePlus image_backup = new ImagePlus();
		image_backup.setImage(current_image.getImage());

		current_image.setImage(image_backup.getImage());
	}
		
	@FXML
	public void image_threshold_auto() {
		ImagePlus image_backup = new ImagePlus();
		image_backup.setImage(current_image.getImage());
		image_backup.getProcessor().autoThreshold();

		current_image.setImage(image_backup.getImage());
	}
	
	@FXML
	public void image_threshold_auto_preview() {
		ImagePlus image_preview = new ImagePlus();
		image_preview.setImage(current_image.getImage());
		image_preview.show();
		image_preview.setTitle("PREVIEW IMAGE");
		image_preview.getProcessor().autoThreshold();
	}
}
