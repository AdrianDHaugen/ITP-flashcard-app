import React from "react";
import { render, fireEvent, screen } from "@testing-library/react";
import DeckList from "../components/DeckList";

// Describe block to group tests for DeckList component
describe("DeckList Component", () => {
  // Mock data for decks and mock functions for event handlers
  const mockDecks = [
    { name: "Deck 1" },
    { name: "Deck 2" },
    { name: "Deck 3" },
  ];

  const mockOnDeckClick = jest.fn();
  const mockOnAddDeckClick = jest.fn();
  const mockOnRemoveDeckClick = jest.fn();
  const mockNavigateToFlashcards = jest.fn();

  // Test to check if deck items are rendered correctly
  it("renders deck items", () => {
    render(
      <DeckList
        decks={mockDecks}
        onDeckClick={mockOnDeckClick}
        onAddDeckClick={mockOnAddDeckClick}
        onRemoveDeckClick={mockOnRemoveDeckClick}
        selectedDeck={null}
        navigateToFlashcards={mockNavigateToFlashcards}
      />
    );
  });

  // Test to verify onDeckClick function is called when a deck is clicked
  it("calls onDeckClick when a deck is clicked", () => {
    render(
      <DeckList
        decks={mockDecks}
        onDeckClick={mockOnDeckClick}
        onAddDeckClick={mockOnAddDeckClick}
        onRemoveDeckClick={mockOnRemoveDeckClick}
        selectedDeck={null}
        navigateToFlashcards={mockNavigateToFlashcards}
      />
    );

    // Simulate clicking the first deck
    fireEvent.click(screen.getByText("Deck 1"));
    expect(mockOnDeckClick).toHaveBeenCalledWith("Deck 1");
  });

  // Test to verify onAddDeckClick function is called when add deck button is clicked
  it("calls onAddDeckClick when add deck button is clicked", () => {
    render(
      <DeckList
        decks={mockDecks}
        onDeckClick={mockOnDeckClick}
        onAddDeckClick={mockOnAddDeckClick}
        onRemoveDeckClick={mockOnRemoveDeckClick}
        selectedDeck={null}
        navigateToFlashcards={mockNavigateToFlashcards}
      />
    );

    // Simulate clicking the add deck button
    fireEvent.click(screen.getByText("Add Deck"));
    expect(mockOnAddDeckClick).toHaveBeenCalled();
  });

  // Test to verify onRemoveDeckClick function is called when remove deck button is clicked
  it("calls onRemoveDeckClick when remove deck button is clicked", () => {
    render(
      <DeckList
        decks={mockDecks}
        onDeckClick={mockOnDeckClick}
        onAddDeckClick={mockOnAddDeckClick}
        onRemoveDeckClick={mockOnRemoveDeckClick}
        selectedDeck={"Deck 1"}
        navigateToFlashcards={mockNavigateToFlashcards}
      />
    );

    // Simulate clicking the remove deck button
    fireEvent.click(screen.getByText("Remove Selected Deck"));
    expect(mockOnRemoveDeckClick).toHaveBeenCalled();
  });

  // Test to verify navigateToFlashcards function is called when flashcards button is clicked
  it("calls navigateToFlashcards when flashcards button is clicked", () => {
    render(
      <DeckList
        decks={mockDecks}
        onDeckClick={mockOnDeckClick}
        onAddDeckClick={mockOnAddDeckClick}
        onRemoveDeckClick={mockOnRemoveDeckClick}
        selectedDeck={null}
        navigateToFlashcards={mockNavigateToFlashcards}
      />
    );

    // Simulate clicking the go to flashcards button
    fireEvent.click(screen.getByText("Go to Flashcards"));
    expect(mockNavigateToFlashcards).toHaveBeenCalled();
  });
});
