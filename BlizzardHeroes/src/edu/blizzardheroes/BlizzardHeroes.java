package edu.blizzardheroes;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BlizzardHeroes extends Application {
    public static final String APPTITLE = "Blizzard Heroes Top Trumps";
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/edu/blizzardheroes/gui/MainWindow.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.setTitle(APPTITLE);
        stage.show();
    }   

    public static void main(String[] args) {
        launch(args);
    }
    
}
