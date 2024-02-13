package fxui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class AppController {
    public ListView<String> listViewCards;

    @FXML
    public void onSwapToCardDeckPressed(ActionEvent event) throws IOException {
        swapToSceneCardDeck(event);
    }

    @FXML
    public void onAddCardPressed(ActionEvent event) throws IOException {
        swapToSceneAddCard(event);
    }

    public static void swapToSceneCardDeck(ActionEvent event) throws IOException {
        swapToScene(event, "CardDeck.fxml");
    }

    public static void swapToSceneCardManager(ActionEvent event) throws IOException {
        swapToScene(event, "CardManager.fxml");
    }

    public static void swapToSceneAddCard(ActionEvent event) throws IOException {
        swapToScene(event, "CardCreator.fxml");
    }

    /**
     * 
     * Allows for swapping between multiple different .fxml scene files within the
     * same application controller.
     * 
     * @param event       The ActionEvent that triggered the scene swap (usually a
     *                    button press).
     * @param targetScene A path to the .fxml file of the scene to swap to.
     * @throws IOException
     * 
     */
    private static void swapToScene(ActionEvent event, String targetScene) throws IOException {
        AppController c = new AppController();
        Parent root = FXMLLoader.load(c.getClass().getResource(targetScene));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
