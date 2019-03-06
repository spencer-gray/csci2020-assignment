// Spencer Gray

// Assignment - Question 2

import javafx.application.Application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class InvestmentValueCalculator extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(10, 10, 10, 10));

        pane.setHgap(10);
        pane.setVgap(1);

        // Getting Investment Amount
        Label invst_lbl = new Label("Investment Amount");
        invst_lbl.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        final TextField invst = new TextField();
        invst.setPromptText("Investment Amount");
        invst.getText();
        invst.setAlignment(Pos.CENTER_RIGHT);
        GridPane.setConstraints(invst_lbl, 0, 0);
        GridPane.setConstraints(invst, 1 , 0);
        pane.getChildren().add(invst_lbl);
        pane.getChildren().add(invst);

        // Getting Years
        Label years_lbl = new Label("Years");
        years_lbl.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        final TextField years = new TextField();
        years.setPromptText("Years");
        years.getText();
        years.setAlignment(Pos.CENTER_RIGHT);
        GridPane.setConstraints(years_lbl, 0, 1);
        GridPane.setConstraints(years, 1, 1);
        pane.getChildren().add(years_lbl);
        pane.getChildren().add(years);

        // Getting Annual Interest Amount
        Label annual_lbl = new Label("Annual Interest Rate");
        annual_lbl.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        final TextField rate = new TextField();
        rate.setPromptText("Annual Interest Rate");
        rate.getText();
        rate.setAlignment(Pos.CENTER_RIGHT);
        GridPane.setConstraints(annual_lbl, 0, 2);
        GridPane.setConstraints(rate, 1, 2);
        pane.getChildren().add(annual_lbl);
        pane.getChildren().add(rate);

        // Displaying Future Value
        Label future_lbl = new Label("Future value");
        future_lbl.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        final TextField future = new TextField();
        future.setEditable(false);
        future.setStyle("-fx-control-inner-background: #EEEFEE");

        future.setAlignment(Pos.CENTER_RIGHT);
        GridPane.setConstraints(future_lbl, 0, 3);
        GridPane.setConstraints(future, 1, 3);
        pane.getChildren().add(future_lbl);
        pane.getChildren().add(future);

        // Calculate Button
        Button submit_btn = new Button("Calculate");
        submit_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Converting string values to integers for calculation
                double investmentAmount = Double.parseDouble(invst.getText());
                double interestRate = Double.parseDouble(rate.getText()) / 100;
                double yearsInvestment = Double.parseDouble(years.getText());
                double futureValue = investmentAmount * Math.pow(1 + interestRate, yearsInvestment);
                String value = String.format("%.2f", futureValue);
                future.setText(value);

            }
        });
        GridPane.setConstraints(submit_btn, 1, 15);
        GridPane.setHalignment(submit_btn, HPos.RIGHT);

        pane.getChildren().add(submit_btn);

        // Generating Stage
        primaryStage.setTitle("Question_2");
        primaryStage.setScene(new Scene(pane, 340, 170));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
