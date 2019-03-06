// Spencer Gray

// Assignment - Question 1

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.util.Random;
import javafx.geometry.Insets;

public class DisplayingThreeCards extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        HBox hbox = new HBox(5);
        hbox.setPadding(new Insets(2));

        // Generating random numbers used for selecting cards
        Random rand = new Random();
        int  rand1 = rand.nextInt(54);
        rand1 += 1;
        rand = new Random();
        int  rand2 = rand.nextInt(54);
        rand2 += 1;
        rand = new Random();
        int  rand3 = rand.nextInt(54);
        rand3 += 1;

        // First Image
        Image img1 = new Image("/Cards/" + Integer.toString(rand1) + ".png");
        ImageView image1 = new ImageView();
        image1.setImage(img1);
        image1.setFitWidth(90);
        image1.setFitHeight(150);
        hbox.getChildren().add(image1);

        // Second Image
        Image img2 = new Image("/Cards/" + Integer.toString(rand2) + ".png");
        ImageView image2 = new ImageView();
        image2.setImage(img2);
        image2.setFitWidth(90);
        image2.setFitHeight(150);
        hbox.getChildren().add(image2);

        // Third Image
        Image img3 = new Image("/Cards/" + Integer.toString(rand3) + ".png");
        ImageView image3 = new ImageView();
        image3.setImage(img3);
        image3.setFitWidth(90);
        image3.setFitHeight(150);
        hbox.getChildren().add(image3);

        // Centering HBox
        hbox.setAlignment(Pos.BASELINE_CENTER);

        // Generating Stage
        Scene scene = new Scene(hbox, 285, 155);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Question_1");
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
