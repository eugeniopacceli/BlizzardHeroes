/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.blizzardheroes.model.cards;

import edu.blizzardheroes.model.actors.Player;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Daniel
 */
// Control the actions about the cards distribution
public class Deck {       
    private ArrayList<Card> deck;
    
    public Deck(){
        this.deck = new ArrayList<>();
    }
    
    public void buildDeck(){    
        //Category build - A
        Card card = new Card("butcher", 9, 4, 5, 3, CardCategory.A);
        deck.add(card);
        card = new Card("genji", 8, 4, 4, 6, CardCategory.A);
        deck.add(card);
        card = new Card("valla", 9, 1, 4, 3, CardCategory.A);
        deck.add(card);
        card = new Card("guldan", 8, 3, 5, 6, CardCategory.A);
        deck.add(card);
        card = new Card("illidan", 8, 3, 6, 8, CardCategory.A);
        deck.add(card);
        card = new Card("kaelthas", 9, 5, 3, 7, CardCategory.A);
        deck.add(card);
        card = new Card("thrall", 8, 5, 7, 6, CardCategory.A);
        deck.add(card);
        card = new Card("tychus", 8, 1, 5, 6, CardCategory.A);
        deck.add(card);
        
        //Category build - B
        card = new Card("artanis", 6, 6, 8, 5, CardCategory.B);
        deck.add(card);
        card = new Card("arthas", 6, 5, 8, 4, CardCategory.B);
        deck.add(card);
        card = new Card("diablo", 5, 7, 9, 5, CardCategory.B);
        deck.add(card);
        card = new Card("chen", 3, 4, 10, 6, CardCategory.B);
        deck.add(card);
        card = new Card("leoric", 4, 5, 8, 6, CardCategory.B);
        deck.add(card);
        card = new Card("rexxar", 5, 8, 7, 6, CardCategory.B);
        deck.add(card);
        card = new Card("johanna", 3, 6, 10, 4, CardCategory.B);
        deck.add(card);
        card = new Card("etc", 4, 6, 9, 3, CardCategory.B);
        deck.add(card);
        
        //Category build - C
        card = new Card("lucio", 3, 8, 6, 5, CardCategory.C);
        deck.add(card);
        card = new Card("lt-morales", 3, 9, 4, 5, CardCategory.C);
        deck.add(card);
        card = new Card("kharazim", 4, 9, 7, 4, CardCategory.C);
        deck.add(card);
        card = new Card("malfurion", 4, 7, 5, 5, CardCategory.C);
        deck.add(card);
        card = new Card("brightwing", 4, 9, 5, 7, CardCategory.C);
        deck.add(card);
        card = new Card("lili", 3, 8, 8, 3, CardCategory.C);
        deck.add(card);
        card = new Card("tassadar", 6, 7, 7, 4, CardCategory.C);
        deck.add(card);
        card = new Card("uther", 3, 9, 7, 2, CardCategory.C);
        deck.add(card);
        
        //Category build - D
        card = new Card("sylvanas", 9, 7, 3, 6, CardCategory.D);
        deck.add(card);
        card = new Card("azmodan", 3, 6, 7, 5, CardCategory.D);
        deck.add(card);
        card = new Card("sgt-hammer", 9, 3, 3, 6, CardCategory.D);
        deck.add(card);
        card = new Card("nazeebo", 8, 4, 3, 6, CardCategory.D);
        deck.add(card);
        card = new Card("murky", 5, 5, 1, 8, CardCategory.D);
        deck.add(card);
        card = new Card("medivh", 2, 10, 6, 8, CardCategory.D);
        deck.add(card);
        card = new Card("xul", 5, 9, 7, 4, CardCategory.D);
        deck.add(card);
        card = new Card("the-lost-vikings", 4, 4, 6, 6, CardCategory.D);
        deck.add(card);        
    }
    // Distribui as cartas para os jogadores
    public ArrayList<Card>[] distributeCards(int playersNumber){
        int playerIndex = 0;
        int remainingCards = 32;         
        
        Card randomCard;
        Random random = new Random();
        
        ArrayList<Card>[] playersDecks;       
        playersDecks = new ArrayList[playersNumber];        
        
        for(int i = 0; i < playersNumber; i++)
            playersDecks[i] = new ArrayList<>();                
        
        while(!this.deck.isEmpty())
        {
            randomCard = this.deck.get(random.nextInt(remainingCards));
            playersDecks[playerIndex].add(randomCard);
            this.deck.remove(randomCard);
            
            playerIndex++;
            remainingCards--;
            
            if(playerIndex >= playersNumber)
                playerIndex = 0;
        }
        return playersDecks;
    }              
    
    public void showDecks(Player[] players){    
        for (Player player : players) {
            player.ShowDeck();
        }
    }
}
