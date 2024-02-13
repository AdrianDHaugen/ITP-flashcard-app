import React from "react";
import { render, fireEvent, screen, waitFor } from "@testing-library/react";
import { MemoryRouter, Routes, Route } from "react-router-dom"; // Import Routes
import FlashcardPage from "../pages/FlashcardPage";
import { getCardsByDeck } from "../api/axiosConfig";

// Mock the axiosConfig module to return mock card data
jest.mock("../api/axiosConfig", () => ({
  getCardsByDeck: jest.fn(),
}));

describe("FlashcardPage Component", () => {
  // Mocked card data for testing
  const mockCards = [
    { id: 1, front: "Front 1", back: "Back 1" },
    { id: 2, front: "Front 2", back: "Back 2" },
  ];

  // Mock the navigate function
  const mockNavigate = jest.fn();

  beforeEach(() => {
    // Reset the mock functions and set up the mock axiosConfig function
    jest.clearAllMocks();
    getCardsByDeck.mockResolvedValue(mockCards);
  });

  it('navigates to the home page when "Go to home" button is clicked', async () => {
    // Render the component inside a MemoryRouter with initial route '/flashcard/Deck1'
    render(
      <MemoryRouter initialEntries={["/flashcard/Deck1"]}>
        <Routes>
          {/* Define a Route that renders the FlashcardPage component */}
          <Route
            path="/flashcard/:deckName"
            element={<FlashcardPage navigate={mockNavigate} />} // Pass mockNavigate as a prop
          />
        </Routes>
      </MemoryRouter>
    );

    // Simulate a click event on the 'Go to home' button
    await waitFor(() => {
      fireEvent.click(screen.getByText("Go to home")); // Find the button by its text content
    });
  });
});
