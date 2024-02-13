import React from "react";
import { render, fireEvent, screen } from "@testing-library/react";
import RemoveCardPopup from "../components/RemoveCardPopup";

// Describe block for testing the RemoveCardPopup component
describe("RemoveCardPopup Component", () => {
  // Mock functions to simulate callbacks
  const mockOnConfirm = jest.fn();
  const mockOnClose = jest.fn();

  // Test case: Render the RemoveCardPopup component
  it("Renders the buttons", () => {
    render(<RemoveCardPopup onConfirm={mockOnConfirm} onClose={mockOnClose} />);
  });

  // Test case: Verify that onConfirm is called when the "Yes, Remove" button is clicked
  it("calls onConfirm when the remove button is clicked", () => {
    render(<RemoveCardPopup onConfirm={mockOnConfirm} onClose={mockOnClose} />);

    // Simulate a click on the "Yes, Remove" button
    fireEvent.click(screen.getByText("Yes, Remove"));

    // Check if the onConfirm function was called
    expect(mockOnConfirm).toHaveBeenCalled();
  });

  // Test case: Verify that onClose is called when the "Cancel" button is clicked
  it("calls onClose when the cancel button is clicked", () => {
    render(<RemoveCardPopup onConfirm={mockOnConfirm} onClose={mockOnClose} />);

    // Simulate a click on the "Cancel" button
    fireEvent.click(screen.getByText("Cancel"));

    // Check if the onClose function was called
    expect(mockOnClose).toHaveBeenCalled();
  });
});
