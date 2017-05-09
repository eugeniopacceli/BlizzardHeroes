package edu.blizzardheroes;

import edu.blizzardheroes.gameplay.GameTable;
import edu.blizzardheroes.model.actors.ComputerPlayer;
import edu.blizzardheroes.model.actors.HumanPlayer;
import edu.blizzardheroes.model.actors.Player;
import edu.blizzardheroes.model.cards.Card;
import edu.blizzardheroes.model.cards.CardAttribute;
import edu.blizzardheroes.model.cards.Deck;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Pair;

public class BlizzardHeroes extends Application {    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }   

    public static void main(String[] args) {
        launch(args);
    }
    
}
