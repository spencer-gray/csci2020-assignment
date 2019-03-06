// Spencer Gray

// Assignment - Question 3

import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.Random;

public class DraggingCircle extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{

        Pane pane = new Pane();

        // Creating Outer Circle
        Circle circ = new Circle(250, 250, 100, Color.TRANSPARENT);
        circ.setStroke(Color.BLACK);

        pane.getChildren().add(circ);

        // Initializing Points
        Circle points[] = new Circle[3];

        // Initializing Joining Lines
        final Line lines[] = new Line[3];

        // Initializing the Angles
        Text angles[] = new Text[3];

        // update the lines
        lines[0] = new Line();
        lines[1] = new Line();
        lines[2] = new Line();


        Random temp = new Random();
        // Random number for y multiplier to handle both axis
        int rand_multiplier = temp.nextInt(2);
        if (rand_multiplier == 0){
            rand_multiplier = -1;
        }

        int x_start = temp.nextInt(201) - 100;

        double y_point = Math.sqrt(Math.pow(100, 2) - Math.pow(x_start, 2)) * rand_multiplier;

        Circle pt1 = new Circle(5);
        pt1.setCenterX(x_start + 250);
        pt1.setCenterY(y_point + 250);

        // Create 3 Initial Points
        int y_multiplier = 1;
        double x, y;
        for (int i = 0; i < 3; i++) {
            y_multiplier = temp.nextInt(2);
            if (y_multiplier == 0) {
                y_multiplier = -1;
            }

            x = temp.nextInt(201) - 100;

            y = Math.sqrt(Math.pow(100, 2) - Math.pow(x, 2)) * y_multiplier;
            points[i] = new Circle(x+250, y+250, 5);
            points[i].setFill(Color.RED);
            points[i].setStroke(Color.BLACK);
            points[i].setCursor(Cursor.HAND);
            final int index = i;
            points[i].setOnMouseDragged(e ->
            {
                double radValue = Math.atan2(e.getY() - circ.getCenterY(), e.getX() - circ.getCenterX());
                double x_updated = circ.getCenterX() + circ.getRadius() * Math.cos(radValue);
                double y_updated = circ.getCenterY() + circ.getRadius() * Math.sin(radValue);
                points[index].setCenterX(x_updated);
                points[index].setCenterY(y_updated);

                // Update the lines
                updateLines(lines, points);

                // Calculating new angles
                updateAngles(angles, lines, points);
            });
        }

        updateLines(lines, points);

        // Initializing angles
        angles[0] = new Text();
        angles[1] = new Text();
        angles[2] = new Text();

        // Calculating angles
        updateAngles(angles, lines, points);

        // Adding Children to pane
        pane.getChildren().addAll(points);
        pane.getChildren().addAll(lines);
        pane.getChildren().addAll(angles[0], angles[1], angles[2]);

        primaryStage.setTitle("Question_3");
        primaryStage.setScene(new Scene(pane, 500, 500));
        primaryStage.show();
    }

    private void updateLines(Line[] lines, Circle[] endpoint) {
        lines[0].setStartX(endpoint[0].getCenterX());
        lines[0].setStartY(endpoint[0].getCenterY());
        lines[0].setEndX(endpoint[1].getCenterX());
        lines[0].setEndY(endpoint[1].getCenterY());

        lines[1].setStartX(endpoint[1].getCenterX());
        lines[1].setStartY(endpoint[1].getCenterY());
        lines[1].setEndX(endpoint[2].getCenterX());
        lines[1].setEndY(endpoint[2].getCenterY());

        lines[2].setStartX(endpoint[2].getCenterX());
        lines[2].setStartY(endpoint[2].getCenterY());
        lines[2].setEndX(endpoint[0].getCenterX());
        lines[2].setEndY(endpoint[0].getCenterY());
    }

    // Calculating new angles of the triangle
    private void updateAngles(Text[] angles, Line[] lines, Circle[] points) {
        double a = Math.round(Math.toDegrees(Math.acos(((lineLength(lines[2]) * lineLength(lines[2]))
                - (lineLength(lines[0]) * lineLength(lines[0])) - (lineLength(lines[1])
                * lineLength(lines[1]))) / (-2 * lineLength(lines[0]) * lineLength(lines[1])))));

        double b = Math.round(Math.toDegrees(Math.acos(((lineLength(lines[0]) * lineLength(lines[0]))
                - (lineLength(lines[2]) * lineLength(lines[2])) - (lineLength(lines[1])
                * lineLength(lines[1]))) / (-2 * lineLength(lines[2]) * lineLength(lines[1])))));

        double c = Math.round(Math.toDegrees(Math.acos(((lineLength(lines[1]) * lineLength(lines[1]))
                - (lineLength(lines[0]) * lineLength(lines[0])) - (lineLength(lines[2])
                * lineLength(lines[2]))) / (-2 * lineLength(lines[0]) * lineLength(lines[2])))));

        angles[0].setText(Integer.toString((int)c));
        angles[1].setText(Integer.toString((int)a));
        angles[2].setText(Integer.toString((int)b));

        // Displaying angles inside circle
        double output[] = new double[2];
        for (int i = 0; i < points.length; i++) {
            if (points[i].getCenterX() <= 250) {
                output[0] = points[i].getCenterX() + 20;
            }
            if (points[i].getCenterX() > 250) {
                output[0] = points[i].getCenterX() - 20;
            }
            if (points[i].getCenterY() <= 250) {
                output[1] = points[i].getCenterY() + 10;
            }
            if (points[i].getCenterY() > 250) {
                output[1] = points[i].getCenterY() - 20;
            }

            angles[i].setStyle("-fx-font: 10 arial");
            angles[i].setX(output[0]);
            angles[i].setY(output[1]);
        }
    }

    // Finds the length of a line when updating angle values
    private double lineLength(Line line) {
        double x1 = line.getStartX();
        double y1 = line.getStartY();
        double x2 = line.getEndX();
        double y2 = line.getEndY();
        return Math.sqrt((x1-x2)*(x1-x2) + (y1-y2) * (y1-y2));
    }


    public static void main(String[] args) {
        launch(args);
    }

}