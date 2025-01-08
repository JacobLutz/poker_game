import javax.swing.*;
import java.awt.*;

public class PokerGameGUI extends JFrame {
    private JPanel cardPanel;
    private JLabel playerLabel;

    public PokerGameGUI() {
        setTitle("Poker Game");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initializeComponents();
        setVisible(true);
    }

    private void initializeComponents() {
        cardPanel = new JPanel();
        playerLabel = new JLabel("Player's Hand:");
        
        cardPanel.setLayout(new GridLayout(1, 5)); // Display cards in a row
        JButton dealButton = new JButton("Deal Cards");
        dealButton.addActionListener(e -> dealCards());

        add(playerLabel, BorderLayout.NORTH);
        add(cardPanel, BorderLayout.CENTER);
        add(dealButton, BorderLayout.SOUTH);
    }

    private void dealCards() {
        cardPanel.removeAll();
        Deck deck = new Deck();
        deck.shuffle();
        for (int i = 0; i < 5; i++) {
            Card card = deck.dealCard();
            JLabel cardLabel = new JLabel(card.toString());
            cardPanel.add(cardLabel);
        }
        cardPanel.revalidate();
        cardPanel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PokerGameGUI gui = new PokerGameGUI();
            gui.setVisible(true);
        });
    }
}
