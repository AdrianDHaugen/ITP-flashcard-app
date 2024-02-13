package core; // Declares the package name as 'core'

public class Card {
    // Private variables to hold the front and back text of the card
    private String front;
    private String back;

    // Default constructor
    public Card() {
    }

    // Constructor that initializes the card with front and back text
    public Card(String front, String back) {
        this.front = front;
        this.back = back;
    }

    // Getter method for the front text of the card
    public String getFront() {
        return front;
    }

    // Setter method for the front text of the card
    public void setFront(String front) {
        this.front = front;
    }

    // Getter method for the back text of the card
    public String getBack() {
        return back;
    }

    // Setter method for the back text of the card
    public void setBack(String back) {
        this.back = back;
    }
}
