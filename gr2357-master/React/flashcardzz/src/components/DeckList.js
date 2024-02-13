import React from "react";
import "./DeckList.css";
import PropTypes from "prop-types"; // Import PropTypes

// DeckList component: Displays a list of decks with functionality to add, remove, and navigate to flashcards.
function DeckList({
  onDeckClick,
  decks,
  onAddDeckClick,
  onRemoveDeckClick,
  selectedDeck,
  navigateToFlashcards,
  onRenameDeckClick,
}) {
  return (
    <div className="deck-container">
      {/* Title for the deck list */}
      <h2>Decks</h2>

      {/* Mapping through each deck to create a list */}
      {decks.map((deck) => (
        <div
          key={deck.name}
          className={`deck-item ${
            deck.name === selectedDeck ? "selected" : ""
          }`}
          onClick={() => onDeckClick(deck.name)}
        >
          {deck.name}
          {/* Arrow indicating clickable items */}
          <span className="deck-arrow">→</span>
        </div>
      ))}

      {/* Buttons for adding, removing, and navigating to flashcards */}
      <button onClick={onAddDeckClick}>Add Deck</button>
      <button onClick={onRemoveDeckClick}>Remove Selected Deck</button>
      <button onClick={onRenameDeckClick}>Rename Deck</button>
      <button onClick={navigateToFlashcards}>Go to Flashcards</button>
    </div>
  );
}
// Define prop types for the DeckList component
DeckList.propTypes = {
  onDeckClick: PropTypes.func.isRequired, // Function to handle deck click
  decks: PropTypes.arrayOf(
    PropTypes.shape({
      // Array of deck objects
      name: PropTypes.string.isRequired, // Each deck object should have a name
    })
  ).isRequired,
  onAddDeckClick: PropTypes.func.isRequired, // Function to handle adding a deck
  onRemoveDeckClick: PropTypes.func.isRequired, // Function to handle removing a deck
  selectedDeck: PropTypes.string, // Name of the selected deck
  navigateToFlashcards: PropTypes.func.isRequired, // Function to navigate to flashcards
  onRenameDeckClick: PropTypes.func.isRequired, // Function to handle renaming a deck
};

// If any of the props are optional and should have default values, define them here
DeckList.defaultProps = {
  selectedDeck: "", // Default value if not provided
};
export default DeckList;
