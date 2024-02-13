import React from "react";
import PropTypes from "prop-types"; // Import PropTypes

// The RenameDeckPopup component is responsible for displaying the popup to rename a deck.
// It takes in several props: the current new deck name, handlers for updating the name,
// confirming the rename operation, and closing the popup.

function RenameDeckPopup({
  newDeckName,
  onNewDeckNameChange,
  onConfirm,
  onClose,
}) {
  return (
    <div className="popup">
      {/* popup-inner div is used for styling and to contain all popup elements */}
      <div className="popup-inner">
        {/* Header for the popup */}
        <h2>Rename Deck</h2>

        {/* Label and input field for the new deck name */}
        <label htmlFor="newDeckName">New Deck Name:</label>
        <input
          type="text"
          id="newDeckName"
          value={newDeckName} // The value of the input is controlled by the newDeckName prop
          onChange={(e) => onNewDeckNameChange(e.target.value)} // Calls onNewDeckNameChange when the input changes, updating the new deck name in the parent component
        />

        {/* Buttons for confirming the rename operation and closing the popup */}
        <div className="popup-buttons">
          <button onClick={onConfirm}>Rename</button>{" "}
          {/* Button to confirm the rename operation */}
          <button onClick={onClose}>Cancel</button>{" "}
          {/* Button to close the popup without renaming */}
        </div>
      </div>
    </div>
  );
}
// Define prop types for the component
RenameDeckPopup.propTypes = {
  newDeckName: PropTypes.string.isRequired, // Specifies that newDeckName is a required string
  onNewDeckNameChange: PropTypes.func.isRequired, // Specifies that onNewDeckNameChange is a required function
  onConfirm: PropTypes.func.isRequired, // Specifies that onConfirm is a required function
  onClose: PropTypes.func.isRequired, // Specifies that onClose is a required function
};

export default RenameDeckPopup;
