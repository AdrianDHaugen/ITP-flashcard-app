import React from "react";
import { render, fireEvent, screen } from "@testing-library/react";
import AddCardPopup from "../components/AddCardPopup";

// Describe block for grouping related tests of AddCardPopup component
describe("AddCardPopup Component", () => {
  // Mock functions to simulate onConfirm and onClose props
  const mockOnConfirm = jest.fn();
  const mockOnClose = jest.fn();

  // Test case to check if the component renders input fields and buttons
  it("renders input fields and buttons", () => {
    render(<AddCardPopup onConfirm={mockOnConfirm} onClose={mockOnClose} />);
    // The render method is called with the AddCardPopup component
  });

  // Test case to verify state updates on input field changes
  it("updates state on input change", () => {
    render(<AddCardPopup onConfirm={mockOnConfirm} onClose={mockOnClose} />);

    // Simulating user input for the card title and body fields
    fireEvent.change(screen.getByPlaceholderText("Card Title"), {
      target: { value: "New Title" },
    });
    fireEvent.change(screen.getByPlaceholderText("Card Body"), {
      target: { value: "New Body" },
    });

    // Assertions to check if the input values are updated correctly
    expect(screen.getByPlaceholderText("Card Title").value).toBe("New Title");
    expect(screen.getByPlaceholderText("Card Body").value).toBe("New Body");
  });

  // Test case to check if onConfirm is called with correct parameters when submitted
  it("calls onConfirm with title and body when submitted", () => {
    render(<AddCardPopup onConfirm={mockOnConfirm} onClose={mockOnClose} />);

    // Simulating user actions for submitting the form
    fireEvent.change(screen.getByPlaceholderText("Card Title"), {
      target: { value: "New Title" },
    });
    fireEvent.change(screen.getByPlaceholderText("Card Body"), {
      target: { value: "New Body" },
    });
    fireEvent.click(screen.getByText("Submit"));

    // Expectation that mockOnConfirm is called with the new title and body as arguments
    expect(mockOnConfirm).toHaveBeenCalledWith("New Title", "New Body");
  });

  // Test case to ensure onConfirm is not called when title or body is empty
  it("does not call onConfirm when title or body is empty", () => {
    render(<AddCardPopup onConfirm={mockOnConfirm} onClose={mockOnClose} />);

    // Simulating click action on the submit button without entering data
    fireEvent.click(screen.getByText("Submit"));

    // Expectation that mockOnConfirm is not called due to empty fields
    expect(mockOnConfirm).not.toHaveBeenCalled();
  });

  // Test case to verify onClose is called when the cancel button is clicked
  it("calls onClose when cancel is clicked", () => {
    render(<AddCardPopup onConfirm={mockOnConfirm} onClose={mockOnClose} />);

    // Simulating click action on the cancel button
    fireEvent.click(screen.getByText("Cancel"));

    // Expectation that mockOnClose is called when cancel is clicked
    expect(mockOnClose).toHaveBeenCalled();
  });
});
