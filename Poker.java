//author - Jacob Lutz

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Poker {
    public static void main(String[] args) {
        Deck cards = new Deck();
        cards.shuffle();
        for (int i = 0; i < cards.deckSize(); i++) {
            System.out.println(cards.getCard(i));
        }  
    }
}

class Deck extends Poker {
    List<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
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
        return rank;
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