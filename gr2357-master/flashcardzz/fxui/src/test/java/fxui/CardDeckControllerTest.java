package fxui;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

import static org.testfx.matcher.base.NodeMatchers.isVisible;
import static org.testfx.matcher.base.NodeMatchers.isInvisible;

import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.framework.junit5.Start;

import core.Context;
import core.Deck;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CardDeckControllerTest extends ApplicationTest {

    @Start
    public void start(Stage stage) throws Exception {
        Deck testDeck = new Deck("test deck");
        testDeck.addCard(new core.Card("test front", "test back"));
        Context.getInstance().setCurrentDeck(testDeck);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CardDeck.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
    }

    @Test
    public void testChangeDeckName() {
        clickOn("Change Deck Name"); // click the button that makes the TextField visible
        sleep(1000); // wait for the visibility change to take effect
        clickOn("#deckNameField");
        write("New Deck Name");
        clickOn("#submitDeckNameButton");
        verifyThat("#deckNameLabel", hasText("New Deck Name"));
    }

    @Test
    public void testToggleDeckNameChange() {
        // Check initial state
        verifyThat("#deckNameField", isInvisible());
        verifyThat("#submitDeckNameButton", isInvisible());

        // Toggle visibility
        clickOn("Change Deck Name");
        verifyThat("#deckNameField", isVisible());
        verifyThat("#submitDeckNameButton", isVisible());

        // Toggle back
        clickOn("#deckNameField");
        write("Flashcard");
        clickOn("Change Deck Name");
        verifyThat("#deckNameField", isInvisible());
        verifyThat("#submitDeckNameButton", isInvisible());
    }

    @Test
    public void testFlipCardFunctionality() {
        verifyThat("#cardLabel", hasText("test front"));

        clickOn("Flip Card");
        verifyThat("#cardLabel", hasText("test back"));

        clickOn("Flip Card");
        verifyThat("#cardLabel", hasText("test front"));
    }
}
