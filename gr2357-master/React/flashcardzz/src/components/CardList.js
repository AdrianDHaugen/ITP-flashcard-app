import React, { useEffect } from "react";
import { getDecks } from "../api/axiosConfig";
import "./CardList.css";
import PropTypes from 'prop-types'; // Import PropTypes

// CardList component displays a list of cards and provides add/remove functionality.
function CardList({
  selectedDeckName,
  onSelectCard,
  onAddCardClick,
  onRemoveCardClick,
  selectedCard,
  cards,
  setCards,
}) {
  // Fetches card data on component mount or when selectedDeckName changes.
  useEffect(() => {
    async function fetchData() {
      if (selectedDeckName) {
        // Fetches decks, then filters to find the selected deck and sets its cards.
        const decks = await getDecks();
        const deck = decks.find((d) => d.name === selectedDeckName);
        setCards(deck.cardDeck);
      }
    }

    fetchData();
  }, [selectedDeckName, setCards]);

  // Handler for the remove card button.
  const handleRemoveClick = () => {
    if (selectedCard) {
      onRemoveCardClick(selectedCard);
    } else {
      alert("Please select a card to remove.");
    }
  };

  // Rendering the card list along with Add and Remove buttons.
  return (
    <div className="card-container">
      {cards.map((card) => (
        <div
          key={card.front}
          className={`clickable-card ${
            selectedCard === card.front ? "selected" : ""
          }`}
          onClick={() => onSelectCard(card.front)}
        >
          {card.front}
        </div>
      ))}
      <button onClick={onAddCardClick}>Add Card</button>
      <button onClick={handleRemoveClick}>Remove Card</button>
    </div>
  );
}
// Define prop types for the CardList component
CardList.propTypes = {
  selectedDeckName: PropTypes.string, // selectedDeckName can be a string
  onSelectCard: PropTypes.func.isRequired, // onSelectCard is a required function
  onAddCardClick: PropTypes.func.isRequired, // onAddCardClick is a required function
  onRemoveCardClick: PropTypes.func.isRequired, // onRemoveCardClick is a required function
  selectedCard: PropTypes.string, // selectedCard can be a string
  cards: PropTypes.arrayOf(
    PropTypes.shape({
      // cards is an array of objects with a specific shape
      front: PropTypes.string.isRequired, // Each object in cards should have a 'front' property that is a string
      // If there are more properties in the card objects, define them here as needed
    })
  ),
  setCards: PropTypes.func.isRequired, // setCards is a required function
};

// If any of the props are optional and should have default values, define them here
CardList.defaultProps = {
  selectedDeckName: "", // Default value if not provided
  selectedCard: "", // Default value if not provided
  cards: [], // Default empty array if not provided
};
export default CardList;
