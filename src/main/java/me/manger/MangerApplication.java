package me.manger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import me.manger.db.ReadFile;
import me.manger.db.WriteFile;

public class MangerApplication extends Application {

    public static void main(String[] args) {
        ReadFile.readDB();
        launch(args);
        WriteFile.writeDB();
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(MangerApplication.class.getResource("/me/manger/view/Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setResizable(false);
        stage.setTitle("Manger");
        stage.setScene(scene);
        stage.show();
    }

}