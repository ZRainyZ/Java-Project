package Metal_Cooler_Box;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;


public class MainMenu implements Initializable {


    @FXML
    private ImageView fridgeImage,backgroundImage;

    @FXML
    private Button openBoxButton;


    @FXML
    void handleCreatorOnAction(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Creator.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("My Information");
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception e){
            System.out.println("Can not load My Information window");
        }
    }

    @FXML
    void handleOpenBoxButtonOnAction(ActionEvent event){
        try {
            Parent fxmlLoader = FXMLLoader.load(getClass().getResource("ChooseSlot.fxml"));
            Scene scene = new Scene(fxmlLoader);
            Stage stage = (Stage) openBoxButton.getScene().getWindow();
            stage.setTitle("Choose Slot");
            stage.setScene(scene);

        } catch (Exception e){
            System.out.println("Can not load Choose Slot window");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        fridgeImage.setImage(new Image("images/fridge.png"));
        backgroundImage.setImage(new Image("images/pastel.jpg"));

        final Tooltip tooltipOpenButton = new Tooltip();
        tooltipOpenButton.setText("Click me to open box");
        openBoxButton.setTooltip(tooltipOpenButton);


    }

}
