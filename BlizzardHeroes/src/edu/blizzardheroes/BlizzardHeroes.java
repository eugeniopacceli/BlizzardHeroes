package edu.blizzardheroes;

import edu.blizzardheroes.gui.MainWindowController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BlizzardHeroes extends Application {
    public static final String APPTITLE = "Blizzard Heroes - Super Trunfo";
    
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass()
                .getResource("/edu/blizzardheroes/gui/MainWindow.fxml")
                .openStream());
        MainWindowController controller = (MainWindowController)loader.getController();
        
        controller.setStage(stage);
        Scene scene = new Scene(root);
        stage.setFullScreenExitHint("");
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.setTitle(APPTITLE);
        stage.show();
    }   

    public static void main(String[] args) {
        launch(args);
    }
    
}
