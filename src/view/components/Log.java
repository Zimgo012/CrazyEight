package view.components;

import javafx.collections.ListChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import model.LogModel;
import model.SingleGameModel;

public class Log {
    private LogModel logModel;
    private SingleGameModel singleGameModel;
    private VBox vbox; // Store VBox globally

    public Log(LogModel logModel, SingleGameModel singleGameModel) {
        this.logModel = logModel;
        this.singleGameModel = singleGameModel;
        this.vbox = new VBox(10); // Adjust spacing

        vbox.setPrefSize(250, 550);
        vbox.setPadding(new Insets(20));
    }

    public ScrollPane generateLog() {
        ScrollPane log = new ScrollPane();
        log.setContent(vbox);
        log.setStyle("-fx-background: transparent; -fx-background-color: rgba(0,0,0,0.5); -fx-background-radius: 20px;");
        log.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        log.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        log.setMaxHeight(200);
        log.setMaxWidth(260);

        return log;
    }

    public void refreshLog() {
        vbox.getChildren().clear(); // Remove old logs
        for (String message : logModel.getLogList()) {
            vbox.getChildren().add(new Label(message));
        }
    }
}
