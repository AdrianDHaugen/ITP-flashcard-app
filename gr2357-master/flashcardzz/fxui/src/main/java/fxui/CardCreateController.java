package fxui;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

import java.io.IOException;

import core.Card;
import core.Context;
import core.Deck;
import javafx.event.ActionEvent;

public class CardCreateController {

    @FXML
    TextField frontInput;
    @FXML
    TextField backInput;

    private Deck deck;

    public CardCreateController() {
        this.deck = Context.getInstance().getCurrentDeck();
    }

    @FXML
    public void onCardCreate(ActionEvent event) throws IOException {
        Card newCard = new Card(frontInput.getText(), backInput.getText());
        deck.addCard(newCard);
        System.out.println(deck.getCardDeck());
        System.out.println(Context.getInstance().getDecks().get(0).getCardDeck());
        try {
            if (App.getStorageMethod() == "remote") {
                RestCommunicator.addCard(deck, newCard);
            } else {
                Context.getInstance().saveDeck();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(AlertType.WARNING, "Could not save to file");
            alert.show();
        }
        AppController.swapToSceneCardManager(event);
    }

    @FXML
    public void onCardCreateCancel(ActionEvent event) throws IOException {
        AppController.swapToSceneCardManager(event);
    }
}
