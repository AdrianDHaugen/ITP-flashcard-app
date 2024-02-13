import React, { useState } from "react";
import "./Popup.css";
import PropTypes from 'prop-types'; // Import PropTypes


// AddDeckPopup component for adding a new deck.
function AddDeckPopup({ onConfirm, onClose }) {
  // State for deck title.
  const [deckTitle, setDeckTitle] = useState("");

  // Handler for changing deck title.
  const handleTitleChange = (event) => {
    setDeckTitle(event.target.value);
  };

  // Handler for submitting the new deck.
  const handleSubmit = () => {
    // Check if the title is not empty.
    if (deckTitle.trim()) {
      onConfirm(deckTitle); // Call onConfirm with the title.
      setDeckTitle(""); // Reset the title after submission.
      onClose(); // Close the popup.
    } else {
      // Alert if the title is empty.
      alert("Please enter a title");
    }
  };

  // JSX for the popup layout.
  return (
    <div className="popup">
      <div className="popup-inner">
        <h3>Add New Deck</h3>
        <input
          type="text"
          value={deckTitle}
          onChange={handleTitleChange}
          placeholder="Deck"
        />
        <button onClick={handleSubmit}>Submit</button>
        <button onClick={onClose}>Cancel</button>
      </div>
    </div>
  );
}
AddDeckPopup.propTypes = {
  onConfirm: PropTypes.func.isRequired, // onConfirm is a required function
  onClose: PropTypes.func.isRequired, // onClose is a required function
};

export default AddDeckPopup;
