package in.balou.mqlive.Monitor;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("MainView.fxml"));
        primaryStage.setTitle("MQLive Monitor");
        primaryStage.setScene(new Scene(root, 761, 750));
        primaryStage.show();
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>()
        {
            @Override
            public void handle(WindowEvent event)
            {
                Platform.exit();
                System.exit(0);
            }
        });

    }


    public static void main(String[] args) {
        launch(args);
    }
}
