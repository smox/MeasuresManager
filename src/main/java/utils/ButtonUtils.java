package utils;

import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;
import java.util.Optional;

public class ButtonUtils {


    public static void addIconToButton(Button button, String pathToIcon) {
        Image imageDelete = new Image(Objects.requireNonNull(ButtonUtils.class.getResourceAsStream(pathToIcon)));
        ImageView imageView = new ImageView(imageDelete);
        imageView.setFitHeight(16);
        imageView.setFitWidth(16);
        button.setGraphic(imageView);
    }

    public static boolean isButton(Optional<ButtonType> button, ButtonType buttonToCompare) {
        return button.isPresent() && button.get().getButtonData().equals(buttonToCompare.getButtonData());
    }
}
