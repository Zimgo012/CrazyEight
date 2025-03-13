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

/**
 * The LogModel class serves as a model for managing a collection of log messages.
 * It provides functionality to add log messages, retrieve the most recent log
 * message, and access the full list of log messages.
 */
public class LogModel {


    private ObservableList<String> logList;

    /**
     * Constructs an instance of the LogModel class.
     * Initializes an observable list to keep track of log messages.
     */
    public LogModel() {
        logList = FXCollections.observableArrayList();
    }

    /**
     * Adds a string message to the log list.
     *
     * @param string the log message to be added
     */
    public void addString(String string) {
        this.logList.add(string);
    }


    /**
     * Retrieves the most recent log message from the log list.
     * If the log list is empty, an empty string is returned.
     *
     * @return the most recent log message as a string, or an empty string if the log list is empty.
     */
    public String getRecentLog(){
        if(logList.isEmpty()){
            return "";
        }
        return this.logList.get(this.logList.size()-1);
    }



    /**
     * Retrieves the list of log messages stored in the model.
     * The log list is observable, allowing UI components or other observers
     * to react to changes in real time as log messages are added or removed.
     *
     * @return an observable list of strings representing the log messages.
     */
    public ObservableList<String> getLogList() {
        return logList;
    }


}
