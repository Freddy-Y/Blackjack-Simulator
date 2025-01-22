public class Card {
    final int value;
    final String name;
    int numberLeftInDeck = 4;

    public Card(String inputName, int inputValue) {
        value = inputValue;
        name = inputName;
    }
}
