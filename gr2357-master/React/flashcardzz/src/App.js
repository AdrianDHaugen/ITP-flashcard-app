import React from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import "./App.css";
import Home from "./pages/Home";
import FlashcardPage from "./pages/FlashcardPage";

function App() {
  // Main app component responsible for routing.
  return (
    <div>
      {/* BrowserRouter component wraps the entire routing structure. */}
      <BrowserRouter>
        {/* Routes component contains all Route components. */}
        <Routes>
          {/* Route for the home page. */}
          {/* 'index' attribute makes this route the default landing page. */}
          <Route index element={<Home />} />

          {/* Route for the FlashcardPage, with dynamic segment ':deckName'. */}
          {/* This allows passing the deck name as a parameter to the FlashcardPage component. */}
          <Route path="/flashcard/:deckName" element={<FlashcardPage />} />
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
