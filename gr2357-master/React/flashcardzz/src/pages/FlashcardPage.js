import React, { useState, useEffect } from "react";
import { useParams, useNavigate } from "react-router-dom";
import Flashcard from "../components/Flashcard";
import { getCardsByDeck } from "../api/axiosConfig";
import "../components/Flashcard.css";

// FlashcardPage component: Displays flashcards from a selected deck.
function FlashcardPage() {
  // State and routing hooks.
  const { deckName } = useParams(); // Retrieves deck name from URL parameters.
  const [cards, setCards] = useState([]); // State to store cards.
  const [currentIndex, setCurrentIndex] = useState(0); // State to track the current card index.
  const [isFlipped, setIsFlipped] = useState(false); // State to track if the card is flipped.
  const navigate = useNavigate(); // Hook for navigating between routes.

  // Navigates to the home page.
  const navigateToHome = () => {
    navigate(`/`);
  };

  // Fetches cards when the component mounts or when deckName changes.
  useEffect(() => {
    async function fetchData() {
      if (deckName) {
        const result = await getCardsByDeck(deckName);
        setCards(result);
      }
    }
    fetchData();
  }, [deckName]);

  // Handles key presses for flipping and navigating cards.
  const handleKeyPress = (event) => {
    if (event.key === "Enter") {
      flipCard();
    }
    if (event.key === "ArrowRight") {
      goToNextCard();
    } else if (event.key === "ArrowLeft") {
      goToPrevCard();
    }
  };

  // Effect for adding and cleaning up the keydown event listener.
  useEffect(() => {
    window.addEventListener("keydown", handleKeyPress);

    return () => {
      window.removeEventListener("keydown", handleKeyPress);
    };
  }, [currentIndex, cards.length]);

  // Flips the current flashcard.
  const flipCard = () => {
    setIsFlipped(!isFlipped);
  };

  // Navigates to the next card.
  const goToNextCard = () => {
    setIsFlipped(false);
    setCurrentIndex((prevIndex) => (prevIndex + 1) % cards.length);
  };

  // Navigates to the previous card.
  const goToPrevCard = () => {
    setIsFlipped(false);
    setCurrentIndex(
      (prevIndex) => (prevIndex - 1 + cards.length) % cards.length
    );
  };

  // Render loading message if cards are not yet loaded.
  if (!cards.length) return <div>Loading cards...</div>;

  // Main component render.
  return (
    <div className="flashcard-page">
      <button className="home-button" onClick={navigateToHome}>
        Go to home
      </button>
      <h1 className="title">{`Cards in ${deckName}`}</h1>
      <div className="flashcard-container">
        {currentIndex < cards.length && (
          <Flashcard
            card={cards[currentIndex]}
            isFlipped={isFlipped}
            flipCard={flipCard}
          />
        )}
      </div>
      <div className="nav-buttons">
        <button className="nav-button" onClick={goToPrevCard}>
          &larr;
        </button>
        <button className="nav-button" onClick={goToNextCard}>
          &rarr;
        </button>
      </div>
    </div>
  );
}

export default FlashcardPage;
