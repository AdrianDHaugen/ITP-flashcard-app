import axios from "axios";

const API_URL = "http://localhost:8080";

// Retrieves a list of all decks from the server.
export const getDecks = async () => {
  const response = await axios.get(`${API_URL}/deck`);
  return response.data;
};

// Creates a new deck with a specified name.
export const createDeck = async (deckName) => {
  try {
    // Make a POST request to the server to create a new deck.
    const response = await axios.post(`${API_URL}/deck`, {
      name: deckName,
    });

    // Log and return the response data.
    console.log("Response:", response.data);
    return response.data;
  } catch (error) {
    // Log any errors that occur during the request.
    console.error("Error:", error);
  }
};

// Removes a deck by its name.
export const removeDeck = async (deckName) => {
  const response = await axios.delete(`${API_URL}/deck/${deckName}`);
  return response;
};

// Retrieves all cards belonging to a specific deck.
export const getCardsByDeck = async (deckName) => {
  const response = await axios.get(`${API_URL}/deck/${deckName}/cards`);
  return response.data;
};

// Creates a new card in a specified deck with front and back text.
export const createCard = async (deckName, frontText, backText) => {
  try {
    const response = await axios.post(`${API_URL}/deck/${deckName}/cards`, {
      front: frontText,
      back: backText,
    });

    // Log and return the response data.
    console.log("Response:", response.data);
    return response.data;
  } catch (error) {
    // Log any errors that occur during the request.
    console.error("Error:", error);
  }
};

// Removes a card from a deck based on its front text.
export const removeCard = async (deckName, cardFront) => {
  const response = await axios.delete(
    `${API_URL}/deck/${deckName}/cards/${cardFront}`
  );
  console.log("Delete card response: " + response.data);
  return response;
};

// Renames the selected deck
export const renameDeck = async (deckName, newDeckName) => {
  const response = await axios.post(
    `${API_URL}/deck/${deckName}/rename`,
    null,
    {
      params: { newDeckName },
    }
  );
  return response;
};
