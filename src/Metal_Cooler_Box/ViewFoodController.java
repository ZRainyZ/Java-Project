package Metal_Cooler_Box;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import models.Food;
import models.FoodBean;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ViewFoodController implements Initializable {
    private String from;
    private String slotNow;
    ObservableList<FoodBean> observableList = FXCollections.observableArrayList();

    @FXML
    TableView table;

    @FXML
    TableColumn<FoodBean,String> name,type,quantifying,dayin,exp,expired;

    @FXML
    Button back;

    @FXML
    ImageView background;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                background.setImage(new Image("images/pastel.jpg"));
                name.setCellValueFactory(new PropertyValueFactory<>("name"));
                type.setCellValueFactory(new PropertyValueFactory<>("type"));
                quantifying.setCellValueFactory(new PropertyValueFactory<>("Quantifying"));
                dayin.setCellValueFactory(new PropertyValueFactory<>("dayin"));
                exp.setCellValueFactory(new PropertyValueFactory<>("exp"));
                expired.setCellValueFactory(new PropertyValueFactory<>("expired"));
                List<Food> allFood;
                String sentTo;
                if (slotNow.charAt(0) == 'F'){
                    sentTo = "freezeSlot" + slotNow.substring(10,11);
                }else {
                    sentTo = "NormalSlot" + slotNow.substring(10,11);
                }
                for (Food food : RunProgram.refrigerator.getSortedFoodFromSlot(sentTo)){
                    observableList.add(new FoodBean(food));
                }
                table.setItems(observableList);
            }
        });
    }

    public void handleBackBtn(ActionEvent event) throws IOException {
        Parent fxmlLoader;
        if (from.equals("freeze")) {
            fxmlLoader = FXMLLoader.load(getClass().getResource("FreezeSlot.fxml"));
        }else{
            fxmlLoader = FXMLLoader.load(getClass().getResource("NormalSlot.fxml"));
        }
        Scene scene = new Scene(fxmlLoader);
        Stage stage = (Stage) back.getScene().getWindow();
        if (from.equals("freeze")) {
            stage.setTitle("Freeze Slot");
        }else{
            stage.setTitle("Normal Slot");
        }
        stage.setScene(scene);
    }

    public void set(String from,String slotNow){
        this.from = from;
        this.slotNow = slotNow;
    }
}
