package fxui;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.nio.charset.StandardCharsets;
import java.net.http.HttpResponse;
import java.util.ArrayList;

import core.Card;
import core.Deck;
import json.JsonHandler;

public class RestCommunicator {
    static URI baseURI = URI.create("http://localhost:8080");

    private static final String APPLICATION_JSON = "application/json";
    private static final String APPLICATION_FORM_URLENCODED = "application/x-www-form-urlencoded";
    private static final String ACCEPT_HEADER = "Accept";
    private static final String CONTENT_TYPE_HEADER = "Content-Type";

    private static String urlEncode(String string) {
        return URLEncoder.encode(string, StandardCharsets.UTF_8).replace("+", "%20");
    }

    public static void addCard(Deck deck, Card card) throws IOException, InterruptedException {
        // create a client
        HttpClient client = HttpClient.newHttpClient();
        // create a request
        String urlDeckName = urlEncode(deck.getName());
        URI uri = baseURI.resolve("/deck/" + urlDeckName + "/cards");
        String data = "{\"front\":\"" + card.getFront() + "\",\"back\":\"" + card.getBack() + "\"}";
        HttpRequest request = HttpRequest.newBuilder(uri)
                .header(ACCEPT_HEADER, APPLICATION_JSON)
                .header(CONTENT_TYPE_HEADER, APPLICATION_JSON)
                .POST(BodyPublishers.ofString(data))
                .build();

        client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public static ArrayList<Deck> getDecks() throws MalformedURLException, IOException, InterruptedException {
        // create a client
        HttpClient client = HttpClient.newHttpClient();
        // create a request
        URI uri = baseURI.resolve("/deck/");
        HttpRequest request = HttpRequest.newBuilder(uri)
                // .header(ACCEPT_HEADER, APPLICATION_JSON)
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return JsonHandler.deserializeDecks(response.body());
    }

    public static void renameDeck(Deck deck, String newName) throws IOException, InterruptedException {
        // create a client
        HttpClient client = HttpClient.newHttpClient();
        // create a request
        String urlDeckName = urlEncode(deck.getName());
        String urlnewName = URLEncoder.encode(newName, StandardCharsets.UTF_8);
        URI uri = baseURI.resolve("/deck/" + urlDeckName + "/rename");
        HttpRequest request = HttpRequest.newBuilder(uri)
                .header(ACCEPT_HEADER, APPLICATION_JSON)
                .header(CONTENT_TYPE_HEADER, APPLICATION_FORM_URLENCODED)
                .POST(BodyPublishers.ofString("newDeckName=" + urlnewName))
                .build();
        client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public static void main(String[] args) {
        try {
            ArrayList<Deck> decks = getDecks();
            Deck deck = new Deck("new");
            decks.add(deck);
            renameDeck(deck, "Bogus");
            deck = getDecks().get(0);
            System.out.println(deck.getName());
        } catch (Exception e) {
            System.out.println("?");
        }
    }
}
