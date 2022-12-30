package com.example.oneStop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.awt.GraphicsEnvironment;
import java.awt.GraphicsDevice;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

       /* GraphicsDevice graphicsDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int width = graphicsDevice.getDisplayMode().getWidth();
        int height = graphicsDevice.getDisplayMode().getHeight();*/

        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("login_page.fxml"));

        Scene scene = new Scene(fxmlLoader.load(),900,640);
        stage.setTitle("OneStop");
        stage.setScene(scene);
        //stage.setMaximized(true);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}