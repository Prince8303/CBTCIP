import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GuessTheNumberGUI {
    private static int numberToGuess;
    private static int numberOfAttempts;
    private static final int MAX_ATTEMPTS = 10;
    private static int lowerBound = 1;
    private static int upperBound = 100;
    private static int score;

    public static void main(String[] args) {
        initializeGame();

        JFrame frame = new JFrame("Guess the Number Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 350);

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        frame.setVisible(true);
    }

    private static void initializeGame() {
        Random random = new Random();
        numberToGuess = random.nextInt(100) + 1;
        numberOfAttempts = 0;
        lowerBound = 1;
        upperBound = 100;
        score = 100;
    }

    private static void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel instructionLabel = new JLabel("Enter a number between 1 and 100:");
        instructionLabel.setBounds(10, 20, 250, 25);
        panel.add(instructionLabel);

        JTextField userGuess = new JTextField(20);
        userGuess.setBounds(10, 50, 165, 25);
        panel.add(userGuess);

        JButton guessButton = new JButton("Guess");
        guessButton.setBounds(10, 80, 80, 25);
        panel.add(guessButton);

        JLabel resultLabel = new JLabel("");
        resultLabel.setBounds(10, 110, 300, 25);
        panel.add(resultLabel);

        JLabel attemptsLabel = new JLabel("Attempts remaining: " + (MAX_ATTEMPTS - numberOfAttempts));
        attemptsLabel.setBounds(10, 140, 250, 25);
        panel.add(attemptsLabel);

        JLabel scoreLabel = new JLabel("Score: " + score);
        scoreLabel.setBounds(10, 170, 100, 25);
        panel.add(scoreLabel);

        JButton restartButton = new JButton("Restart");
        restartButton.setBounds(10, 200, 100, 25);
        restartButton.setVisible(false);
        panel.add(restartButton);

        guessButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String guessStr = userGuess.getText();
                try {
                    int guess = Integer.parseInt(guessStr);
                    numberOfAttempts++;
                    score = Math.max(0, 100 - (numberOfAttempts * 10)); // Decrease score by 10 points with each attempt

                    if (guess < lowerBound || guess > upperBound) {
                        resultLabel.setText("Please enter a number within the range.");
                    } else if (guess < numberToGuess) {
                        resultLabel.setText("Too low! Try again.");
                        lowerBound = Math.max(lowerBound, guess + 1);
                    } else if (guess > numberToGuess) {
                        resultLabel.setText("Too high! Try again.");
                        upperBound = Math.min(upperBound, guess - 1);
                    } else {
                        resultLabel.setText("Correct! You guessed it in " + numberOfAttempts + " attempts. Your score is " + score + ".");
                        guessButton.setEnabled(false);
                        restartButton.setVisible(true);
                    }
                    attemptsLabel.setText("Attempts remaining: " + (MAX_ATTEMPTS - numberOfAttempts));
                    instructionLabel.setText("Enter a number between " + lowerBound + " and " + upperBound + ":");
                    scoreLabel.setText("Score: " + score);

                    if (numberOfAttempts >= MAX_ATTEMPTS && guess != numberToGuess) {
                        resultLabel.setText("Game over! The number was " + numberToGuess + ". Your score is " + score + ".");
                        guessButton.setEnabled(false);
                        restartButton.setVisible(true);
                    }
                } catch (NumberFormatException ex) {
                    resultLabel.setText("Please enter a valid number.");
                }
            }
        });

        restartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                initializeGame();
                guessButton.setEnabled(true);
                userGuess.setText("");
                resultLabel.setText("");
                attemptsLabel.setText("Attempts remaining: " + MAX_ATTEMPTS);
                instructionLabel.setText("Enter a number between 1 and 100:");
                scoreLabel.setText("Score: " + score);
                restartButton.setVisible(false);
            }
        });
    }
}
