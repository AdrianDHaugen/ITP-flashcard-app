import React from "react";
import { render, waitFor } from "@testing-library/react";
import CardList from "../components/CardList";
import { getDecks } from "../api/axiosConfig";

// Mocking the getDecks function from axiosConfig
jest.mock("../api/axiosConfig", () => ({
  getDecks: jest.fn(),
}));

// Grouping tests for CardList component
describe("CardList Component", () => {
  // Mock data for decks to use in the tests
  const mockDeckData = [
    { name: "Test Deck", cardDeck: [{ front: "Card 1" }, { front: "Card 2" }] },
  ];

  // Resetting mocks before each test
  beforeEach(() => {
    getDecks.mockResolvedValue(mockDeckData);
  });

  // Test to check if the component fetches and displays cards correctly
  it("fetches cards for the selected deck and displays them", async () => {
    // Mock function for setCards
    const setCards = jest.fn();

    // Rendering CardList component with props
    render(
      <CardList
        selectedDeckName="Test Deck"
        onSelectCard={() => {}}
        onAddCardClick={() => {}}
        onRemoveCardClick={() => {}}
        selectedCard={null}
        cards={[]}
        setCards={setCards}
      />
    );

    // Wait for asynchronous actions and asserting that getDecks was called
    // and setCards was called with the right arguments
    await waitFor(() => {
      expect(getDecks).toHaveBeenCalled();
      expect(setCards).toHaveBeenCalledWith(mockDeckData[0].cardDeck);
    });
  });
});
