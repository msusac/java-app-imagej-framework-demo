package hr.tvz.imagej.susac.controllers;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import hr.tvz.imagej.susac.controllers.analyze.Analyze_Particles_Controller;
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
import ij.process.ImageProcessor;
import ij.process.ImageStatistics;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main_Controller {

	@FXML
	public Label label_current_image;
	
	@FXML
	public Label label_current_image_status;
	
	@FXML
	public MenuItem analyze_particles;
	
	@FXML
	public MenuItem file_menuItem_closeImage;
	
	@FXML
	public MenuItem file_menuItem_closeAllImage;
	
	@FXML
	public MenuItem file_menuItem_openImage;
	
	@FXML
	public MenuItem file_menuItem_saveImage;
	
	@FXML
	public MenuItem file_menuItem_saveAsImage;
	
	@FXML
	public MenuItem file_menuItem_saveAllImage;
	
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
	public ImageView iv_1;
	
	@FXML
	public ImageView iv_2;
	
	@FXML
	public ImageView iv_3;
	
	@FXML
	public ImageView iv_4;
	
	@FXML
	public ImageView iv_5;
	
	@FXML
	public ImageView iv_6;
	
	@FXML
	public ImageView iv_7;
	
	@FXML
	public ImageView iv_8;
	
	@FXML
	public ImageView iv_9;
	
	@FXML
	public ImageView iv_current_image;
	
	@FXML
	public Text textMessage;
	
	@FXML
	public VBox vbox_current_image;
	
	private ArrayList<ImageView> iv_array = new ArrayList<ImageView>();
	private ArrayList<ImagePlus> ip_array = new ArrayList<ImagePlus>();
	
	private Image default_image;
	
	private static ImagePlus current_image;
	
	@FXML
	public void initialize() {
		initialize_imageView_arrays();
		default_image = iv_1.getImage();
	}
	
	public void initialize_imageView_arrays() {
		
		iv_array.add(iv_1);
		iv_array.add(iv_2);
		iv_array.add(iv_3);
		iv_array.add(iv_4);
		iv_array.add(iv_5);
		iv_array.add(iv_6);
		iv_array.add(iv_7);
		iv_array.add(iv_8);
		iv_array.add(iv_9);
	}
		
	@FXML
	public void analyze_particles() {
		
		ImageStatistics stats = current_image.getStatistics();
		
		if (stats.histogram[0] + stats.histogram[255] != stats.pixelCount) {
			analyze_particles_alert();
		}
		else {
			analyze_particles_controller();
		}
	}
	
	private void analyze_particles_alert() {
		
		Alert alert = new Alert(AlertType.WARNING, "A thresholded image is required!", ButtonType.OK);
		alert.setTitle("Warning!");
		alert.showAndWait();
	}
	
	private void analyze_particles_controller() {
		
		try {
	        
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxmls/analyze/Analyze_Particles_Layout.fxml"));
	        Parent root = (Parent) loader.load();
	        
	        Analyze_Particles_Controller controller = loader.<Analyze_Particles_Controller>getController();
	        controller.setImage(current_image);
	        
	        Stage stage = new Stage();
	        
	        stage.setResizable(false);
	        stage.initModality(Modality.APPLICATION_MODAL);
	        stage.initStyle(StageStyle.UTILITY);
			stage.setTitle("Analyze Particles for " + current_image.getTitle());
			stage.setScene(new Scene(root));
			
			stage.showAndWait();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void file_closeImage() {
		
		Alert alert = new Alert(AlertType.CONFIRMATION, "Do you want to save before closing?", 
				ButtonType.NO, ButtonType.OK, ButtonType.CANCEL);
		
		alert.setTitle("Alert!");
		
		alert.showAndWait().ifPresent(type -> {
			
			if(type == ButtonType.OK || type == ButtonType.NO) {
				
				Integer id = Integer.valueOf(iv_current_image.getAccessibleText());
				String image_title = ip_array.get(id).getTitle();
				
				if(type == ButtonType.OK) {
					file_closeImage_save(id);
				}
				
				file_closeImage_sortImages(id);
				file_closeSuccessfullMessage(image_title);
			}
		});
	}
	
	private void file_closeImage_save(Integer id) {
		 
		String path = ip_array.get(id).getOriginalFileInfo().directory + ip_array.get(id).getTitle();
		
		IJ.save(ip_array.get(id), path);
		
		Alert alert = new Alert(AlertType.INFORMATION, "Image" + " " + ip_array.get(id).getTitle() + " " + "saved!", 
				ButtonType.OK);
			
		alert.setTitle("Alert!");
		alert.showAndWait();
	}
	
	private void file_closeImage_sortImages(Integer id) {
		
		Tooltip.uninstall(iv_array.get(id), new Tooltip(ip_array.get(id).getTitle()));
		iv_array.get(id).setImage(default_image);
		
		ip_array.remove(ip_array.get(id));
		
		for(int i = 0; i < iv_array.size(); i++) {
			iv_array.get(i).setImage(default_image);
		}
		
		for(int i = 0; i < ip_array.size(); i++) {
			
			Image image_preview_fx = SwingFXUtils.toFXImage(ip_array.get(i).getProcessor().getBufferedImage(), null);
			
			iv_array.get(i).setImage(image_preview_fx);
			Tooltip.install(iv_array.get(i), new Tooltip(ip_array.get(i).getTitle()));
		}
		
		onImageClosed();
	}
	
	@FXML
	public void file_closeAllImage() {
		
		Alert alert = new Alert(AlertType.CONFIRMATION, "Do you want to save before closing all images?", 
				ButtonType.NO, ButtonType.OK, ButtonType.CANCEL);
		
		alert.setTitle("Alert!");
		alert.showAndWait().ifPresent(type -> {
			
			if(type == ButtonType.OK || type == ButtonType.NO) {
				
				if(type == ButtonType.OK) {
					file_closeAllImage_saveAll();
				}
				
				for(int i = 0; i < ip_array.size(); i++) {
					Tooltip.uninstall(iv_array.get(i), new Tooltip(ip_array.get(i).getTitle()));
					iv_array.get(i).setImage(default_image);
				}
				
				ip_array.removeAll(ip_array);
				onImageClosed();
			}
		});
	}
	
	private void file_closeAllImage_saveAll() {
		
		for(int i = 0; i < ip_array.size(); i++) {
			
			Tooltip.uninstall(iv_array.get(i), new Tooltip(ip_array.get(i).getTitle()));
			iv_array.get(i).setImage(default_image);
		
			String path = ip_array.get(i).getOriginalFileInfo().directory + ip_array.get(i).getTitle();
			
			IJ.save(ip_array.get(i), path);
		}
		
		Alert alert_save = new Alert(AlertType.INFORMATION, "All Images" + " " + "are" + " " + "saved!", 
				ButtonType.OK);
			
		alert_save.setTitle("Alert!");
		alert_save.showAndWait();
	}
	
	@FXML
	public void file_openImage() throws InvocationTargetException {
		
		if(ip_array.size() < 9) {
			
			try {	
					current_image = new ImagePlus();
					current_image = IJ.openImage();
					file_openImage_check(current_image);
			}
			catch(NullPointerException e) {}
		}
		else {
			Alert alert = new Alert(AlertType.WARNING, "Maximum capacity reached! Please close other images!", ButtonType.OK);
			alert.setTitle("Warning!");
			alert.showAndWait();
		}
	}
	
	public void file_openImage_check(ImagePlus image) {
		
		boolean same_image_check = false;
		
		for(int i = 0; i < ip_array.size(); i++) {
			
			if(current_image.getTitle().equals(ip_array.get(i).getTitle())) {
				
				Alert alert = new Alert(AlertType.WARNING, "You have already opened the same image!", ButtonType.OK);
				alert.setTitle("ImageJ Warning!");
				alert.showAndWait();
				
				same_image_check = true;
				break;
			}
		}
		
		if(!same_image_check) {
			
			Image image_preview_fx = SwingFXUtils.toFXImage(current_image.getProcessor().getBufferedImage(), null);
			
			ip_array.add(current_image);
			
			iv_array.get(ip_array.size() - 1).setImage(image_preview_fx);
			Tooltip.install(iv_array.get(ip_array.size() - 1), new Tooltip(current_image.getTitle().toString()));
			
			file_openSuccessfullMessage(current_image.getTitle());
			
		}
	}
	
	@FXML
	public void file_save() {
		
		Integer id = Integer.valueOf(iv_current_image.getAccessibleText());
		
		String path = ip_array.get(id).getOriginalFileInfo().directory + ip_array.get(id).getTitle();
		
		IJ.save(ip_array.get(id), path);
		
		setCurrentImage(id);
		
		Alert alert = new Alert(AlertType.INFORMATION, "Image" + " " + ip_array.get(id).getTitle() + " " + "saved!", 
				ButtonType.OK);
			
		alert.setTitle("Alert!");
		alert.showAndWait();
	}
	
	@FXML
	public void file_saveAll() {
		
		Integer id = Integer.valueOf(iv_current_image.getAccessibleText());
		
		for(int i = 0; i < ip_array.size(); i++) {
			
			String path = ip_array.get(i).getOriginalFileInfo().directory + ip_array.get(i).getTitle();
			
			IJ.save(ip_array.get(i), path);
		}
		
		setCurrentImage(id);
		
		Alert alert = new Alert(AlertType.INFORMATION, "All Images" + " " + "are" + " " + "saved!", 
				ButtonType.OK);
			
		alert.setTitle("Alert!");
		alert.showAndWait();
	}
	
	@FXML
	public void file_saveAs() {
		
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
			
			Integer id = Integer.parseInt(iv_current_image.getAccessibleText());
			
			setCurrentImage(id);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void file_quit() {
		
		Alert alert = new Alert(AlertType.WARNING, "Do you want to close program? Any unsaved changes will be lost!", 
				ButtonType.NO, ButtonType.YES);
		alert.setWidth(600);
		alert.setTitle("Warning!");
		alert.showAndWait().ifPresent(type -> {
	
		if(type == ButtonType.YES) {
			Platform.exit();	
		}
		});
	}
	
	private void onImageOpened() {
		
		analyze_particles.setDisable(false);
		file_menuItem_closeImage.setDisable(false);
		file_menuItem_closeAllImage.setDisable(false);
		file_menuItem_saveImage.setDisable(false);
		file_menuItem_saveAllImage.setDisable(false);
		file_menuItem_saveAsImage.setDisable(false);
		image_menuItem_brightness_contrast.setDisable(false);
		image_menuItem_convert_type.setDisable(false);
		image_menu_threshold.setDisable(false);
		process_menuItem_filters.setDisable(false);
	    process_menuItem_shadow.setDisable(false);
		process_menu_noise.setDisable(false);
		process_menu_others.setDisable(false);
		
		vbox_current_image.setDisable(false);
		vbox_current_image.setVisible(true);
	}
	
	public void onImageClosed() {
		
		analyze_particles.setDisable(true);
		file_menuItem_closeImage.setDisable(true);
		file_menuItem_saveImage.setDisable(true);
		file_menuItem_saveAsImage.setDisable(true);
		image_menuItem_brightness_contrast.setDisable(true);
		image_menuItem_convert_type.setDisable(true);
		image_menu_threshold.setDisable(true);
		process_menuItem_filters.setDisable(true);
	    process_menuItem_shadow.setDisable(true);
		process_menu_noise.setDisable(true);
		process_menu_others.setDisable(true);
		
		vbox_current_image.setDisable(true);
		vbox_current_image.setVisible(false);
		
		file_menuItem_closeAllImage.setDisable(true);
		file_menuItem_saveAllImage.setDisable(true);
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
				setImageProcessor(controller.getImageProcessor());
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
				setImagePlus(controller.getImagePlus());
				
				Integer id = Integer.parseInt(iv_current_image.getAccessibleText());
				
				setCurrentImage(id);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void image_threshold_adjust() {
		
		if(current_image.getType() != ImagePlus.GRAY8) {
			image_threshold_alert();
		}
		else {
			image_threshold_adjust_controller();
		}
	}
	
	private void image_threshold_adjust_controller() {
		
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
				setImageProcessor(controller.getImageProcessor());
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void image_threshold_auto() {
		
		if(current_image.getType() != ImagePlus.GRAY8) {
			image_threshold_alert();
		}
		else {
			image_threshold_auto_controller();
		}
	}
	
	private void image_threshold_auto_controller() {
		
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
				setImageProcessor(controller.getImageProcessor());
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void image_threshold_auto_instant() {
		
		if(current_image.getType() != ImagePlus.GRAY8) {
			image_threshold_alert();
		}
		else {
			ImagePlus image_backup = new ImagePlus();
			image_backup.setImage(current_image.getImage());
		
			image_backup.getProcessor().autoThreshold();
		
			setImageProcessor(image_backup.getProcessor());
		}
	}
	
	private void image_threshold_alert() {
		
		Alert alert = new Alert(AlertType.WARNING, "An 8-bit image is required!", ButtonType.OK);
		alert.setTitle("Threshold Warning!");
		alert.showAndWait();
	}

	@FXML
	public void process_find_edges() {
		
		ImagePlus image_backup = new ImagePlus();
		image_backup.setImage(current_image.getImage());
	
		image_backup.getProcessor().findEdges();
	
		setImageProcessor(image_backup.getProcessor());
	}
	
	@FXML
	public void process_invert() {
		
		ImagePlus image_backup = new ImagePlus();
		image_backup.setImage(current_image.getImage());
	
		image_backup.getProcessor().invert();
	
		setImageProcessor(image_backup.getProcessor());
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
				setImageProcessor(controller.getImageProcessor());
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
				setImageProcessor(controller.getImageProcessor());
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
	
		setImageProcessor(image_backup.getProcessor());
	}
	
	@FXML
	public void process_smooth() {
		
		ImagePlus image_backup = new ImagePlus();
		image_backup.setImage(current_image.getImage());
	
		image_backup.getProcessor().smooth();
	
		setImageProcessor(image_backup.getProcessor());
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
				setImageProcessor(controller.getImageProcessor());
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
	
	@FXML
	public void show_image() {
		current_image.show();
	}
	
	/*Messages*/
	
	private void file_openSuccessfullMessage(String imageTitle) {
		textMessage.setText(imageTitle + " " + "was successfully opened!");
	}
	
	private void file_closeSuccessfullMessage(String imageTitle) {
		textMessage.setText(imageTitle + " " + "was successfully closed!");
	}
	
	@FXML
	public void iv_array_1_setCurrentImage_OnClick() {
		setCurrentImage(Integer.parseInt(iv_1.getAccessibleText()));
	}
	
	@FXML
	public void iv_array_2_setCurrentImage_OnClick() {
		setCurrentImage(Integer.parseInt(iv_2.getAccessibleText()));
	}
	
	@FXML
	public void iv_array_3_setCurrentImage_OnClick() {
		setCurrentImage(Integer.parseInt(iv_3.getAccessibleText()));
	}
	
	@FXML
	public void iv_array_4_setCurrentImage_OnClick() {
		setCurrentImage(Integer.parseInt(iv_4.getAccessibleText()));
	}
	
	@FXML
	public void iv_array_5_setCurrentImage_OnClick() {
		setCurrentImage(Integer.parseInt(iv_5.getAccessibleText()));
	}
	
	@FXML
	public void iv_array_6_setCurrentImage_OnClick() {
		setCurrentImage(Integer.parseInt(iv_6.getAccessibleText()));
	}
	
	@FXML
	public void iv_array_7_setCurrentImage_OnClick() {
		setCurrentImage(Integer.parseInt(iv_7.getAccessibleText()));
	}
	
	@FXML
	public void iv_array_8_setCurrentImage_OnClick() {
		setCurrentImage(Integer.parseInt(iv_8.getAccessibleText()));
	}
	
	@FXML
	public void iv_array_9_setCurrentImage_OnClick() {
		setCurrentImage(Integer.parseInt(iv_9.getAccessibleText()));
	}
	
	private void setCurrentImage(Integer id) {
		
		if(id < ip_array.size()) {
			current_image = new ImagePlus();
			current_image = ip_array.get(id);
			
			label_current_image.setText((ip_array.get(id).getTitle()));
			
			String resolution = String.valueOf(ip_array.get(id).getWidth()) + "x" + String.valueOf(ip_array.get(id).getHeight());
			
			String type = null;
			
			int image_type = ip_array.get(id).getType();
			
			if(image_type == 0) {
				type = "Gray 8-bit";
			}
			else if(image_type == 1) {
				type = "Gray 16-bit";
			}
			else if(image_type == 2) {
				type = "Gray 32-bit";
			}
			else if(image_type == 3) {
				type = "Color 256-bit";
			}
			else if(image_type == 4) {
				type = "RGB";
			}
			
			label_current_image_status.setText(resolution + "; " + type);
			
			Image image_preview_fx = SwingFXUtils.toFXImage(ip_array.get(id).getBufferedImage(), null);
			iv_current_image.setImage(image_preview_fx);
			iv_current_image.setAccessibleText(String.valueOf(id));
			
			onImageOpened();
		}
	}
	
	private void setImageProcessor(ImageProcessor ip) {
		
		Integer id = Integer.parseInt(iv_current_image.getAccessibleText());
		
		current_image.setProcessor(ip);
		ip_array.get(id).setProcessor(ip);
		
		Image image_preview_fx1 = SwingFXUtils.toFXImage(current_image.getBufferedImage(), null);
		iv_current_image.setImage(image_preview_fx1);
	
		Image image_preview_fx2 = SwingFXUtils.toFXImage(ip_array.get(id).getBufferedImage(), null);
		iv_array.get(id).setImage(image_preview_fx2);
	}
	
	private void setImagePlus(ImagePlus ip) {
		
		Integer id = Integer.parseInt(iv_current_image.getAccessibleText());
		
		current_image.setImage(ip);
		ip_array.get(id).setImage(ip);
		
		Image image_preview_fx1 = SwingFXUtils.toFXImage(current_image.getBufferedImage(), null);
		iv_current_image.setImage(image_preview_fx1);
	
		Image image_preview_fx2 = SwingFXUtils.toFXImage(ip_array.get(id).getBufferedImage(), null);
		iv_array.get(id).setImage(image_preview_fx2);
	}
}
