package Metal_Cooler_Box;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.Food;
import models.Refrigerator;

import java.io.*;


public class RunProgram extends Application {

    public static Refrigerator refrigerator;

    @Override
    public void start(Stage primaryStage) throws Exception{
        refrigerator = new Refrigerator();
        File file = new File("RefrigeratorData.csv");
        if (!file.exists()){
            file.createNewFile();
        }
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String line = "";
        String[] data;
        while ((line = bufferedReader.readLine()) != null){
            data = line.split(",");
            Food food = new Food(data[0],data[1],data[2],data[3],data[4],data[5]);
            refrigerator.addFood(food);
        }
        bufferedReader.close();

        Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        primaryStage.setTitle("Metal_Cooler_Box");
        primaryStage.setScene(new Scene(root, 740, 640));
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        File file = new File("RefrigeratorData.csv");
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
        for (Food food : refrigerator.getAllFood()){
            bufferedWriter.write(food.toString());
            bufferedWriter.newLine();
        }
        bufferedWriter.close();
        super.stop();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
