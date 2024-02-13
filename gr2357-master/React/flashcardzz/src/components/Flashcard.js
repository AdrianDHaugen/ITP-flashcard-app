import React from "react";
import "./Flashcard.css"; // Importing CSS for styling the flashcard
import PropTypes from 'prop-types'; // Import PropTypes

// Flashcard component: Displays a card with front and back sides and flip animation
function Flashcard({ card, isFlipped, flipCard }) {
  // Rendering the flashcard component
  return (
    <div className="flashcard-container">
      {/* Flashcard wrapper with an onClick event to trigger the flip */}
      <div className="flashcard" onClick={flipCard}>
        {/* Card container that toggles the 'flipped' class based on isFlipped state */}
        <div className={isFlipped ? "card flipped" : "card"}>
          {/* Front of the card displaying card.front content */}
          <div className="card-front">{card.front}</div>
          {/* Back of the card displaying card.back content */}
          <div className="card-back">{card.back}</div>
        </div>
      </div>
    </div>
  );
}
Flashcard.propTypes = {
  card: PropTypes.shape({
    front: PropTypes.string, // Expected to be a string
    back: PropTypes.string, // Expected to be a string
  }).isRequired, // card is a required object with specified structure
  isFlipped: PropTypes.bool.isRequired, // isFlipped is a required boolean
  flipCard: PropTypes.func.isRequired, // flipCard is a required function
};
export default Flashcard;
