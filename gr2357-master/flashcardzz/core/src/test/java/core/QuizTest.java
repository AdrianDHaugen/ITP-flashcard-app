package core;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class QuizTest {

    private Deck testDeck;
    private Quiz quiz;

    @BeforeEach
    public void setUp() {
        Context.reset();
        testDeck = new Deck("test");
        testDeck.addCard(new Card("front1", "back1"));
        testDeck.addCard(new Card("front2", "back2"));
        testDeck.addCard(new Card("front3", "back3"));
        testDeck.addCard(new Card("front4", "back4"));
        testDeck.addCard(new Card("front5", "back5"));
        testDeck.addCard(new Card("front6", "back6"));
        testDeck.addCard(new Card("front7", "back7"));
        testDeck.addCard(new Card("front8", "back8"));
        quiz = new Quiz(testDeck);
    }

    @Test
    public void TestQuizzesAllCards() {
        ArrayList<String> questions = new ArrayList<>();
        for (int i = 0; i < quiz.getQuizLength(); i++) {
            questions.add(quiz.getQuestion());
            quiz.nextQuestion();
        }
        assertTrue(testDeck.getCardDeck().stream()
                .map(card -> card.getFront())
                .allMatch(card -> questions.contains(card)));
    }

    @Test
    public void TestGetPointsForCorrectAnswer() {
        for (int i = 0; i < quiz.getQuizLength(); i++) {
            String answer = testDeck.getCardDeck().stream()
                    .filter(card -> card.getFront().equals(quiz.getQuestion()))
                    .map(card -> card.getBack())
                    .findFirst().get();
            assertNotEquals(0, quiz.answerQuestion(answer));
            quiz.nextQuestion();
        }
    }

    @Test
    public void TestUniqeAlternatives() {
        for (int i = 0; i < quiz.getQuizLength(); i++) {
            assertFalse(hasDuplicateElements(quiz.getAlternatives()));
        }
    }

    private boolean hasDuplicateElements(List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.indexOf(list.get(i)) != i) {
                return true;
            }
        }
        return false;
    }
}
