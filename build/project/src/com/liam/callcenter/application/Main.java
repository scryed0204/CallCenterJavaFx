package com.liam.callcenter.application;

import com.liam.callcenter.test.CallCenterTester;
import com.liam.callcenter.util.TextAreaPrinter;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {

		primaryStage.setTitle("CallCenterTest");
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent t) {
				Platform.exit();
				System.exit(0);
			}
		});

		// Creating a GridPane container
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(5);
		grid.setHgap(5);

		// Defining the text field
		Label label1 = new Label("Call Counts:");
		TextField textField = new TextField();
		HBox hb = new HBox();
		hb.getChildren().addAll(label1, textField);
		hb.setSpacing(10);
		GridPane.setConstraints(hb, 0, 0);
		grid.getChildren().add(hb);

		// Defining the Submit button
		Button submit = new Button("TEST NOW");
		GridPane.setConstraints(submit, 0, 1);
		grid.getChildren().add(submit);

		// Defining the Text Area
		TextArea textArea = new TextArea();
		GridPane.setConstraints(textArea, 0, 2);
		grid.getChildren().add(textArea);

		TextAreaPrinter.init(textArea);

		submit.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				textArea.clear();
				if ((textField.getText() != null)) {
					try {
						int callCnt = Integer.parseInt(textField.getText());
						CallCenterTester.StartTest(callCnt);
					} catch (NumberFormatException nfe) {
						textArea.appendText("Please input an integer");
					}
				} else {
					textArea.appendText("Please input an integer");
				}
			}
		});

		primaryStage.setScene(new Scene(grid, 500, 500));
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
