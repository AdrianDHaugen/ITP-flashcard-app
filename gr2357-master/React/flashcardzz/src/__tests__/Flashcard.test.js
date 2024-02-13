import React from "react";
import { render, fireEvent, screen } from "@testing-library/react";
import Flashcard from "../components/Flashcard";

// Describe block for grouping tests related to Flashcard component
describe("Flashcard Component", () => {
  // Mock function to simulate the flipCard action
  const mockFlipCard = jest.fn();
  // Sample data for a flashcard
  const cardData = {
    front: "Front of the card",
    back: "Back of the card",
  };

  // Test to verify if the flipCard function is called when the flashcard is clicked
  it("calls flipCard when the flashcard is clicked", () => {
    // Rendering the Flashcard component with props
    render(
      <Flashcard card={cardData} isFlipped={false} flipCard={mockFlipCard} />
    );

    // Simulating a click on the flashcard
    fireEvent.click(screen.getByText("Front of the card"));

    // Expecting the mockFlipCard function to have been called as a result of the click
    expect(mockFlipCard).toHaveBeenCalled();
  });
});
