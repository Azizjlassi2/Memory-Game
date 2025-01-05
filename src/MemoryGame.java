import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MemoryGame {

    private JFrame frame;
    private JPanel cardPanel;
    private JLabel timerLabel;
    private Timer timer;
    private List<JButton> cards;
    private List<Integer> cardValues;
    private int timeRemaining = 60;
    private int currentIndex = 0;

    public MemoryGame() {
        initializeGame();
    }

    private void initializeGame() {
        frame = new JFrame("Memory Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        timerLabel = new JLabel("Time Remaining: 60", SwingConstants.CENTER);
        timerLabel.setFont(new Font("Arial", Font.BOLD, 16));
        frame.add(timerLabel, BorderLayout.NORTH);

        cardPanel = new JPanel();
        frame.add(cardPanel, BorderLayout.CENTER);

        setupGame();

        frame.setVisible(true);
    }

    private void setupGame() {
        String input = JOptionPane.showInputDialog(frame, "Enter number of cards (even number):", "10");
        int numCards;
        try {
            numCards = Integer.parseInt(input);
            if (numCards <= 0 || numCards % 2 != 0)
                throw new NumberFormatException();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Invalid input. Using default value: 10.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            numCards = 10;
        }

        cardPanel.setLayout(new GridLayout(numCards / 2, 2));
        cards = new ArrayList<>();
        cardValues = new ArrayList<>();

        for (int i = 1; i <= numCards; i++) {
            cardValues.add(i);
        }
        Collections.shuffle(cardValues);

        for (int i = 0; i < numCards; i++) {
            JButton cardButton = new JButton("?");
            cardButton.setFont(new Font("Arial", Font.BOLD, 20));
            cardButton.addActionListener(new CardClickListener(i));
            cards.add(cardButton);
            cardPanel.add(cardButton);
        }

        startTimer();
    }

    private void startTimer() {
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeRemaining--;
                timerLabel.setText("Time Remaining: " + timeRemaining);

                if (timeRemaining <= 0) {
                    timer.stop();
                    JOptionPane.showMessageDialog(frame, "Time's up! Restarting game.", "Game Over",
                            JOptionPane.INFORMATION_MESSAGE);
                    resetGame();
                }
            }
        });
        timer.start();
    }

    private void resetGame() {
        frame.dispose();
        new MemoryGame();
    }

    private class CardClickListener implements ActionListener {
        private final int index;

        public CardClickListener(int index) {
            this.index = index;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton clickedCard = cards.get(index);

            if (!clickedCard.getText().equals("?")) {
                return; // Card already revealed
            }

            clickedCard.setText(String.valueOf(cardValues.get(index)));

            if (cardValues.get(index) == currentIndex + 1) {
                currentIndex++;
                if (currentIndex == cardValues.size()) {
                    timer.stop();
                    JOptionPane.showMessageDialog(frame, "Congratulations! You've won!", "Victory",
                            JOptionPane.INFORMATION_MESSAGE);
                    resetGame();
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Wrong card! Resetting cards.", "Error",
                        JOptionPane.WARNING_MESSAGE);
                hideAllCards();
                currentIndex = 0;
            }
        }
    }

    private void hideAllCards() {
        for (JButton card : cards) {
            card.setText("?");
        }
    }

    public static void main(String[] args) {
        new MemoryGame();
    }
}
