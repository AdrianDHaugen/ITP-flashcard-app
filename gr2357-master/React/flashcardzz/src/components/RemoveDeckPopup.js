import React from "react";
import PropTypes from 'prop-types'; // Import PropTypes

// RemoveDeckPopup component: Displays a confirmation popup for removing a deck.
function RemoveDeckPopup({ onConfirm, onClose }) {
  // Rendering the popup UI
  return (
    <div className="popup">
      {/* Inner content of the popup */}
      <div className="popup-inner">
        {/* Title of the popup */}
        <h3>Remove Deck</h3>
        {/* Confirmation message asking the user if they are sure about the action */}
        <p>Are you sure you want to remove this deck?</p>
        {/* Button to confirm the removal action */}
        <button onClick={onConfirm}>Yes, Remove</button>
        {/* Button to cancel the action and close the popup */}
        <button onClick={onClose}>Cancel</button>
      </div>
    </div>
  );
}
RemoveDeckPopup.propTypes = {
  onConfirm: PropTypes.func.isRequired, // onConfirm is a required function
  onClose: PropTypes.func.isRequired, // onClose is a required function
};
export default RemoveDeckPopup;
