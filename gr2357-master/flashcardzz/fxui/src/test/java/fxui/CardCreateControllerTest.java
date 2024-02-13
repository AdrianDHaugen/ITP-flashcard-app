package fxui;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isNotNull;
import static org.testfx.matcher.control.TextInputControlMatchers.hasText;

import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.framework.junit5.Start;

import core.Context;
import core.Deck;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CardCreateControllerTest extends ApplicationTest {

    @Start
    public void start(Stage stage) throws Exception {
        Context.getInstance().setCurrentDeck(new Deck("TestDeck"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CardCreator.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
    }

    @Test
    public void testTextFieldsExist() {
        verifyThat("#frontInput", isNotNull());
        verifyThat("#backInput", isNotNull());
    }

    @Test
    public void testInputInTextFields() {
        clickOn("#frontInput").write("Front Text");
        clickOn("#backInput").write("Back Text");
        verifyThat("#frontInput", hasText("Front Text"));
        verifyThat("#backInput", hasText("Back Text"));
    }
}
