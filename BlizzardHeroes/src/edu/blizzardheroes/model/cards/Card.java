/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.blizzardheroes.model.cards;

/**
 *
 * @author Daniel
 */
public class Card {
    private String heroName;
    
    private int damage;
    private int utility;
    private int survivability;
    private int complexity;
    
    private CardCategory category;
        
    public Card(String heroName, 
                int damage,
                int utility, 
                int survivavility,
                int complexity,
                CardCategory category)                
    {    
        this.heroName = heroName;
        this.damage = damage;
        this.utility = utility;
        this.survivability = survivavility;
        this.complexity = complexity;
        this.category = category;        
    }
    
    public boolean isTrumpCard(){    
        return this.heroName.equalsIgnoreCase("the-lost-vikings");
    }
    
    public int getAttribute(CardAttribute attribute){
        switch(attribute){
            case DAMAGE:
                return this.getDamage();
            case UTILITY:
                return this.getUtility();
            case SURVIVABILITY:
                return this.getSurvivability();
            case COMPLEXITY:
                return this.getComplexity();
            default:
                return 0;
        }
    }   

    public String getHeroName() {
        return heroName;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getUtility() {
        return utility;
    }

    public void setUtility(int utility) {
        this.utility = utility;
    }

    public int getSurvivability() {
        return survivability;
    }

    public void setSurvivability(int survivability) {
        this.survivability = survivability;
    }

    public int getComplexity() {
        return complexity;
    }

    public void setComplexity(int complexity) {
        this.complexity = complexity;
    }

    public CardCategory getCategory() {
        return category;
    }
    
    public String getCategoryName() {
        switch(category){
            case A: return "assassins";
            case B: return "warriors";
            case C: return "supports";
            default: return "specialists";
        }
    }

    public void setCategory(CardCategory category) {
        this.category = category;
    }
}
