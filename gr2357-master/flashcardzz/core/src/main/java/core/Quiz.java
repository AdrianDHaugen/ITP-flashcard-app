package core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Quiz {
    private List<MultipleChoiceQuestion> questions;
    private Deck deck;
    private int index = 0;
    private int score = 0;
    private int maxScore = 0;

    public Quiz(Deck deck) {
        this.deck = deck;
        questions = generateMultipleChoice(deck);
    }

    private List<MultipleChoiceQuestion> generateMultipleChoice(Deck deck) {
        int numOfAlternatives = 4;
        questions = new ArrayList<>();
        for (Card card : deck) {
            List<Card> shuffeld = deck.getCardDeck();
            System.out.println(card.getFront());
            Collections.shuffle(shuffeld);
            List<String> wrongAlternatives = shuffeld.stream()
                .filter(c -> !c.equals(card))
                .limit(numOfAlternatives-1)
                .map(c -> c.getBack())
                .toList();
            MultipleChoiceQuestion question = new MultipleChoiceQuestion(card, wrongAlternatives);
            questions.add(question);
            maxScore += question.getAttainablePoints();
        }
        Collections.shuffle(questions);
        return questions;
    }

    public int getQuizLength() {
        return deck.getCardDeckSize();
    }

    public String getQuestion() {
        return questions.get(index).getQuestion();
    }

    public List<String> getAlternatives() {
        return questions.get(index).getAlternatives();
    }

    public int answerQuestion(String answer) {
        int answerPoints = questions.get(index).answerQuestion(answer);
        score += answerPoints;
        return answerPoints;
    }

    public void nextQuestion() {
        index += 1;
    }

    public void prevQuestion() {
        index -= 1;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getScore() {
        return score;
    }

    public int getMaxScore() {
        return maxScore;
    }
}