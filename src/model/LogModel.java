/**
 * File name: LogModel.java
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
package model;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class LogModel {


    private ObservableList<String> logList;

    public LogModel() {
        logList = FXCollections.observableArrayList();
    }

    public void addString(String string) {
        this.logList.add(string);
    }


    public String getRecentLog(){
        if(logList.isEmpty()){
            return "";
        }
        return this.logList.get(this.logList.size()-1);
    }



    public ObservableList<String> getLogList() {
        return logList;
    }


}
