import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class driver {
    public static int playerMoney = 500;
    public static int casinoMoney;
    public static int betPub;
    

    public static final String ANSI_UNDERLINE = "\u001B[4m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String ANSI_RESET = "\u001B[0m";

    public static ArrayList<cardDeck> deck = new ArrayList<>();
    
    //public static ArrayList<cardDeck> shuffledDeck = new ArrayList<>();
    
    public static ArrayList<cardDeck> createDeck() {
        int suit = 2;
        String suitString = "Hearts";
       
        for (int z = 0; z < 4; z++) {
            for (int i = 1; i <= 14; i++) {
                cardDeck c1 = new cardDeck(suitString, i);
                deck.add(c1);
            }
            if (suit == 2) {
                suitString = "Diamonds";
            } 
            else if (suit == 3) {
                suitString = "Clubs";
            }
            else if (suit == 4) {
                suitString = "Spades";
            }
            suit++;
        }
        return deck;
    }

    
    public static ArrayList<cardDeck> shuffleDeck() {
        deck.clear();
        createDeck();
        Collections.shuffle(deck);
        return deck;
        
    }
    public static ArrayList<cardDeck> shuffledDeck = shuffleDeck();
    

    public static void gameDriver() {
        shuffleDeck();
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to the casino! Will you play fairly or cheat?");
        System.out.println("You can play Blackjack or Poker. Which one? \t1. Poker, \t2. Blackjack");
        int choice = input.nextInt();
        if (choice == 1) {
            System.out.println("Poker it is!");
        }
        else if (choice == 2) {
            System.out.println("Blackjack it is!");
            blackjackDriver();
            int i = 0;
            while (i != 2) {
                System.out.println("Do you want to play blackjack again? 1. Yes, 2. No");
                i = input.nextInt();
                if (i == 1) {
                    blackjackDriver();   
                }
            }
        }
        else {
            System.out.println("Invalid input gng :sob");
            gameDriver();
        }

    }
    public static void blackjackDriver() {
        Scanner input = new Scanner(System.in);
        System.out.println("do you 1. want to cheat, or 2. no cheat be good boy");
        int choice = input.nextInt();
        if (choice == 1) {
            System.out.println("So you choose to cheat ;). Don't get caught or you WILL lose all of your money and access into another casino");
        }
        else {
            System.out.println("tsktsktsk you choose to not cheat xd loser. ");
            blackjack();
        }
    }
    public static void blackjack() {
        playerCards.clear();
        Scanner input = new Scanner(System.in);
        int totalValue = 0;
        boolean bust = false;

        //System.out.println("Yo");
        dealCards(2);
        
        System.out.println("The dealers top card is a " + dealerCards.get(0));
        System.out.println("Your cards are " + playerCards);
        System.out.println("Time to bet! Minimum bet is $50 and you have $" + playerMoney + ". How much are you going to bet?");
        betPub = input.nextInt();
        while (betPub < 50) {
            System.out.println("Input was invalid. Pick another");
            betPub = input.nextInt();
            playerMoney -=betPub;
        }
        while (bust != true) {
            totalValue = totalValue("player", "blackjack");
            if (totalValue == 21) {
                System.out.println("Blackjack!");
                bust = false;
                break;
            }
            else if (totalValue > 21) {
                System.out.println("Bust!");
                bust = true;
                break;
            }
            System.out.println("Your cards are: " + playerCards);
            
            System.out.println(BLUE+"Your cards values add up to " + totalValue + " Do you 1. Hit, 2. Stand, 3. Bet more" + ANSI_RESET);
            int choice = input.nextInt();
            if (choice == 1) {
                drawCard();
                System.out.println("You hit and get a " + playerCards.get(0));
            }
            else if (choice == 2) {
                System.out.println("You stand at " + totalValue);
                break;
            }
            else if (choice == 3) {
                System.out.println("How much will you add to your bet?");
                int bet = input.nextInt();
                betPub = bet + betPub;
            }
            else {
                System.out.println("Input was invalid");
            }
        } // checlk who won
        if (bust == true) {
            System.out.println("You busted at " + totalValue + ". Now it's the dealers turn");
            dealerTurn("blackjack");
        }
        else {
            System.out.println("It's the dealers turn");
            dealerTurn("blackjack");
        }
        // after dealer turn, check who won and the bet they made. 
        if (bust == true) {
            System.out.println(RED+"Your value was " + totalValue + " and Dealers was: " + dealCardTotal+ANSI_RESET);
            System.out.println("Dealer won because u busted!\nYou lost your money!");
            playerMoney-=betPub;
        }
        else if (dealCardTotal > 21) {
            //System.out.println("Dealer busted! You win!");
            playerMoney += 2*betPub;
            System.out.println("Dealer busted! You win! You got " + 2*betPub + " dollars from that!");
        }
        else if (totalValue > dealCardTotal) {
            playerMoney += betPub*2;
            System.out.println(RED+"Your value was " + totalValue + " and Dealers was: " + dealCardTotal+ANSI_RESET);
            System.out.println("You won! You get your double your bet back! It is: " + 2*betPub + " back to you!");          
        }
        else if (totalValue == dealCardTotal) {
            playerMoney -= betPub;
            System.out.println(RED+"Your value was " + totalValue + " and Dealers was: " + dealCardTotal +ANSI_RESET);
            System.out.println("You tied in value but the dealer wins in a tie! You lost " + betPub + " dollars"); 
        }
        else if (dealCardTotal > totalValue) {
            System.out.println(RED+"Your value was " + totalValue + " and Dealers was: " + dealCardTotal+ANSI_RESET);
            System.out.println("You lost " + betPub + " dollars because you lost");     
        }
    }
     /*
    In blackjack, the dealer follows fixed rules rather than choosing whether to hit or stand:

        The dealer draws cards on 16 or less.
        The dealer usually stands on hard 17 or higher.

    */
    // make dealer turn, input for blackjack or poker
    public static int dealCardTotal;
    public static void dealerTurn(String game) {
        if (game.equals("blackjack")) {    
            System.out.println("The dealers top card is a " + dealerCards.get(0));
            dealCardTotal = totalValue("dealer", "blackjack");
            while (dealCardTotal <=16) {
                dealDrawCard();
                dealCardTotal = totalValue("dealer", "blackjack");
                System.out.println(dealCardTotal);
            }
            if (dealCardTotal == 21) {
                System.out.println("Dealer has blackjack!");
            }
            else if (dealCardTotal > 21) {
                System.out.println("Dealer busts!");
            }
            else {
                System.out.println("The dealer has to stand on " + dealCardTotal);
            }
        }
    }

    // universal deal, also, make the playerCards and dealerCards
    public static ArrayList<cardDeck> playerCards = new ArrayList<>();
    public static ArrayList<cardDeck> dealerCards = new ArrayList<>();

    public static void dealCards(int amount) {
        for (int i = 0; i < amount; i++) {
            drawCard();
            dealDrawCard();
        }
    }
    // make dealer turn, input for blackjack or poker
    

    // draw card functions for both dealer and player
    public static void drawCard() {
        playerCards.add(shuffledDeck.get(0));
        shuffledDeck.remove(0);
    }
    public static void dealDrawCard() {
        //System.out.println(shuffledDeck);
        dealerCards.add(shuffledDeck.get(0));
        shuffledDeck.remove(0);
    }
    public static int totalValue(String who, String game) {
        int total = 0;
        int currentValue =0;
        if (game.equals("blackjack")) {
            if (who.equals("player")) {
                for (int i = 0; i < playerCards.size(); i++) {
                    currentValue = playerCards.get(i).getValue();
                    if (currentValue >= 11) {
                        currentValue = 10;
                    }
                    else if (currentValue == 14) {
                        currentValue = 11;
                    }
                    total +=currentValue;
                }
                return total;
            }
            else if (who.equals("dealer")) {
                for (int i = 0; i < dealerCards.size(); i++) {
                    currentValue = dealerCards.get(i).getValue();
                    if (currentValue >= 11) {
                        currentValue = 11;
                    }
                    total +=currentValue;
                }
                return total;
            }
        }
        return total;
    }



    // main bs
    public static void main(String[]args) {
        gameDriver();
    } 
    
}
