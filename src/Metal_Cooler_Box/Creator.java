package Metal_Cooler_Box;


import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Creator {

    @FXML
    private ImageView creatorImage,backgroundImage;


    @FXML
    public void initialize() {

        creatorImage.setImage(new Image("images/user.jpg"));
        backgroundImage.setImage(new Image("images/pastel.jpg"));
    }

}
