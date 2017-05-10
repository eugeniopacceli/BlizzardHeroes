package edu.blizzardheroes.gui;

import edu.blizzardheroes.gameplay.GameTable;
import edu.blizzardheroes.model.actors.ComputerPlayer;
import edu.blizzardheroes.model.actors.HumanPlayer;
import edu.blizzardheroes.model.actors.Player;
import edu.blizzardheroes.model.cards.Card;
import edu.blizzardheroes.model.cards.CardAttribute;
import edu.blizzardheroes.model.cards.Deck;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
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
    
    @FXML Label subtitle;

    @FXML Button gameButton;
    
    Player[] players;
    Deck deck;
    GameTable table;
    Player currentPlayer;

    int playersNumber;
    
    protected GameState state;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        backgroundGrid.setStyle(
                "-fx-background-color: #000000;"
                + "-fx-background-image: url(\"/edu/blizzardheroes/assets/header_lrg.jpg\");"
                + "-fx-background-repeat: stretch;"
                + "-fx-background-position: center center;");

        //Image image = new Image("/edu/blizzardheroes/assets/heroes-logo-large.png");
        humanCardImage.setStyle("-fx-background-color: #000000;");

        playersNumber = 4;        
        state = GameState.MANUAL;
        players = new Player[playersNumber];

        //The first players always is the Human
        players[0] = new HumanPlayer("Humano");
        for(int i = 1; i < playersNumber; i++)
            players[i] = new ComputerPlayer("Computador " + i);
        
        deck = new Deck();
        deck.buildDeck();
        ArrayList<Card>[] decks = deck.distributeCards(playersNumber);
        
        for(int i = 0; i < playersNumber; i++)
            players[i].setCards(decks[i]); 
        
        table = new GameTable();

        currentPlayer = players[0];
        sync();
    }
    
    protected CardAttribute displayChooseDialog(int damage, int utility, int survival, int complexity){
        List<String> choices = new ArrayList<>();
        
        final String damageStr = "Dano";
        final String utilityStr = "Utilidade";
        final String survivalStr = "Sobrevivência";
        final String complexityStr = "Complexidade";
        
        choices.add(damageStr);
        choices.add(utilityStr);
        choices.add(survivalStr);
        choices.add(complexityStr);

        ChoiceDialog<String> dialog = new ChoiceDialog<>("Dano", choices);
        dialog.setTitle("Que a batalha comece!");
        dialog.setHeaderText("Em suas mãos...\nDano: " + damage +
                             "\nUtilidade: "+ utility +
                             "\nSobrevivência: "+ survival +
                             "\nComplexidade: " + complexity);
        dialog.setContentText("Escolha o atributo para competir:");
        
        Image image = new Image("/edu/blizzardheroes/assets/heroes-logo-large.png");
        ImageView imgView = new ImageView();
        imgView.setImage(image);
        
        dialog.setGraphic(imgView);

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            switch(result.get()){
                case damageStr: return CardAttribute.DAMAGE;
                case utilityStr: return CardAttribute.UTILITY;
                case survivalStr: return CardAttribute.SURVIVABILITY;
                case complexityStr: return CardAttribute.COMPLEXITY;
            }
        }
        return CardAttribute.DAMAGE;
    }
    
    
    protected Label getStatusLabelByPlayer(Player p){
        if(p == players[0]){
            return humanStats;
        }
        if(p == players[1]){
            return computer1Stats;
        }
        if(p == players[2]){
            return computer2Stats;
        }
        return computer3Stats;
    }
    
    protected void setStatusLabel(Label status, String text){
        computer1Stats = computer2Stats = computer3Stats = humanStats = null;
        status.setText(text);
    }
    
    protected void updateImage(ImageView imgView, String category, String imageName){
        System.out.println(category + "/" + imageName);
        Image image = new Image("/edu/blizzardheroes/assets/cards/" + category + "/" +imageName + ".png");
        imgView.setImage(image);
    }

    protected void sync(){
        updateImage(computer1CardImage, players[1].playCard().getCategoryName(), players[1].playCard().getHeroName());
        updateImage(computer2CardImage, players[2].playCard().getCategoryName(), players[2].playCard().getHeroName());
        updateImage(computer3CardImage, players[3].playCard().getCategoryName(), players[3].playCard().getHeroName());
        updateImage(humanCardImage, players[0].playCard().getCategoryName(), players[0].playCard().getHeroName());
        
        computer1CardCount.setText(Integer.toString(players[1].getCards().size()));
        computer2CardCount.setText(Integer.toString(players[2].getCards().size()));
        computer3CardCount.setText(Integer.toString(players[3].getCards().size()));
        humanCardCount.setText(Integer.toString(players[0].getCards().size()));
        
        switch(state){
            case MANUAL:
                Card currentCard = players[0].playCard();
                table.attributeSelected = displayChooseDialog(currentCard.getDamage(),
                                    currentCard.getUtility(),
                                    currentCard.getSurvivability(),
                                    currentCard.getComplexity());
                state = GameState.PLAYING;
                break;
            case PLAYING:
                // AI players
                ComputerPlayer p = (ComputerPlayer)currentPlayer;
                table.attributeSelected = p.getCurrentBestAttribute();
                break;
        }
        subtitle.setText(currentPlayer.getName() + " escolheu " + table.attributeSelected.name());

        for(int i = 0; i < playersNumber; i++){
            table.currentCards.put(players[i], players[i].getCards().get(0));
        }
        Pair<Player, Card> winner = table.compareCards();

        if(winner.getKey() != players[0]){
            state = GameState.PLAYING;
            currentPlayer = winner.getKey();
            gameButton.setText("Confirma");
        }else{
            state = GameState.MANUAL;
            currentPlayer = players[0];
            gameButton.setText("Escolha");
        }
        setStatusLabel(getStatusLabelByPlayer(currentPlayer), "Selecionando");
    }
    
    @FXML
    public void gameAction(){
        sync();
    }
}
