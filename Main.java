import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class Main {
    static boolean gameComplete = false;

    static Scanner scanner = new Scanner(System.in);
    static Random random = new Random();

    static Card ace = new Card("ace", 1);
    static Card two = new Card("two", 2);
    static Card three = new Card("three", 3);
    static Card four = new Card("four", 4);
    static Card five = new Card("five", 5);
    static Card six = new Card("six", 6);
    static Card seven = new Card("seven", 7);   
    static Card eight = new Card("eight", 8);
    static Card nine = new Card("nine", 9);   
    static Card ten = new Card("ten", 10);
    static Card jack = new Card("jack", 10);
    static Card queen = new Card("queen", 10);
    static Card king = new Card("king", 10);
    static Card[] deck = {ace, two, three, four, five, six, seven, eight, nine, ten, jack, queen, king};

    static int playerCardIndex = 0;
    static int playerHandValue = 0;
    static ArrayList<String> playerHand = new ArrayList<String>();
    static boolean playerBust = false;
    static boolean wantToHit = false;

    static int dealerCardIndex = 0;
    static int dealerHandValue = 0;
    static ArrayList<String> dealerHand = new ArrayList<String>();
    static boolean dealerBust = false;

    public static int randomCardDraw() {
        int potentialCardIndex = random.nextInt(13);
        int potentialCardValue = deck[potentialCardIndex].value;

        while (deck[potentialCardIndex].numberLeftInDeck <= 0) {
            potentialCardIndex = random.nextInt(13);
            potentialCardValue = deck[potentialCardIndex].value;
        }
        deck[potentialCardIndex].numberLeftInDeck--;
        return potentialCardIndex;
    }
    public static void main(String[] args) {
        System.out.println("Hello! Welcome to a classic game of Blackjack!");
        System.out.println("Are you ready to get started? Please type 'y' for Yes, or 'n' for No.");
        String playerResponse = scanner.nextLine();
        boolean isReadyToPlay = playerResponse.charAt(0) == 'y' || playerResponse.charAt(0) == 'Y';

        if (!isReadyToPlay) {
            System.exit(0);
        }

        while (!gameComplete) {
            playerCardIndex = randomCardDraw();
            playerHand.add(deck[playerCardIndex].name);
            playerHandValue += deck[playerCardIndex].value;
            System.out.println("You drew a " + deck[playerCardIndex].name);
            System.out.println("Your current hand is now: " + playerHand.toString());


            dealerCardIndex = randomCardDraw();
            dealerHand.add(deck[dealerCardIndex].name);
            dealerHandValue += deck[dealerCardIndex].value;
            System.out.println("Dealer drew a " + deck[dealerCardIndex].name);
            System.out.println("Dealer's current hand is now: " + dealerHand.toString());

            playerBust = playerHandValue > 21;
            dealerBust = dealerHandValue > 21;

            if (playerBust && !dealerBust) {
                System.out.println("You busted! You lose :(");
                gameComplete = true;
            } else if (!playerBust && dealerBust) {
                System.out.println("Dealer busted. You win! :D");
                gameComplete = true;
            } else if (playerBust && dealerBust) {
                System.out.println("Both you and the dealer busted. Tie!");
                gameComplete = true;
            } else {
                System.out.println("Hit? (y/n)");
                wantToHit = scanner.nextLine().charAt(0) == 'y';
                if (!wantToHit) {
                    if (playerHandValue > dealerHandValue) {
                        System.out.println("Congratulations! Your hand value of " + playerHandValue + " was greater than the dealer's hand value of " + dealerHandValue + ". You won!");
                    } else if (playerHandValue < dealerHandValue) {
                        System.out.println("Unfortunately, Your hand value of " + playerHandValue + " was less than the dealer's hand value of " + dealerHandValue + ". You lost.");
                    } else {
                        System.out.println("Both your hand and the dealer's hand had the same value of " + playerHandValue + ". You tied!");
                    }
                    gameComplete = true;
                }
            }
        }
    }
    
}
