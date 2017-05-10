package edu.blizzardheroes.gui;

import edu.blizzardheroes.gameplay.GameTable;
import edu.blizzardheroes.model.actors.ComputerPlayer;
import edu.blizzardheroes.model.actors.HumanPlayer;
import edu.blizzardheroes.model.actors.Player;
import edu.blizzardheroes.model.cards.Card;
import edu.blizzardheroes.model.cards.CardAttribute;
import edu.blizzardheroes.model.cards.DeckBuilder;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

public class MainWindowController implements Initializable {
    
    @FXML GridPane backgroundGrid;
    
    @FXML TitledPane computer1Frame;
    @FXML TitledPane computer2Frame;
    @FXML TitledPane computer3Frame;
    @FXML TitledPane humanFrame;
    
    @FXML Label computer1CardCount;
    @FXML Label computer2CardCount;
    @FXML Label computer3CardCount;
    @FXML Label humanCardCount;
    
    @FXML ImageView computer1CardImage;
    @FXML ImageView computer2CardImage;
    @FXML ImageView computer3CardImage;
    @FXML ImageView humanCardImage;
    
    @FXML Label computer1Stats;
    @FXML Label computer2Stats;
    @FXML Label computer3Stats;
    @FXML Label humanStats;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        backgroundGrid.setStyle(
                "-fx-background-color: #000000;"
                + "-fx-background-image: url(\"/edu/blizzardheroes/assets/header_lrg.jpg\");"
                + "-fx-background-repeat: stretch;"
                + "-fx-background-position: center center;");

        //Image image = new Image("/edu/blizzardheroes/assets/heroes-logo-large.png");
        Image image = new Image("/edu/blizzardheroes/assets/cards/specialists/medivh.png");
        computer1CardImage.setImage(image);
        computer2CardImage.setImage(image);
        computer3CardImage.setImage(image);
        humanCardImage.setImage(image);
        humanCardImage.setStyle("-fx-background-color: #000000;");
        
        int i;
        int playersNumber = 4;
        
        Player[] players = new Player[playersNumber];
        
        //The first players always is the Human
        players[0] = new HumanPlayer("Humano");
        
        for(i = 1; i < playersNumber; i++)
            players[i] = new ComputerPlayer("Computer " + i);
        
        DeckBuilder deck = new DeckBuilder();
        deck.buildDeck();
        ArrayList<Card>[] decks = deck.distributeCards(playersNumber);
        
        for(i = 0; i < playersNumber; i++)
            players[i].setCards(decks[i]); 
        
        deck.showDecks(players);
        
        GameTable table = new GameTable();
        table.attributeSelected = CardAttribute.DAMAGE;
        
        for(i = 0; i < playersNumber; i++)
            table.currentCards.put(players[i], players[i].getCards().get(0));
        
        Pair<Player, Card> winner = table.playTurn();            
        
        deck.showDecks(players);
    }    
    
}
