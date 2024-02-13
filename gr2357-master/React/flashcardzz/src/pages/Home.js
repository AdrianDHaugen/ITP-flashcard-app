import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import DeckList from "../components/DeckList";
import CardList from "../components/CardList";
import "../App.css";
import RemoveCardPopup from "../components/RemoveCardPopup";
import AddCardPopup from "../components/AddCardPopup";
import AddDeckPopup from "../components/AddDeckPopup";
import RemoveDeckPopup from "../components/RemoveDeckPopup";
import {
  createDeck,
  getDecks,
  createCard,
  removeDeck,
  removeCard,
  getCardsByDeck,
  renameDeck,
} from "../api/axiosConfig";
import RenameDeckPopup from "../components/RenameDeckPopup";

// Home component: Main page for managing decks and cards.
function Home() {
  // State declarations for various features.
  const [selectedDeck, setSelectedDeck] = useState(null); // Currently selected deck.
  const [selectedDeckName, setSelectedDeckName] = useState(""); // Name of the selected deck.
  const [selectedCard, setSelectedCard] = useState(null); // Currently selected card.
  const [decks, setDecks] = useState([]); // Array of all decks.
  const [cards, setCards] = useState([]); // Array of cards in the selected deck.
  const [newDeckName, setNewDeckName] = useState("");
  // States for showing or hiding popups.
  const [showAddCardPopup, setShowAddCardPopup] = useState(false);
  const [showRemoveCardPopup, setShowRemoveCardPopup] = useState(false);
  const [showAddDeckPopup, setShowAddDeckPopup] = useState(false);
  const [showRemoveDeckPopup, setShowRemoveDeckPopup] = useState(false);
  const [showRenameDeckPopup, setShowRenameDeckPopup] = useState(false);

  const navigate = useNavigate(); // Hook for navigation.

  // Function to navigate to the flashcard view of the selected deck.
  const navigateToFlashcards = () => {
    if (selectedDeckName) {
      navigate(`/flashcard/${selectedDeckName}`);
    } else {
      alert("Please select a deck.");
    }
  };

  // Fetches deck data when the component mounts.
  useEffect(() => {
    const fetchDecks = async () => {
      const fetchedDecks = await getDecks();
      setDecks(fetchedDecks);
    };

    fetchDecks();
  }, []);

  // Fetching cards for the selected deck
  useEffect(() => {
    const fetchCards = async () => {
      if (selectedDeck) {
        const fetchedCards = await getCardsByDeck(selectedDeck);
        setCards(fetchedCards);
      } else {
        setCards([]); // Clear cards if no deck is selected.
      }
    };

    fetchCards();
  }, [selectedDeck]);

  const fetchCards = async () => {
    if (selectedDeckName) {
      const decks = await getDecks();
      const deck = decks.find((d) => d.name === selectedDeckName);
      setCards(deck.cardDeck);
    } else {
      setCards([]); // If there is no selected deck, clear the cards
    }
  };

  // Fetching cards for the selected deck
  useEffect(() => {
    fetchCards();
  }, [selectedDeckName]);

  // Function to handle clicking on a deck.
  const handleDeckClick = (deckName) => {
    const deck = decks.find((d) => d.name === deckName);
    setSelectedDeck(deckName);
    setSelectedDeckName(deck ? deck.name : "");
  };

  // Adds a new deck.
  const handleAddDeck = async (title) => {
    const newDeck = await createDeck(title);
    if (newDeck) {
      setSelectedDeck(newDeck.name);
      setSelectedDeckName(newDeck.name);
      setDecks((prevDecks) => [...prevDecks, newDeck]);
    }
  };
  // Removes the selected deck.
  const handleRemoveDeck = () => {
    if (selectedDeck) {
      removeDeck(selectedDeckName);
      setDecks((prevDecks) =>
        prevDecks.filter((deck) => deck.name !== selectedDeck)
      );
      setSelectedDeck(null);
      setSelectedDeckName("");
      setCards((prevCards) =>
        prevCards.filter((card) => card.deckId !== selectedDeck)
      );
      setShowRemoveDeckPopup(false);
    }
  };
  
  // Function to handle name changes for a deck.
  const handleNewDeckNameChange = (newName) => {
    setNewDeckName(newName);
  };
  // Function to rename the selected deck.
  const handleRenameDeck = async () => {
    if (selectedDeck && newDeckName.trim() !== "") {
      // Check if the new deck name is not empty and a deck is selected.
      const success = await renameDeck(selectedDeck, newDeckName.trim());

      if (success) {
        // If renaming was successful, update the decks and selected deck name
        const updatedDecks = decks.map((deck) => {
          if (deck.name === selectedDeck) {
            deck.name = newDeckName.trim();
          }
          return deck;
        });

        setDecks(updatedDecks);
        setSelectedDeckName(newDeckName.trim());
        setShowRenameDeckPopup(false); // Close the rename deck popup
      }
    }
  };

  // Adds a new card to the selected deck.
  const handleAddCard = async (title, body) => {
    const newCard = await createCard(selectedDeckName, title, body);
    if (newCard) {
      setCards((prevCards) => [...prevCards, newCard]);
      setShowAddCardPopup(false); // Close the add card popup after card is added
      fetchCards();
    }
  };

  // Selects a card.
  const handleCardSelect = (cardId) => {
    setSelectedCard(cardId);
  };

  // Confirms removal of a selected card.
  const confirmRemoveCard = () => {
    if (selectedCard) {
      removeCard(selectedDeckName, selectedCard);
      setCards((prevCards) =>
        prevCards.filter((card) => card.front !== selectedCard)
      );
      setSelectedCard(null);
      setShowRemoveCardPopup(false); // Close the remove card popup after card is removed
    } else {
      alert("Please select a card to remove.");
    }
  };

  // Toggles the visibility of popups.
  const togglePopup = (popupStateSetter) => {
    popupStateSetter((prevState) => !prevState);
  };

  // Main render function of the Home component.
  return (
    <div className="app-container">
      <div className="deck-list">
        <DeckList
          decks={decks}
          selectedDeck={selectedDeck}
          onDeckClick={handleDeckClick}
          onAddDeckClick={() => togglePopup(setShowAddDeckPopup)}
          onRemoveDeckClick={() =>
            selectedDeck
              ? togglePopup(setShowRemoveDeckPopup)
              : alert("Please select a deck to remove.")
          }
          onRenameDeckClick={() =>
            selectedDeck
              ? togglePopup(setShowRenameDeckPopup)
              : alert("Please select a deck to rename.")
          }
          navigateToFlashcards={navigateToFlashcards}
        />
        {showAddDeckPopup && (
          <AddDeckPopup
            onConfirm={handleAddDeck}
            onClose={() => togglePopup(setShowAddDeckPopup)}
          />
        )}
        {showRemoveDeckPopup && (
          <RemoveDeckPopup
            onConfirm={handleRemoveDeck}
            onClose={() => togglePopup(setShowRemoveDeckPopup)}
          />
        )}
        {showRenameDeckPopup && (
          <RenameDeckPopup
            newDeckName={newDeckName}
            onNewDeckNameChange={handleNewDeckNameChange}
            onConfirm={handleRenameDeck}
            onClose={() => togglePopup(setShowRenameDeckPopup)}
          />
        )}
      </div>

      {selectedDeck && (
        <div className="card-list">
          <h2>{`Cards in ${selectedDeckName}`}</h2>
          <CardList
            cards={cards} //.filter((card) => card.deckId === selectedDeck)}
            selectedCard={selectedCard}
            onSelectCard={handleCardSelect}
            onAddCardClick={() => togglePopup(setShowAddCardPopup)}
            onRemoveCardClick={() =>
              selectedCard
                ? togglePopup(setShowRemoveCardPopup)
                : alert("Please select a card to remove.")
            }
          />
          {showAddCardPopup && (
            <AddCardPopup
              onConfirm={handleAddCard}
              onClose={() => togglePopup(setShowAddCardPopup)}
            />
          )}
          {showRemoveCardPopup && (
            <RemoveCardPopup
              onConfirm={confirmRemoveCard} // Pass confirmRemoveCard to onConfirm
              onClose={() => togglePopup(setShowRemoveCardPopup)}
            />
          )}
        </div>
      )}
    </div>
  );
}

export default Home;
