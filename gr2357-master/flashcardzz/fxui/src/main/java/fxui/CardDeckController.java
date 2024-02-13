package fxui;

import java.io.IOException;

import core.Context;
import core.Deck;
import core.Deck.CardSide;
import core.Card;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

public class CardDeckController {

    @FXML
    private Label cardLabel;
    @FXML
    private TextField deckNameField;
    @FXML
    private Label deckNameLabel;
    @FXML
    private Button nextButton;
    @FXML
    private Button previousButton;
    @FXML
    private Button submitDeckNameButton;

    private Deck deck;

    public CardDeckController() {
        this.deck = Context.getInstance().getCurrentDeck();
    }

    @FXML
    private void initialize() {
        deckNameLabel.setText(deck.getName());

        deckNameField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                changeDeckName();
            }
        });

        updateCardDisplay();
    }

    @FXML
    public void nextCard() {
        deck.goToNextCard();
        updateCardDisplay();
    }

    @FXML
    public void previousCard() {
        deck.goToPreviousCard();
        updateCardDisplay();
    }

    @FXML
    public void flipCard() {
        deck.flipCard();
        updateCardDisplay();
    }

    private void updateCardDisplay() {
        Card currentCard = deck.getCurrentCard();
        String displayText;

        if (deck.getCurrentCardSide() == CardSide.FRONT) {
            displayText = currentCard.getFront();
        } else {
            displayText = currentCard.getBack();
        }

        cardLabel.setText(displayText);
        updateNavigationButtons();
    }

    @FXML
    public void changeDeckName() {
        String newDeckName = deckNameField.getText().trim();

        if (newDeckName.isEmpty()) {
            showErrorMessage("Invalid Name", "The deck name cannot be empty or just whitespace.");
            return;
        }

        deckNameLabel.setText(newDeckName); // Update the displayed deck name
        deckNameField.clear(); // Clear the input field
        deckNameField.setVisible(false); // Hide the TextField
        submitDeckNameButton.setVisible(false); // Hide the submit button

        try {
            if (App.getStorageMethod() == "remote") {
                RestCommunicator.renameDeck(deck, newDeckName);
                ;
                deck.setName(newDeckName);
            } else {
                deck.setName(newDeckName);
                Context.getInstance().saveDeck();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(AlertType.WARNING, "Could not save to database");
            alert.show();
        }
    }

    @FXML
    public void onBackToManagerPressed(ActionEvent event) throws IOException {
        AppController.swapToSceneCardManager(event);
    }

    @FXML
    public void toggleDeckNameChange() {
        if (deckNameField.isVisible()) {
            changeDeckName();
        } else {
            deckNameField.setVisible(true);
            submitDeckNameButton.setVisible(true);
        }
    }

    private void updateNavigationButtons() {
        if (deck.getCardDeckSize() <= 1) {
            nextButton.setDisable(true);
            previousButton.setDisable(true);
        } else {
            nextButton.setDisable(false);
            previousButton.setDisable(false);
        }
    }

    private void showErrorMessage(String title, String content) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null); // No header for this alert
        alert.setContentText(content);
        alert.showAndWait();
    }

}
