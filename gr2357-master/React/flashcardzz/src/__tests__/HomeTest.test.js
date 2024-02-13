import React from "react";
import { render, fireEvent, screen, waitFor } from "@testing-library/react";
import Home from "../pages/Home";
import * as router from "react-router-dom";
import * as axiosConfig from "../api/axiosConfig";

// Mocking react-router-dom and axiosConfig modules
jest.mock("react-router-dom", () => ({
  ...jest.requireActual("react-router-dom"),
  useNavigate: jest.fn(),
}));

jest.mock("../api/axiosConfig", () => ({
  getDecks: jest.fn(),
  getCardsByDeck: jest.fn(),
  createDeck: jest.fn(),
  removeDeck: jest.fn(),
  createCard: jest.fn(),
  removeCard: jest.fn(),
  renameDeck: jest.fn(),
}));

// Describe block for grouping tests related to Home component
describe("Home Component", () => {
  // Setup before each test
  beforeEach(() => {
    // Mock resolved values for getDecks and getCardsByDeck
    axiosConfig.getDecks.mockResolvedValue([
      { name: "Deck1" },
      { name: "Deck2" },
    ]);
    axiosConfig.getCardsByDeck.mockResolvedValue([
      { front: "Card 1 Front", back: "Card 1 Back" },
      { front: "Card 2 Front", back: "Card 2 Back" },
    ]);
    // Mocking useNavigate hook
    router.useNavigate.mockReturnValue(jest.fn());
    axiosConfig.removeDeck.mockResolvedValue(); 

  });

  // Test to verify navigation behavior when a deck is clicked
  it("navigates to the flashcard view of the selected deck", async () => {
    // Mocking the navigate function
    const navigate = jest.fn();
    router.useNavigate.mockReturnValue(navigate);

    // Rendering the Home component
    render(<Home />);

    // Simulating a click event on the 'Deck1' item in the deck list
    const deck1Button = await screen.findByText("Deck1");
    fireEvent.click(deck1Button);

    // Simulation a click event on the 'Go to Flashcards button'
    fireEvent.click(screen.getByText("Go to Flashcards"));

    // Wait for any asynchronous operations, state updates, or UI changes to complete
    await waitFor(() => {
      // Now check if navigate was called correctly
      expect(navigate).toHaveBeenCalledWith(`/flashcard/Deck1`);
    });
  });
  // Test to verify renaming a deck
  it("renames a deck successfully", async () => {
    // Rendering the Home component
    render(<Home />);

    // Find and click the deck to select it
    const deckButton = await screen.findByText("Deck1");
    fireEvent.click(deckButton);

    // Open the Rename Deck Popup
    fireEvent.click(screen.getByText("Rename Deck"));

    // Find the input field and simulate typing a new deck name
    const input = screen.getByLabelText("New Deck Name:");
    fireEvent.change(input, { target: { value: "New Deck Name" } });

    // Find and click the Rename button in the popup
    fireEvent.click(screen.getByRole("button", { name: "Rename" }));

    // Wait for any asynchronous operations to complete
    await waitFor(() => {
      // Check if the renameDeck function was called with correct arguments
      expect(axiosConfig.renameDeck).toHaveBeenCalledWith(
        "Deck1",
        "New Deck Name"
      );
    });
  });
});
