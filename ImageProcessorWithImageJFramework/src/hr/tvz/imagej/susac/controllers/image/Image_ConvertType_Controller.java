package hr.tvz.imagej.susac.controllers.image;

import ij.ImagePlus;
import ij.process.ImageConverter;
import ij.process.ImageProcessor;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Button;

public class Image_ConvertType_Controller {

	@FXML
	public Button button_adjust;
	
	@FXML
	public Button button_cancel;
	
	@FXML
	public Button button_gray_8_bit;
	
	@FXML
	public Button button_gray_16_bit;
	
	@FXML
	public Button button_gray_32_bit;
	
	@FXML
	public Button button_rgb;
	
	@FXML
	public Button button_rgb_stack;
	
	@FXML
	public Button button_hsb;
	
	@FXML
	public Button button_lab;
	
	@FXML
	public Button button_rgb_stack_to_lab;
	
	@FXML
	public Button button_hsb_to_rgb;
	
	@FXML
	public Button button_lab_to_rgb;
	
	@FXML
	public ImageView imageView_preview;
	
	@FXML
	public Text textView_status;
	
	private boolean stage_closed_on_exit_status = true;
	
	private ImagePlus image = new ImagePlus();
	private ImagePlus image_preview_ip = new ImagePlus();
	
	@FXML
	public void button_adjust_action_event(ActionEvent event) {
		
		stage_closed_on_exit_status = false;
		
	    Stage stage = (Stage) button_adjust.getScene().getWindow();
	    stage.close();
	}
	
	@FXML
	public void button_cancel_action_event(ActionEvent event) {
		
		Stage stage = (Stage) button_adjust.getScene().getWindow();
	    stage.close();
	}
	
	@FXML
	public void image_convertToGray8Bit() {
		
		image_preview_ip = new ImagePlus();
		image_preview_ip.setImage(image);
		
		try {
			ImageConverter imageConverter = new ImageConverter(image_preview_ip);
			imageConverter.convertToGray8();
			set_imageViewPreview_textViewStatus(image_preview_ip);
		}
		catch(IllegalArgumentException e) {
			image_convertFailedMessage(e.getMessage());
		}
	}
		
	@FXML
	public void image_convertToGray16Bit() {
		
		image_preview_ip = new ImagePlus();
		image_preview_ip.setImage(image);
		
		try {
			ImageConverter imageConverter = new ImageConverter(image_preview_ip);
			imageConverter.convertToGray16();
			set_imageViewPreview_textViewStatus(image_preview_ip);
		}
		catch(IllegalArgumentException e) {
			image_convertFailedMessage(e.getMessage());
		}
	}
		
	@FXML
	public void image_convertToGray32Bit() {
		
		image_preview_ip = new ImagePlus();
		image_preview_ip.setImage(image);
		
		try {
			ImageConverter imageConverter = new ImageConverter(image_preview_ip);
			imageConverter.convertToGray32();
			set_imageViewPreview_textViewStatus(image_preview_ip);
		}
		catch(IllegalArgumentException e) {
			image_convertFailedMessage(e.getMessage());
		}
	}
		
	@FXML
	public void image_convertToRGB() {
		
		image_preview_ip = new ImagePlus();
		image_preview_ip.setImage(image);
		
		ImageConverter imageConverter = new ImageConverter(image_preview_ip);
		imageConverter.convertToRGB();
		set_imageViewPreview_textViewStatus(image_preview_ip);
	}
		
	@FXML
	public void image_convertToRGBStack() {
		
		image_preview_ip = new ImagePlus();
		image_preview_ip.setImage(image);
		
		try {
			ImageConverter imageConverter = new ImageConverter(image_preview_ip);
			imageConverter.convertToRGBStack();
			set_imageViewPreview_textViewStatus(image_preview_ip);
		}
		catch(IllegalArgumentException e) {
			image_convertFailedMessage(e.getMessage());
		}
	}
		
	@FXML
	public void image_convertToHSB() {
		
		image_preview_ip = new ImagePlus();
		image_preview_ip.setImage(image);
		
		try {
			ImageConverter imageConverter = new ImageConverter(image_preview_ip);
			imageConverter.convertToHSB();
			set_imageViewPreview_textViewStatus(image_preview_ip);
		}
		catch(IllegalArgumentException e) {
			image_convertFailedMessage(e.getMessage());
		}
	}
		
	@FXML
	public void image_convertToLab() {
		
		image_preview_ip = new ImagePlus();
		image_preview_ip.setImage(image);
		
		try {
			ImageConverter imageConverter = new ImageConverter(image_preview_ip);
			imageConverter.convertToLab();
			set_imageViewPreview_textViewStatus(image_preview_ip);
		}
		catch(IllegalArgumentException e) {
			image_convertFailedMessage(e.getMessage());
		}
	}
		
	@FXML
	public void image_convertRGBStackToLab() {
		
		image_preview_ip = new ImagePlus();
		image_preview_ip.setImage(image);
		
		try {
			ImageConverter imageConverter = new ImageConverter(image_preview_ip);
			imageConverter.convertRGBStackToRGB();
			set_imageViewPreview_textViewStatus(image_preview_ip);
		}
		catch(IllegalArgumentException e) {
			image_convertFailedMessage(e.getMessage());
		}
	}
		
	@FXML
	public void image_convertHSBToRGB() {
		
		image_preview_ip = new ImagePlus();
		image_preview_ip.setImage(image);
		
		try {
			ImageConverter imageConverter = new ImageConverter(image_preview_ip);
			imageConverter.convertHSBToRGB();
			set_imageViewPreview_textViewStatus(image_preview_ip);
		}
		catch(IllegalArgumentException e) {
			image_convertFailedMessage(e.getMessage());
		}
	}
		
	@FXML
	public void image_convertLabToRGB() {
		
		image_preview_ip = new ImagePlus();
		image_preview_ip.setImage(image);
		
		try {
			ImageConverter imageConverter = new ImageConverter(image_preview_ip);
			imageConverter.convertLabToRGB();
		}
		catch(IllegalArgumentException e) {
			image_convertFailedMessage(e.getMessage());
		}
	}
	
	public void set_imageViewPreview_textViewStatus(ImagePlus image_preview_ip) {
		
		Image image_preview_fx = SwingFXUtils.toFXImage(image_preview_ip.getProcessor().getBufferedImage(), null);
		imageView_preview.setImage(image_preview_fx);
		
		textView_status.setText(String.valueOf(image_preview_ip.getType()));
	}

	public void image_convertFailedMessage(String message) {
		
		Alert alert = new Alert(AlertType.ERROR, message, ButtonType.OK);
		alert.setTitle("Image convert type error!");
		alert.showAndWait();
	}
	
	public void setImage(ImagePlus ip) {
		
		image = new ImagePlus();
		
		this.image.setImage(ip);
	}
	
	public Boolean getStageClosedOnExit() {
		return stage_closed_on_exit_status;
	}
	
	public ImagePlus getImagePlus() {
		return image_preview_ip;
	}
	
}
