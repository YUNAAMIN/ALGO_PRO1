package application;
	

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
public class maincalc extends Application {// main calculation in main class this screen make greeting to user and start the program.
    @Override
    public void start(Stage primaryStage) {
        BorderPane bp = new BorderPane();
        StackPane pane = new StackPane();
        Button start = new Button("LET'S START!!");
        start.setFont(new Font("Comic Sans MS", 20));
        start.setStyle("-fx-background-color: #89CFF0; -fx-text-fill: white;");
        start.setMaxWidth(300);
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setStyle("-fx-background-color: #89CFF0");
        Image startImage = new Image("C:\\Users\\Yuna\\Desktop\\New folder\\pic\\giphy.gif");
        ImageView view = new ImageView(startImage);
        view.setFitWidth(700);
        view.setFitHeight(700);
        view.setPreserveRatio(true);
        vbox.getChildren().addAll(view);
        pane.getChildren().addAll(vbox, start);
        VBox gif= new VBox();
        gif.setAlignment(Pos.CENTER);
        gif.setStyle("-fx-background-color: #89CFF0");
        Image gifim = new Image("C:\\Users\\Yuna\\Desktop\\New folder\\pic\\48691010fc420959ddbad33ab95f323b.gif");
        ImageView imageView = new ImageView(gifim);
        imageView.setFitWidth(700);
        imageView.setFitHeight(900);
        imageView.setPreserveRatio(true);
        gif.getChildren().addAll(imageView);
        bp.setCenter(pane);
        Scene scene = new Scene(bp, 700, 400); // Set the size of the scene
        primaryStage.setScene(scene);
        primaryStage.setTitle("Digital LEDs System :)");
        primaryStage.show();
//**************** set on actions***************//
        start.setOnAction(e -> {// go to next screen "calcscreen" and play a one second.
            bp.setCenter(gif);
            // make time that the image will be shown for one second.
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
                //after the time is over and the image shown it will go to next screen "calcscreen".
                    bp.setCenter(new calcscreen());// go to next screen "calcscreen" .
            }));
            timeline.setCycleCount(1); // make the image shown only once by one cycle.
            timeline.play(); // play the image that is shown
        });
    }
    public static void main(String[] args) {
        Application.launch(args);
    }
    
}
