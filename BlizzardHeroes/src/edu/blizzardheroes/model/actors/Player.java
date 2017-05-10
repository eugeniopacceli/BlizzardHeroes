/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.blizzardheroes.model.actors;

import edu.blizzardheroes.model.cards.Card;
import java.util.ArrayList;

/**
 *
 * @author Daniel
 */
public abstract class Player {
    private ArrayList<Card> cards;
    private String name;
    
    public Player(String name){    
        this.name = name;
        this.cards = new ArrayList<>();
    }    
    
    public Card playCard(){
        return this.cards.get(0);               
    }            
    
    public boolean isActive(){
        return this.cards.size() > 0;
    }
    
    public void ShowDeck()
    {
        System.out.println("Deck Player: " + this.name + "\n");
        this.cards.forEach((card) -> {
            System.out.println(card.getHeroName() + "\n");
        });        
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
