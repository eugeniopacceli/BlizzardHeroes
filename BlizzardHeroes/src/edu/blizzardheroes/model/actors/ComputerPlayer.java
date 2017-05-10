/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.blizzardheroes.model.actors;

import edu.blizzardheroes.model.cards.Card;
import edu.blizzardheroes.model.cards.CardAttribute;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Daniel
 */
public class ComputerPlayer extends Player {
    public ComputerPlayer(String name){
        super(name);
    }
    
    public CardAttribute getCurrentBestAttribute(){
        HashMap<CardAttribute, Integer> attr = new HashMap<>();
        Card card = this.playCard();
        int max = 0;
        CardAttribute maxAttr = CardAttribute.DAMAGE;
        
        attr.put(CardAttribute.DAMAGE,card.getDamage());
        attr.put(CardAttribute.UTILITY,card.getUtility());
        attr.put(CardAttribute.COMPLEXITY,card.getComplexity());
        attr.put(CardAttribute.SURVIVABILITY,card.getSurvivability());

        for(Map.Entry<CardAttribute, Integer> entry : attr.entrySet()) {
            if(entry.getValue() > max){
                max = entry.getValue();
                maxAttr = entry.getKey();
            }
        }

        return maxAttr;
    }
}
