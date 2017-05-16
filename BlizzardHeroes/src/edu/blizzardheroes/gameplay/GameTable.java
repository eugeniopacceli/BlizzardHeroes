package edu.blizzardheroes.gameplay;

import edu.blizzardheroes.model.actors.Player;
import edu.blizzardheroes.model.cards.CardAttribute;
import edu.blizzardheroes.model.cards.Card;
import edu.blizzardheroes.model.cards.CardCategory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javafx.util.Pair;


// This class represents the place where the game occurs
public class GameTable {
    public Map<Player, Card> currentCards;  
    public CardAttribute attributeSelected;

    public GameTable(){    
        currentCards = new HashMap();
    }
    
    // Compare all the n cards on the table, returning the winner and owner
    public Pair<Player, Card> playTurn(){           
        System.out.println("Cards on the table: \n");
        currentCards.entrySet().forEach((entry) -> {
            Player player = entry.getKey();
            Card card = entry.getValue();
            System.out.println("Card player: " + player.getName());
            System.out.println(card.getHeroName()
                    + " Damage: " + card.getDamage()
                    + " Utility: " + card.getUtility()
                    + " Survavibility: " + card.getSurvivability()
                    + " Complexity: " + card.getComplexity() + "\n");
        });     
        
        Pair<Player, Card> bestCard = compareAttribute(this.attributeSelected);       
        System.out.println("Winner: " + bestCard.getKey().getName()
                          +" Card: " + bestCard.getValue().getHeroName() + "\n");
        
        // Remove the card from each player that lost
        this.currentCards.entrySet().forEach((entry) -> {
            entry.getKey().getCards().remove(entry.getValue());        
        });
        
        // Add the cards to the winner
        bestCard.getKey().getCards().addAll(this.currentCards.values());
        
        // Clear the 'table'
        this.currentCards.clear();        
        return bestCard;
    }
    
    // Compare cards based on attribute selected
    public Pair<Player, Card> compareAttribute(CardAttribute attribute){        
        Pair<Player, Card> bestCard = new Pair(this.currentCards.keySet().iterator().next(),
                                               this.currentCards.values().iterator().next());
        Pair<Player, Card> trumpCard = null;
        ArrayList<Pair<Player, Card>> aCards = new ArrayList<>();
        for(Map.Entry<Player, Card> entry : this.currentCards.entrySet()){
            Card card = entry.getValue();
            if(card.getCategory() == CardCategory.A){
                aCards.add(new Pair(entry.getKey(), entry.getValue()));
            } else if(card.isTrumpCard()){
                trumpCard = new Pair(entry.getKey(), entry.getValue());
            }
            if(card.getAttribute(attribute) > bestCard.getValue().getAttribute(attribute)){
                bestCard = new Pair(entry.getKey(), entry.getValue());
            }
        }
        if(trumpCard != null ){
            if(aCards.size() == 0){
                return trumpCard;
            }else{
                return aCards.get(0);
            }
        }
        return bestCard;
    }            

    // Tell if only one current player (winner)
    public boolean verifyWinner(Player[] players) {
        int actives = 0;
        for(int i = 0; i < players.length; i++){
            if(players[i].isActive()){
                actives++;
            }
        }
        return actives == 1;
    }
    
    // This method can be used to get the winner 
    public Player getWinner(Player[] players){
        for(int i = 0; i < players.length; i++){
            if(players[i].isActive()){
                return players[i];
            }
        }
        return null;
    }
}