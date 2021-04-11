package Metal_Cooler_Box;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import models.Food;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class FreezeSlotController implements Initializable {

    @FXML
    ImageView freezeSlot1,freezeSlot2,freezeSlot1Back,freezeSlot2Back;


    @FXML
    private Button goBackButton,addFoodButton;

    @FXML private Label slot1Detail,slot2Detail;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        freezeSlot1Back.setImage(new Image("images/slot.jpg"));
        freezeSlot2Back.setImage(new Image("images/slot.jpg"));
        show();
    }

    private void show(){
        if (!(RunProgram.refrigerator.getSortedFoodFromSlot("freezeSlot1").size() == 0)){
            setImage(freezeSlot1,RunProgram.refrigerator.getSortedFoodFromSlot("freezeSlot1").get(0));
            setDetail(slot1Detail,RunProgram.refrigerator.getSortedFoodFromSlot("freezeSlot1").get(0),RunProgram.refrigerator.getSortedFoodFromSlot("freezeSlot1").size());
        }else{
            freezeSlot1.setImage(null);
            slot1Detail.setText("");
        }
        if (!(RunProgram.refrigerator.getSortedFoodFromSlot("freezeSlot2").size() == 0)){
            setImage(freezeSlot2,RunProgram.refrigerator.getSortedFoodFromSlot("freezeSlot2").get(0));
            setDetail(slot2Detail,RunProgram.refrigerator.getSortedFoodFromSlot("freezeSlot2").get(0),RunProgram.refrigerator.getSortedFoodFromSlot("freezeSlot2").size());
        }else {
            freezeSlot2.setImage(null);
            slot2Detail.setText("");
        }
    }

    private void setImage(ImageView imageView, Food food){
        String type = food.getFoodType();
        if (type.equals("Beverage")){
            imageView.setImage(new Image("images/beverage.jpg"));
        }else if(type.equals("Dessert")){
            imageView.setImage(new Image("images/dessert.jpg"));
        }else if(type.equals("Snack")){
            imageView.setImage(new Image("images/snack.jpg"));
        }else if(type.equals("Delicatessen")){
            imageView.setImage(new Image("images/delicatessen.jpg"));
        }
    }

    private void setDetail(Label label,Food food,int total){
        String detail = "Food name: " + food.getFoodName()+"\n" +
                        "Food type: " + food.getFoodType()+"\n" +
                        "Food Expire Day: " + food.getExpire()+ "\n" +
                        "There are " + total + " item(s) in this slot" + "\n" +
                        "Food in slot for " + food.dayInRefridgerator() + " day(s)\n";
        if (food.isExpired()){
            detail += "\nExpired for " + food.dayExpired() + " day(s)";
            label.setTextFill(Color.web("#f076a3"));
        }else{
            label.setTextFill(Color.web("#000000"));
        }
        label.setText(detail);
    }

    @FXML
    void handleGoBackToSlotOnAction(ActionEvent event){
        try {
            Parent fxmlLoader = FXMLLoader.load(getClass().getResource("ChooseSlot.fxml"));
            Scene scene = new Scene(fxmlLoader);
            Stage stage = (Stage) goBackButton.getScene().getWindow();
            stage.setTitle("Choose Slot");
            stage.setScene(scene);

        } catch (Exception e){
            System.out.println("Can not load Freeze Slot window");
        }
    }

    @FXML
    public void handleAddFoodOnAction(ActionEvent event){
        try {
            Parent fxmlLoader = FXMLLoader.load(getClass().getResource("AddFoodFreezeSlot.fxml"));
            Scene scene = new Scene(fxmlLoader);
            Stage stage = (Stage) addFoodButton.getScene().getWindow();
            stage.setTitle("Add Food");
            stage.setScene(scene);

        } catch (Exception e){
            System.out.println("Can not load Add Food window");
        }
    }

    @FXML
    public void handleViewFoodBtn(ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewFood.fxml"));
        Stage stage = (Stage) b.getScene().getWindow();
        Scene scene = new Scene(loader.load());
        ViewFoodController viewFood = loader.getController();
        viewFood.set("freeze",b.getId());
        stage.setTitle("view food");
        stage.setScene(scene);
    }

    @FXML
    public void handleRemoveFoodBtn(ActionEvent event){
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Remove food form");
        dialog.setHeaderText("Input your information about remove food");
        Label label1 = new Label("Slot: ");
        Label label2 = new Label("amount: ");
        ObservableList<String> options = FXCollections.observableArrayList();
        options.add("Freeze Slot 1");
        options.add("Freeze Slot 2");
        TextField textField = new TextField("1");
        ComboBox<String> comboBox = new ComboBox<>(options);
        comboBox.getSelectionModel().select(0);
        GridPane gridPane = new GridPane();
        gridPane.add(label1,1,1);
        gridPane.add(comboBox,2,1);
        gridPane.add(label2,1,2);
        gridPane.add(textField,2,2);
        dialog.getDialogPane().setContent(gridPane);
        ButtonType buttonTypeRemove = new ButtonType("Remove");
        dialog.getDialogPane().getButtonTypes().addAll(buttonTypeRemove);
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            String slot = comboBox.getSelectionModel().getSelectedItem();
            String amount = textField.getText();
            if (Integer.parseInt(amount.trim()) <= 0){
                Alert alert = new Alert(Alert.AlertType.WARNING,"number to remove must me greater than 0");
                alert.showAndWait();
                return;
            }
            if (RunProgram.refrigerator.removeFoodFromSlot(slot,Integer.parseInt(amount))){
                show();
                Alert alert = new Alert(Alert.AlertType.INFORMATION,"Remove success");
                alert.showAndWait();
            }else{
                Alert alert = new Alert(Alert.AlertType.WARNING,"cannot remove " +Integer.parseInt(amount.trim()) + " item(s)");
                alert.showAndWait();
            }

        }
    }
}
