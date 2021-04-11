package Metal_Cooler_Box;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.net.URL;
import java.util.ResourceBundle;


public class ChooseSlot implements Initializable {

    @FXML
    private ImageView freezeSlotImage,normalSlotImage;


    @FXML
    private Button freezeSlotButton,backButton,normalSlotButton;

    @FXML
    void handleFreezeSlotOnAction(ActionEvent event){
        try {
            Parent fxmlLoader = FXMLLoader.load(getClass().getResource("FreezeSlot.fxml"));
            Scene scene = new Scene(fxmlLoader);
            Stage stage = (Stage) freezeSlotButton.getScene().getWindow();
            stage.setTitle("Freeze Slot");
            stage.setScene(scene);

        } catch (Exception e){
            e.printStackTrace();
        }
    }


    @FXML
    void handleNormalSlotOnAction(ActionEvent event){
        try {
            Parent fxmlLoader = FXMLLoader.load(getClass().getResource("NormalSlot.fxml"));
            Scene scene = new Scene(fxmlLoader);
            Stage stage = (Stage) normalSlotButton.getScene().getWindow();
            stage.setTitle("Normal Slot");
            stage.setScene(scene);

        } catch (Exception e){
            e.printStackTrace();
        }
    }


    @FXML
    void handleBackOnAction(ActionEvent event){
        try {
            Parent fxmlLoader = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
            Scene scene = new Scene(fxmlLoader);
            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.setTitle("Metal_Cooler_Box");
            stage.setScene(scene);

        } catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        freezeSlotImage.setImage(new Image("images/freezeslot.png"));
        normalSlotImage.setImage(new Image("images/normalslot.png"));


        final Tooltip tooltipFreezeButton = new Tooltip();
        tooltipFreezeButton.setText("Click here to open Freeze Slot");
        freezeSlotButton.setTooltip(tooltipFreezeButton);

        final Tooltip tooltipNormalButton = new Tooltip();
        tooltipNormalButton.setText("Click here to open Normal Slot");
        normalSlotButton.setTooltip(tooltipNormalButton);

    }
}
