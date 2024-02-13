import React from "react";
import { render, fireEvent, screen } from "@testing-library/react";
import AddDeckPopup from "../components/AddDeckPopup";

// Test suite for AddDeckPopup component
describe("AddDeckPopup Component", () => {
  // Mock functions to simulate onConfirm and onClose props
  const mockOnConfirm = jest.fn();
  const mockOnClose = jest.fn();

  // Clear all mocks before each test
  beforeEach(() => {
    jest.clearAllMocks();
  });

  // Test to check if the component updates its state on input change
  it("updates state on input change", () => {
    render(<AddDeckPopup onConfirm={mockOnConfirm} onClose={mockOnClose} />);

    // Simulate user input
    fireEvent.change(screen.getByPlaceholderText("Deck"), {
      target: { value: "New Deck" },
    });

    // Check if the input field value is updated
    expect(screen.getByPlaceholderText("Deck").value).toBe("New Deck");
  });

  // Test to verify if onConfirm and onClose are called with a valid title submission
  it("calls onConfirm and onClose when a valid title is submitted", () => {
    render(<AddDeckPopup onConfirm={mockOnConfirm} onClose={mockOnClose} />);

    // Simulate entering a title and submitting
    fireEvent.change(screen.getByPlaceholderText("Deck"), {
      target: { value: "New Deck" },
    });
    fireEvent.click(screen.getByText("Submit"));

    // Check if onConfirm was called with the new deck title and onClose was also called
    expect(mockOnConfirm).toHaveBeenCalledWith("New Deck");
    expect(mockOnClose).toHaveBeenCalled();
  });

  // Test to ensure onConfirm is not called when the title is empty
  it("does not call onConfirm when title is empty", () => {
    // Mock window.alert function
    window.alert = jest.fn();

    render(<AddDeckPopup onConfirm={mockOnConfirm} onClose={mockOnClose} />);

    // Simulate clicking submit without entering a title
    fireEvent.click(screen.getByText("Submit"));

    // Check that onConfirm wasn't called and alert was triggered with the appropriate message
    expect(mockOnConfirm).not.toHaveBeenCalled();
    expect(window.alert).toHaveBeenCalledWith("Please enter a title");
  });

  // Test to verify if onClose is called when the cancel button is clicked
  it("calls onClose when cancel is clicked", () => {
    render(<AddDeckPopup onConfirm={mockOnConfirm} onClose={mockOnClose} />);

    // Simulate clicking the cancel button
    fireEvent.click(screen.getByText("Cancel"));

    // Check if onClose was called
    expect(mockOnClose).toHaveBeenCalled();
  });
});
