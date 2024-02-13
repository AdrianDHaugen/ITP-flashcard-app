import React from "react";
import { render, screen, fireEvent } from "@testing-library/react";
import "@testing-library/jest-dom";
import RenameDeckPopup from "../components/RenameDeckPopup"; // Importing the component to be tested

// Grouping tests for RenameDeckPopup component using describe
describe("RenameDeckPopup", () => {
  // Test to check if the component renders correctly
  it("renders correctly", () => {
    // Rendering the component with test props
    render(
      <RenameDeckPopup
        newDeckName="Old Deck Name"
        onNewDeckNameChange={() => {}}
        onConfirm={() => {}}
        onClose={() => {}}
      />
    );

    // Assertions to check if all elements are rendered
    expect(screen.getByText("Rename Deck")).toBeInTheDocument(); // Check if the title is rendered
    expect(screen.getByLabelText("New Deck Name:")).toBeInTheDocument(); // Check if the input field is rendered
    expect(screen.getByRole("button", { name: "Rename" })).toBeInTheDocument(); // Check if the 'Rename' button is rendered
    expect(screen.getByRole("button", { name: "Cancel" })).toBeInTheDocument(); // Check if the 'Cancel' button is rendered
  });

  // Test to check if typing in the input field works
  it("allows typing in the deck name input", () => {
    const handleNewDeckNameChange = jest.fn(); // Mock function for input change handler
    render(
      <RenameDeckPopup
        newDeckName="Old Deck Name"
        onNewDeckNameChange={handleNewDeckNameChange}
        onConfirm={() => {}}
        onClose={() => {}}
      />
    );

    // Simulating user typing in the input field
    fireEvent.change(screen.getByLabelText("New Deck Name:"), {
      target: { value: "New Deck Name" },
    });

    // Assertion to check if the change handler was called with the new value
    expect(handleNewDeckNameChange).toHaveBeenCalledWith("New Deck Name");
  });

  // Test to check if the onConfirm function is called when the 'Rename' button is clicked
  it("calls onConfirm when the Rename button is clicked", () => {
    const handleConfirm = jest.fn(); // Mock function for confirm handler
    render(
      <RenameDeckPopup
        newDeckName="Old Deck Name"
        onNewDeckNameChange={() => {}}
        onConfirm={handleConfirm}
        onClose={() => {}}
      />
    );

    // Simulating a click on the 'Rename' button
    fireEvent.click(screen.getByRole("button", { name: "Rename" }));

    // Assertion to check if the confirm handler was called
    expect(handleConfirm).toHaveBeenCalledTimes(1);
  });

  // Test to check if the onClose function is called when the 'Cancel' button is clicked
  it("calls onClose when the Cancel button is clicked", () => {
    const handleClose = jest.fn(); // Mock function for close handler
    render(
      <RenameDeckPopup
        newDeckName="Old Deck Name"
        onNewDeckNameChange={() => {}}
        onConfirm={() => {}}
        onClose={handleClose}
      />
    );

    // Simulating a click on the 'Cancel' button
    fireEvent.click(screen.getByRole("button", { name: "Cancel" }));

    // Assertion to check if the close handler was called
    expect(handleClose).toHaveBeenCalledTimes(1);
  });
});
