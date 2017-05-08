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
        
        int i;
        int playersNumber = 4;
        
        Player[] players = new Player[playersNumber];
        
        //The first players always is the Human
        players[0] = new HumanPlayer("Humano");
        
        for(i = 1; i < playersNumber; i++)
            players[i] = new ComputerPlayer("Computer " + i);
        
        Deck deck = new Deck();
        deck.buildDeck();
        ArrayList<Card>[] decks = deck.distributeCards(playersNumber);
        
        for(i = 0; i < playersNumber; i++)
            players[i].setCards(decks[i]); 
        
        deck.showDecks(players);
        
        GameTable table = new GameTable();
        table.attributeSelected = CardAttribute.DAMAGE;
        
        for(i = 0; i < playersNumber; i++)
            table.currentCards.put(players[i], players[i].getCards().get(0));
        
        Pair<Player, Card> winner = table.compareCards();            
        
        deck.showDecks(players);
    }   

    public static void main(String[] args) {
        launch(args);
    }
    
}
