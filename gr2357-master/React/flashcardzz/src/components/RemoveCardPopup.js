import React from "react";
import PropTypes from "prop-types"; // Import PropTypes

// RemoveDeckPopup component: Displays a confirmation popup for removing a card.
function RemoveCardPopup({ onConfirm, onClose }) {
  // JSX for rendering the popup
  return (
    <div className="popup">
      {/* Popup inner content container */}
      <div className="popup-inner">
        {/* Confirmation message */}
        <h3>Are you sure you want to remove this card?</h3>
        {/* Button to confirm the action (removing the card) */}
        <button onClick={onConfirm}>Yes, Remove</button>
        {/* Button to cancel the action and close the popup */}
        <button onClick={onClose}>Cancel</button>
      </div>
    </div>
  );
}
RemoveCardPopup.propTypes = {
  onConfirm: PropTypes.func.isRequired, // onConfirm is a required function
  onClose: PropTypes.func.isRequired, // onClose is a required function
};

export default RemoveCardPopup;
