package core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MultipleChoiceQuestion extends Question {
    private int attainablePoints;
    private Card questionCard;
    private List<String> alternatives;
    private final int questionType = Question.MULTIPLE_CHOICE;

    MultipleChoiceQuestion(Card questionCard, Collection<String> wrongAlternatives) {
        attainablePoints = 1;
        this.questionCard = questionCard;
        alternatives = new ArrayList<>(wrongAlternatives);
        alternatives.add(questionCard.getBack());
        Collections.shuffle(alternatives);
    }

    public String getQuestion() {
        return questionCard.getFront();
    }

    public ArrayList<String> getAlternatives() {
        return new ArrayList<>(alternatives);
    }

    public int getAttainablePoints() {
        return attainablePoints;
    }

    public int getQuestionType() {
        return this.questionType;
    }

    /**
     * Answer the question by providing a text answer
     * 
     * @param answer
     * @return the amout of points scored for the answer
     */
    public int answerQuestion(String answer) {
        if (answer == questionCard.getBack()) {
            return attainablePoints;
        }
        return 0;
    }
}
