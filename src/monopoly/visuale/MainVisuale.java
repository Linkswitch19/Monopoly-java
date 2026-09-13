package monopoly.visuale;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;


import java.io.IOException;


public class MainVisuale extends Application {
    private static GiocoVisuale giocoVisuale;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        MainVisuale.giocoVisuale=new GiocoVisuale(primaryStage);
    }

    public static GiocoVisuale getGiocoVisuale() {
        return giocoVisuale;
    }
}
