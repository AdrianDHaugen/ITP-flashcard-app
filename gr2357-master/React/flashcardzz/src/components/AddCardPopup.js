import React, { useState } from "react";
import "./Popup.css";
import PropTypes from 'prop-types'; // Import PropTypes


// AddCardPopup component for adding a new card.
function AddCardPopup({ onConfirm, onClose }) {
  // State for card title.
  const [cardTitle, setCardTitle] = useState("");
  // State for card body content.
  const [cardBody, setCardBody] = useState("");

  // Handler for changing card title.
  const handleTitleChange = (event) => {
    setCardTitle(event.target.value);
  };

  // Handler for changing card body.
  const handleBodyChange = (event) => {
    setCardBody(event.target.value);
  };

  // Handler for submitting the new card.
  const handleSubmit = () => {
    // Check if both title and body are not empty.
    if (cardTitle.trim() && cardBody.trim()) {
      // Call onConfirm with title and body, then reset the states.
      onConfirm(cardTitle, cardBody);
      setCardTitle("");
      setCardBody("");
    } else {
      // Alert if either title or body is empty.
      alert("Please enter both a title and body for the card.");
    }
  };

  // JSX for the popup layout.
  return (
    <div className="popup">
      <div className="popup-inner">
        <h3>Add New Card</h3>
        <input
          type="text"
          value={cardTitle}
          onChange={handleTitleChange}
          placeholder="Card Title"
        />
        <textarea
          value={cardBody}
          onChange={handleBodyChange}
          placeholder="Card Body"
        />
        <button onClick={handleSubmit}>Submit</button>
        <button onClick={onClose}>Cancel</button>
      </div>
    </div>
  );
}
AddCardPopup.propTypes = {
  onConfirm: PropTypes.func.isRequired, // onConfirm is a required function
  onClose: PropTypes.func.isRequired, // onClose is a required function
};

export default AddCardPopup;
