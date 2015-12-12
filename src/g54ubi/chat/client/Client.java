package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import javafx.scene.layout.StackPane;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Client extends Application {
	public String line;
	private BufferedReader bufferedReader;
	private PrintWriter printWriter;
	Socket socket;
	int port = 9000;
	String serverAddress;
	TextArea textArea;

	@Override
	public void start(Stage primaryStage) throws Exception {
		StackPane root = new StackPane();
		HBox hbox = new HBox(30);
		VBox vbox = new VBox(10);
		Label textLabel = new Label("Enter Text: ");
		TextField textField = new TextField();
		textArea = new TextArea();
		textArea.setPrefWidth(400);
		VBox.setVgrow(textArea, Priority.ALWAYS);
		vbox.getChildren().addAll(textLabel, textField, textArea);
		hbox.setPadding(new Insets(20));
		hbox.getChildren().addAll(vbox);
		root.getChildren().add(hbox);
		Scene scene = new Scene(root, 600, 400);
		primaryStage.setTitle("G54SQMChatClient");
		primaryStage.setScene(scene);
		primaryStage.show();

		socket = new Socket("localhost", port);

		new Thread(new WriteThread(socket)).start();
		new Thread(new RecieveThread(socket)).start();

		textField.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				textField.setOnAction((event) -> {
					line = textField.getText();
					new Thread(new WriteThread(socket)).start();

					textArea.appendText("\nYou sent-- " + textField.getText());
					textField.clear();

				});

			}
		});

	}

	class RecieveThread implements Runnable {
		private Socket socket;

		public RecieveThread(Socket socket) {
			this.socket = socket;

		}

		public void run() {

			try {
				bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				while (true)
					textArea.appendText("\nServer response-- " + bufferedReader.readLine());
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	class WriteThread implements Runnable {
		private Socket socket;

		public WriteThread(Socket socket) {
			this.socket = socket;

		}

		public void run() {
			try {
				printWriter = new PrintWriter(socket.getOutputStream(), true);
				printWriter.println(line);

			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}