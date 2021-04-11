package Metal_Cooler_Box;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import models.Food;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;


public class NormalSlotController implements Initializable {


    @FXML
    private ImageView normalSlotImage1,normalSlotImage2,normalSlotImage3,normalSlotImage4,normalSlotImage5,normalSlotImage6;
    @FXML
    private ImageView normalSlot1,normalSlot2,normalSlot3,normalSlot4,normalSlot5,normalSlot6;

    @FXML
    Label slot1Detail,slot2Detail,slot3Detail,slot4Detail,slot5Detail,slot6Detail;

    @FXML
    private Button goBackButton,addFoodButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        normalSlotImage1.setImage(new Image("images/slot.jpg"));
        normalSlotImage2.setImage(new Image("images/slot.jpg"));
        normalSlotImage3.setImage(new Image("images/slot.jpg"));
        normalSlotImage4.setImage(new Image("images/slot.jpg"));
        normalSlotImage5.setImage(new Image("images/slot.jpg"));
        normalSlotImage6.setImage(new Image("images/slot.jpg"));
        show();
    }

    private void show(){
        ImageView[] allimage = new ImageView[6];
        allimage[0] = normalSlot1;
        allimage[1] = normalSlot2;
        allimage[2] = normalSlot3;
        allimage[3] = normalSlot4;
        allimage[4] = normalSlot5;
        allimage[5] = normalSlot6;
        Label[] allLabel = new Label[]{slot1Detail,slot2Detail,slot3Detail,slot4Detail,slot5Detail,slot6Detail};
        int i=0;
        for (ImageView imageView : allimage){
            if (!(RunProgram.refrigerator.getSortedFoodFromSlot(imageView.getId()).size() == 0)){
                setImage(imageView,RunProgram.refrigerator.getSortedFoodFromSlot(imageView.getId()).get(0));
                setDetail(allLabel[i],RunProgram.refrigerator.getSortedFoodFromSlot(imageView.getId()).get(0),RunProgram.refrigerator.getSortedFoodFromSlot(imageView.getId()).size());
            }else{
                imageView.setImage(null);
                allLabel[i].setText("");
            }
            i++;
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
        }else if(type.equals("Fresh Food")){
            imageView.setImage(new Image("images/fresh food.jpg"));
        }else if(type.equals("Clean Food")){
            imageView.setImage(new Image("images/cleanfood.jpg"));
        }
    }

    private void setDetail(Label label,Food food,int total){
        String detail = "Food name: " + food.getFoodName()+"\n" +
                "Food type: " + food.getFoodType()+"\n" +
                "Food Expire Day: " + food.getExpire()+ "\n" +
                "There are " + total + " item(s) in this slot" + "\n" +
                "Food in slot for " + food.dayInRefridgerator() + " day(s)\n";
        if (food.isExpired()){
            detail += "Expired for " + food.dayExpired() + " day(s)";
            label.setTextFill(Color.web("#f076a3"));
        }else{
            label.setTextFill(Color.web("#000000"));
        }
        label.setText(detail);
    }

    @FXML
    void handleGoBackSlotOnAction(ActionEvent event){
        try {
            Parent fxmlLoader = FXMLLoader.load(getClass().getResource("ChooseSlot.fxml"));
            Scene scene = new Scene(fxmlLoader);
            Stage stage = (Stage) goBackButton.getScene().getWindow();
            stage.setTitle("Choose Slot");
            stage.setScene(scene);

        } catch (Exception e){
            System.out.println("Can not load Choose Slot window");
        }

    }

    @FXML
    void handleAddFoodOnAction(ActionEvent event) {
        try {
            Parent fxmlLoader = FXMLLoader.load(getClass().getResource("AddFoodNormalSlot.fxml"));
            Scene scene = new Scene(fxmlLoader);
            Stage stage = (Stage) addFoodButton.getScene().getWindow();
            stage.setTitle("Add Food");
            stage.setScene(scene);

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void handleViewFoodBtn(ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewFood.fxml"));
        Stage stage = (Stage) b.getScene().getWindow();
        Scene scene = new Scene(loader.load());
        ViewFoodController viewFood = loader.getController();
        viewFood.set("normal",b.getId());
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
        options.add("Normal Slot 1");
        options.add("Normal Slot 2");
        options.add("Normal Slot 3");
        options.add("Normal Slot 4");
        options.add("Normal Slot 5");
        options.add("Normal Slot 6");
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
