// Spencer Gray

// Assignment - Question 4

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;

public class Histogram extends Application {

    // Initializing max count -> helps check if output needs to be resized.
    private int max = 0;

    // Converts letter to alphabetical position
    private int letterToPosition(char c) {
        String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

        return alphabet.indexOf(c) % 26;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        BorderPane pane = new BorderPane();

        HBox hbox = new HBox();
        // Adding a border around the bottom hbox
        hbox.setStyle("-fx-border-style: solid inside;" + "-fx-border-width: 1;");

        // Label + TextField creation
        Label file_lbl = new Label("Filename");
        TextField path = new TextField("Enter full pathname...");
        path.setPrefWidth(345);

        // View Button
        Button view_btn = new Button("View");
        view_btn.setPrefWidth(50);
        view_btn.setDefaultButton(true);            // links it to enter-pressed action
        view_btn.setOnAction(e -> {
            // Try opening file
            try {
                // Initializing reader
                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path.getText()), "UTF8"));

                // Initializing array to store the different letter counts
                int[] letterCount = new int[26];
                String line;

                // filling letterCount with char counts in the file
                while ((line = br.readLine()) != null) {
                    for (int i = 0; i < line.length(); i++) {
                        if (Character.isLetter(line.charAt(i))) {
                            int position = letterToPosition(line.charAt(i));

                            // Adding to current chars count
                            letterCount[position]++;
                        }
                    }
                }
                // Closing input file
                br.close();

                // Finding max letterCount to adjust window accordingly
                for (int i = 0; i < letterCount.length; i++) {
                    if (letterCount[i] > max) {
                        max = letterCount[i];
                    }
                }

                // Checking if max count is greater then window height, if so we
                // will have to adjust rectangle sizes accordingly
                double countMultiplier = 1;
                if (max > 300) {
                    countMultiplier = 0.5;
                    while(true) {
                        if (max*countMultiplier > 300) {
                            countMultiplier = countMultiplier/2;
                        }
                        else {
                            break;
                        }
                    }
                }

                // Storing wanted nodes before clearing (bottom border and x-axis)
                Node node1 = pane.getChildren().get(0);
                Node node2 = pane.getChildren().get(1);
                Node node3 = pane.getChildren().get(2);

                // Deleting everything from scene
                pane.getChildren().clear();

                // Adding back the bottom border hbox and x-axis nodes
                pane.getChildren().addAll(node1, node2, node3);

                // making bar graph
                for (int i = 0; i < 26; i++) {
                    Rectangle rect = new Rectangle(10,letterCount[i]*countMultiplier, Color.WHITE);
                    rect.setStroke(Color.BLACK);
                    rect.setX((16*(i+1))-3);
                    rect.setY(275-(letterCount[i])*countMultiplier);
                    pane.getChildren().add(rect);
                }

            } catch (IOException f) {
                // File not found / Problems opening
                System.out.println("File cannot be read, verify your path");
            }

            //primaryStage.setHeight(350);

        });

        // adding nodes to bottom pane's hbox
        hbox.getChildren().addAll(file_lbl, path, view_btn);

        // Plotting x axis
        Line line = new Line(10, 275, 440, 275);
        Text x_axis = new Text("A  B  C  D  E  F  G  H  I   J  K  L  M  N  O  P  Q  R  S  T  U  V W  X  Y  Z");
        x_axis.setX(12);
        x_axis.setY(290);

        pane.getChildren().addAll(line, x_axis);

        pane.setBottom(hbox);

        // Creating Stage
        primaryStage.setTitle("Question 4");
        primaryStage.setScene(new Scene(pane, 453, 350));
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}


