package unipi.it.umdb;

import java.io.IOException;
import java.util.Objects;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class UmdbGui extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Javafx/Homepage.fxml")));
        Scene scene = new Scene(root);
        primaryStage.setTitle("UMDb");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

//    @Override
//    public void stop() {
//        MongoDriver md = MongoDriver.getInstance();
//        Neo4jDriver nd = Neo4jDriver.getInstance();
//        md.close();
//        nd.close();
//        Platform.exit();
//    }
}
