package utils;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class ButtonUtils {


    public static void addIconToButton(Button btnDelete, String pathToIcon) {
        Image imageDelete = new Image(Objects.requireNonNull(ButtonUtils.class.getResourceAsStream(pathToIcon)));
        ImageView imageView = new ImageView(imageDelete);
        imageView.setFitHeight(16);
        imageView.setFitWidth(16);
        btnDelete.setGraphic(imageView);
    }

}
