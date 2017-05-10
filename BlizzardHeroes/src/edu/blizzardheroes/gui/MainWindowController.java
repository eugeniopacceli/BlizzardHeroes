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
import javafx.stage.Stage;
import javafx.util.Pair;

public class MainWindowController implements Initializable {
    Stage stage;
    
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

    @FXML Label subtitle;

    @FXML Button gameButton;
    
    Player[] players = new Player[4];
    Label[] playersLabel = new Label[4];
    ImageView[] playersCardImage = new ImageView[4];
    DeckBuilder deck;
    GameTable table;
    Player currentPlayer;

    int playersNumber;
    
    protected GameState state;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        playersNumber = 4;        
        state = GameState.MANUAL;
        players = new Player[playersNumber];

        playersLabel[1] = computer1CardCount;
        playersLabel[2] = computer2CardCount;
        playersLabel[3] = computer3CardCount;
        playersLabel[0] = humanCardCount;
        
        playersCardImage[1] = computer1CardImage;
        playersCardImage[2] = computer2CardImage;
        playersCardImage[3] = computer3CardImage;
        playersCardImage[0] = humanCardImage;
        
        //The first players always is the Human
        players[0] = new HumanPlayer("Humano");
        for(int i = 1; i < playersNumber; i++)
            players[i] = new ComputerPlayer("Computador " + i);

        deck = new DeckBuilder();
        deck.buildDeck();
        ArrayList<Card>[] decks = deck.distributeCards(playersNumber);
        
        for(int i = 0; i < playersNumber; i++)
            players[i].setCards(decks[i]); 
        
        table = new GameTable();
        currentPlayer = players[0];
    }
    
    public void setStage(Stage stage){
        this.stage = stage;
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
        
        if(stage != null){
            dialog.initOwner(stage);
        }

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
    
    protected void updateImage(ImageView imgView, String category, String imageName){
        System.out.println(category + "/" + imageName);
        Image image = new Image("/edu/blizzardheroes/assets/cards/" + category + "/" +imageName + ".png");
        imgView.setImage(image);
    }

    protected void sync(){
        for(int i = 0; i < players.length; i++){
            if(players[i].isActive()){
                updateImage(playersCardImage[i], players[i].playCard().getCategoryName(), players[i].playCard().getHeroName());
                playersLabel[i].setText(Integer.toString(players[i].getCards().size()));
            }
        }

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

        for(int i = 0; i < playersNumber; i++)
            if(players[i].isActive())
                table.currentCards.put(players[i], players[i].playCard());        
        
        Pair<Player, Card> winner = table.playTurn();

        subtitle.setText(currentPlayer.getName() + " escolheu " + table.attributeSelected.name()+", " + winner.getKey().getName() + " venceu a rodada.");

        if(winner.getKey() != players[0]){
            state = GameState.PLAYING;
            currentPlayer = winner.getKey();
            gameButton.setText("Confirma");
        }else{
            state = GameState.MANUAL;
            currentPlayer = players[0];
            gameButton.setText("Escolha");
        }
    }
    
    @FXML
    public void gameAction(){
        sync();
    }
}
