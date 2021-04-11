package Metal_Cooler_Box;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import models.Food;

import java.io.IOException;
import java.time.LocalDate;

public class AddFoodNormalSlot{

    ObservableList<String> addToSlotList = FXCollections.observableArrayList("Normal Slot 1","Normal Slot 2","Normal Slot 3","Normal Slot 4","Normal Slot 5","Normal Slot 6");
    ObservableList<String> addFoodTypeList = FXCollections.observableArrayList("Dessert","Fresh Food","Beverage", "Snack", "Delicatessen","Clean Food");

    @FXML
    TextField foodNameInput,foodQuantifyingInput,foodAmountInput;

    @FXML
    DatePicker foodExpireInput;

    @FXML
    private ComboBox addToSlot, addFoodType;

    @FXML
    private Button addFoodButton,goBackButton;

    @FXML
    private ImageView foodBackGroundImage;

    @FXML Label errorMessage;

    @FXML
    private void initialize() {

        addToSlot.setItems(addToSlotList);
        addFoodType.setItems(addFoodTypeList);
        foodBackGroundImage.setImage(new Image("images/foodbackground.jpg"));

    }

    @FXML
    public void handleAddOnAction(ActionEvent event) throws IOException {
        if (!checkInput(foodNameInput,addFoodType,foodQuantifyingInput,foodAmountInput,foodExpireInput.getValue(),addToSlot)){
            return;
        }
        int amount = Integer.parseInt(foodAmountInput.getText());
        String name = foodNameInput.getText();
        String type = addFoodType.getSelectionModel().getSelectedItem().toString();
        String quantifying = foodQuantifyingInput.getText();
        String expiredate = foodExpireInput.getValue().toString();
        String slot = addToSlot.getSelectionModel().getSelectedItem().toString();
        Food food = new Food(name,type,quantifying,expiredate,slot);
        if (!RunProgram.refrigerator.addFood(food)){
            Alert alert = new Alert(Alert.AlertType.WARNING,"Invalid food type");
            alert.showAndWait();
            addFoodType.getSelectionModel().clearSelection();
            return;
        }
        for (int i=1; i<amount; i++){
            food = new Food(name,type,quantifying,expiredate,slot);
            RunProgram.refrigerator.addFood(food);
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION,"Add Complete");
        alert.showAndWait();

        Parent fxmlLoader = FXMLLoader.load(getClass().getResource("NormalSlot.fxml"));
        Scene scene = new Scene(fxmlLoader);
        Stage stage = (Stage) addFoodButton.getScene().getWindow();
        stage.setTitle("Normal Slot");
        stage.setScene(scene);

    }

    private boolean checkInput(TextField name, ComboBox<String> type, TextField quantifying, TextField amount, LocalDate expire,ComboBox<String> slot){
        if (name.getText().equals("") || type.getSelectionModel().getSelectedItem() == null || quantifying.getText().equals("")
                || amount.getText().equals("") || expire == null || slot.getSelectionModel().getSelectedItem() == null){
            errorMessage.setText("กรุณาใส่ข้อมูลให้ครบถ้วน");
            errorMessage.setVisible(true);
            return false;
        }else if (expire.isBefore(LocalDate.now())){
            errorMessage.setText("ExpireDate Invalid");
            errorMessage.setVisible(true);
            return false;
        }
        try{
            Integer.parseInt(amount.getText());
        }catch (NumberFormatException e){
            errorMessage.setText("Amount field cannot contain Character");
            errorMessage.setVisible(true);
            return false;
        }
        return true;
    }

    @FXML
    public void handleGoBackOnAction(ActionEvent event){
        try {
            Parent fxmlLoader = FXMLLoader.load(getClass().getResource("NormalSlot.fxml"));
            Scene scene = new Scene(fxmlLoader);
            Stage stage = (Stage) goBackButton.getScene().getWindow();
            stage.setTitle("Normal Slot");
            stage.setScene(scene);

        } catch (Exception e){
            System.out.println("Can not load Normal Slot window");
        }

    }

    @FXML
    public void handleClearBtn(ActionEvent event){
        foodNameInput.clear();
        addFoodType.getSelectionModel().clearSelection();
        foodQuantifyingInput.clear();
        foodAmountInput.clear();
        foodExpireInput.setValue(null);
        addToSlot.getSelectionModel().clearSelection();
    }


}