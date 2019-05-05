package hr.tvz.imagej.susac.controllers;

import java.lang.reflect.InvocationTargetException;

import ij.IJ;
import ij.ImagePlus;
import ij.io.FileSaver;
import ij.plugin.ContrastEnhancer;
import ij.plugin.Resizer;
import ij.process.ImageConverter;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class MainLayoutController {

	/*File - Menu items*/
	
	@FXML
	public MenuItem file_menuItem_openImage;
	
	@FXML
	public MenuItem file_menuItem_closeImage;
	
	@FXML
	public MenuItem file_menuItem_saveImage;
	
	@FXML
	public Menu file_menu_saveAs;
	
	@FXML
	public Text textMessage;
	
	public static ImagePlus image;
	
	/*File*/
	
	//File - Open image
	public void file_openImage() throws InvocationTargetException {
		try {
			image = IJ.openImage();
			onImageOpened();
			image.show();
		}
		catch(NullPointerException e) {
			
		}
	}
	
	//File - Save image (TIFF)
	public void file_saveImage() {
		
		FileSaver filesaver = new FileSaver(image);
	
		if(filesaver.save() == true) {
			file_saveSuccessfullMessage(image.getTitle());
		}
		else {
			file_saveCanceledMessage(image.getTitle());
		}
	}
	
		//File - Save image as BMP
		public void file_saveImageAsBmp() {
			FileSaver filesaver = new FileSaver(image);
			
			if(filesaver.saveAsBmp() == true) {
				file_saveSuccessfullMessage(image.getTitle());
			}
			else {
				file_saveCanceledMessage(image.getTitle());
			}
		}
		
		//File - Save image as FITS
		public void file_saveImageAsFits() {
			FileSaver filesaver = new FileSaver(image);
			
			if(filesaver.saveAsFits() == true) {
				file_saveSuccessfullMessage(image.getTitle());
			}
			else {
				file_saveAsFitsFailedMessage();
			}
		}
		
		//File - Save image as GIF
		public void file_saveImageAsGif() {
			FileSaver filesaver = new FileSaver(image);
			
			if(filesaver.saveAsGif() == true) {
				file_saveSuccessfullMessage(image.getTitle());
			}
			else {
				file_saveCanceledMessage(image.getTitle());
			}
		}
		
		//File - Save image as JPEG
		public void file_saveImageAsJpeg() {
			FileSaver filesaver = new FileSaver(image);
			
			if(filesaver.saveAsJpeg() == true) {
				file_saveSuccessfullMessage(image.getTitle());
			}
			else {
				file_saveCanceledMessage(image.getTitle());
			}
		}
		
		//File - Save image as LUT
		public void file_saveImageAsLut() {
			FileSaver filesaver = new FileSaver(image);
			
			if(filesaver.saveAsLut() == true) {
				file_saveSuccessfullMessage(image.getTitle());
			}
			else {
				file_saveAsLutFailedMessage();
			}
		}
		
		//File - Save image as PGM
		public void file_saveImageAsPgm() {
			FileSaver filesaver = new FileSaver(image);
			
			if(filesaver.saveAsPgm() == true) {
				file_saveSuccessfullMessage(image.getTitle());
			}
			else {
				file_saveCanceledMessage(image.getTitle());
			}
		}
		
		//File - Save image as PNG
		public void file_saveImageAsPng() {
			FileSaver filesaver = new FileSaver(image);
			
			if(filesaver.saveAsPng() == true) {
				file_saveSuccessfullMessage(image.getTitle());
			}
			else {
				file_saveCanceledMessage(image.getTitle());
			}
		}
		
		//File - Save image as RAW
		public void file_saveImageAsRaw() {
			FileSaver filesaver = new FileSaver(image);
			
			if(filesaver.saveAsRaw() == true) {
				file_saveSuccessfullMessage(image.getTitle());
			}
			else {
				file_saveCanceledMessage(image.getTitle());
			}
		}
	
		//File - Save image as TEXT
		public void file_saveImageAsText() {
			FileSaver filesaver = new FileSaver(image);
			
			if(filesaver.saveAsText() == true) {
				file_saveSuccessfullMessage(image.getTitle());
			}
			else {
				file_saveCanceledMessage(image.getTitle());
			}
		}
		
		//File - Save image as TIFF
		public void file_saveImageAsTiff() {
			FileSaver filesaver = new FileSaver(image);
			
			if(filesaver.saveAsTiff() == true) {
				file_saveSuccessfullMessage(image.getTitle());
			}
			else {
				file_saveCanceledMessage(image.getTitle());
			}
		}
		
		//File - Save image as ZIP
		public void file_saveImageAsZip() {
			FileSaver filesaver = new FileSaver(image);
			
			if(filesaver.saveAsZip() == true) {
				file_saveSuccessfullMessage(image.getTitle());
			}
			else {
				file_saveCanceledMessage(image.getTitle());
			}
		}
	
	//File - Close image
	public void file_closeImage() {
		try {
			image.close();
			onImageClosed();
			file_closeSuccessfullMessage(image.getTitle());
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
				ImageConverter imageConverter = new ImageConverter(image);
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
				ImageConverter imageConverter = new ImageConverter(image);
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
				ImageConverter imageConverter = new ImageConverter(image);
				imageConverter.convertToGray32();
				image_convertSuccessfullMessage("32-bit gray");
			}
			catch(IllegalArgumentException e) {
				image_convertFailedMessage(e.getMessage());
			}
		}
		
		//Image - Type - Convert To RGB
		public void image_convertToRGB() {
			ImageConverter imageConverter = new ImageConverter(image);
			imageConverter.convertToRGB();
			image_convertSuccessfullMessage("RGB");
		}
		
		//Image - Type - Convert To RGB Stack
		public void image_convertToRGBStack() {
			try {
				ImageConverter imageConverter = new ImageConverter(image);
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
				ImageConverter imageConverter = new ImageConverter(image);
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
				ImageConverter imageConverter = new ImageConverter(image);
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
				ImageConverter imageConverter = new ImageConverter(image);
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
				ImageConverter imageConverter = new ImageConverter(image);
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
				ImageConverter imageConverter = new ImageConverter(image);
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
		
		
	}
	
	public void onImageClosed() {
		file_menuItem_openImage.setDisable(false);
		file_menuItem_closeImage.setDisable(true);
		file_menuItem_saveImage.setDisable(true);
		file_menu_saveAs.setDisable(true);
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
		
}
