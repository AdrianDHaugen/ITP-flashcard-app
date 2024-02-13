package fxui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

import core.Context;
import core.Deck;

/**
 * JavaFX App
 */
public class App extends Application {

    private static String storageMethod = "remote";

    @Override
    public void start(Stage stage) throws IOException {
        Deck currentDeck = new Deck("Flashcards");
        // load deck
        try {
            ArrayList<Deck> decks;
            if (storageMethod == "remote") {
                decks = RestCommunicator.getDecks();
            } else {
                Context.getInstance().loadDeck();
                decks = Context.getInstance().getDecks();
            }
            if (!decks.isEmpty()) {
                currentDeck = decks.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(AlertType.WARNING, "Could not save to database");
            alert.show();
        }
        Context.getInstance().setCurrentDeck(currentDeck);
        if (Context.getInstance().getDecks().indexOf(currentDeck) == -1) {
            Context.getInstance().addDeck(currentDeck);
        }
        // start scene
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("CardManager.fxml"));
        Parent parent = fxmlLoader.load();
        stage.setScene(new Scene(parent));
        stage.show();
    }

    public static String getStorageMethod() {
        return storageMethod;
    }

    public static void setStorageMethod(String method) {
        if (method != "remote" && method != "local") {
            throw new IllegalArgumentException("undefiend storage method");
        }
        storageMethod = method;
    }

    public static void main(String[] args) {
        launch();
    }
}