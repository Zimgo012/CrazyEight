/**
 * File name: PlayerModel.java
 * Student Name: JOHN RYCCA BELCINA
 * Student Number: 041128039
 * Course: CST 8221 – JAP, Lab Section: 300/303
 * Professor: Cormier, Daniel | Singh, Ramanjeet
 * Date: 2025-02-09
 * Compiler: IntelliJ IDEA
 * Purpose:
 * This project involves developing a software version of the Crazy Eights card game using a standard deck of playing cards.
 * The game will follow the first three variation rules listed on Wikipedia, along with additional custom rules.
 * Understanding these variations is essential for proper implementation.
 * This project is a mandatory requirement for passing the Algonquin CST 8221 – JAP course.
 * Copyright © 2025 John Rycca Belcina. All rights reserved.
 */
package Model;


import java.util.ArrayList;
import java.util.List;

public class PlayerModel {
    private String playerName;
    private List<CardModel> hand;

    public PlayerModel(String playerName) {
        this.playerName = playerName;
        this.hand = new ArrayList<>();
    }

    public void addCard(CardModel card) {
        hand.add(card);
    }

    public void removeCard(CardModel card) {
        hand.remove(card);
    }

    public String getPlayerName(){
        return this.playerName;
    }

    public List<CardModel> getPlayerCards(){
        return this.hand;
    }

    public void printCard(){
        for(CardModel card : hand){
            System.out.println(card);
        }
    }




}
