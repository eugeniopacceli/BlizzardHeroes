
package edu.blizzardheroes.model.actors;

import edu.blizzardheroes.model.cards.Card;
import edu.blizzardheroes.model.cards.CardAttribute;
import java.util.HashMap;
import java.util.Map;

// One type of the players: AI or Computer
public class ComputerPlayer extends Player {
    public ComputerPlayer(String name){
        super(name);
    }
    
    // Basic AI to computer defines the best attribute to start the turn
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
