package fxui;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isNotNull;

import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.framework.junit5.Start;

import core.Context;
import core.Deck;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppControllerTest extends ApplicationTest {

    @Start
    public void start(Stage stage) throws Exception {
        App.setStorageMethod("local");
        Context.getInstance().setCurrentDeck(new Deck("TestDeck"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CardManager.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
    }

    @Test
    public void testButtonsExist() {
        verifyThat("#buttonAddCard", isNotNull());
        verifyThat("#buttonSwapToCardDeck", isNotNull());
    }

    @Test
    public void testSwapToSceneAddCard() {
        clickOn("#buttonAddCard");
        verifyThat("#addCardPane", isNotNull());
    }

    @Test
    public void testSwapToSceneCardDeck() {
        clickOn("#buttonSwapToCardDeck");
        verifyThat("#cardDeckPane", isNotNull());
    }

}