//author - Jacob Lutz

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class Poker {
    public static void main(String[] args) {
        Deck cards = new Deck();
        cards.shuffle();
        for(int i = 0; i < 52; i++) {
            System.out.println(cards.getCard(i));
        }
        Player p1 = new Player("jacob", 100000, true, null, null);
        Player p2 = new Player("camryn", 100000, true, null, null);
        p1.setCard1(cards.dealCard());
        p2.setCard1(cards.dealCard());
        p1.setCard2(cards.dealCard());
        p2.setCard2(cards.dealCard());
        System.out.println("------------");
        p1.showHand();
        p2.showHand();
        cards.dealBoard();
        cards.evaluateHand(p2.getHand(p2.card1, p2.card2), cards.getCommunityCards());
        cards.countSuit(cards.getCommunityCards());
    }
}

class Deck extends Poker {
    Stack<Card> cards;
    ArrayList<Card> communityCards;
    ArrayList<Card> combinedCards;
    public Deck() {
        cards = new Stack<>();
        for(String suit : new String[]{"Hearts", "Diamonds", "Clubs", "Spades"}){
            for (int rank = 1; rank <= 13; rank++) {
                cards.add(new Card(suit, rank));
            }
        }
    }
    public Card getCard(int index) {
        return cards.get(index);
    }

    public int deckSize() {
        return cards.size();
    }

    public void shuffle() {
        Random random = new Random();
        for (int i = 0; i < cards.size(); i++) {
            int j = random.nextInt(cards.size()); // Random index
            // Swap cards[i] with cards[j]
            Card temp = cards.get(i);
            cards.set(i, cards.get(j));
            cards.set(j, temp);
        }
    }

    public static void evaluateHand(ArrayList playerCards, ArrayList community) {

        ArrayList<Card> combinedCards = new ArrayList<>(playerCards);
        combinedCards.addAll(community);

        System.out.println("\nCombined Cards: ");
        for(int i = 0; i < 7; i++) {
            System.out.println(combinedCards.get(i));
        }
    }//end evaluateHand

    public void countSuit(ArrayList<Card> hand) {
        int diamondCount = 0;
        int heartCount = 0;
        int spadeCount = 0;
        int clubCount = 0;
        for (Card card : hand) {
            if(card.getSuit() == "Diamond's") {
                diamondCount++;
            }
            if(card.getSuit() == ("Heart's")) {
                heartCount++;
            }
            if(card.getSuit().equalsIgnoreCase("Club's")) {
                clubCount++;
            }
            if(card.getSuit().equalsIgnoreCase("Spade's")) {
                spadeCount++;
            }
        }
        System.out.println("Diamond Count: " + diamondCount + "\nHeart Count: " + heartCount + "\nClub Count: " + clubCount + "\n Spade Count: " + spadeCount);
    }



    public ArrayList getCommunityCards() {
        return communityCards;
    }

    public Card dealCard() {
        return cards.pop();        
    }

    public void dealFlop() {
        for (int i = 51; i > 48; i--) {
            System.out.println(cards.pop());
        }
    }
   
    public void dealBoard() {
        communityCards = new ArrayList<>();
        System.out.println("Board: ");
        for(int i = 0; i <5; i++) {
            communityCards.add(cards.peek());
            System.out.println(cards.pop());
        }
    }
}

class Card{
    private String suit;
    private int rank;
    private String rankName;

    public Card(String suit, int rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public String getSuit() {
        return suit;
    }
    
    public int getRank() {
        return this.rank;
    }

    @Override
    public String toString() {
        if(rank == 1 || rank >= 11) {
            switch (rank) {
                case 1: 
                    rankName = "Ace";
                    break;
                case 11:
                    rankName = "Jack";
                    break;
                case 12:
                    rankName = "Queen";
                    break;
                case 13: 
                    rankName = "King";
                    break;
                default:
                rankName = String.valueOf(rank);
            }
            return rankName + " of " + suit;
        }
        else{
            return rank + " of " + suit;
        }
    }
}

class Player {
    private String name;
    private int chips;
    private boolean inHand = true;
    public Card card1;
    public Card card2;

    public Player(String name, int chips, boolean inHand, Card card1, Card card2) {
        this.name = name;
        this.chips = chips;
        this.inHand = inHand;
        this.card1 = null;
        this.card2 = null;
    }

    public void fold() {
        this.inHand = false;
    }

    public void setCard1(Card card1) {
        this.card1 = card1;

    }
    public void setCard2(Card card2) {
        this.card2 = card2;
    }

    public ArrayList getHand(Card card1, Card card2) {
        ArrayList<Card> playersHand = new ArrayList<>();
        playersHand.add(card1);
        playersHand.add(card2);
        return playersHand;
    }


    public void showHand() {
        System.out.println(this.name + "'s cards: ");
        System.out.println(card1);
        System.out.println(card2);
    }
}