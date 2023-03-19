package com.company;

// AR97903 - R.S.Lakmini

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuessingGame {
    int number = 1 + (int) (100 * Math.random()); // generate random number
    int count = 0; // get the number of attempts
    int score = 100; // get total score
    boolean end = false; // check whether the conditions for game ending are true or false

    public GuessingGame(){

        // panel
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 450, 40);
        panel.setBackground(Color.black);

        // Guessing Game label
        JLabel label = new JLabel("Guessing Game");
        label.setBounds(400, 50, 200, 60);
        label.setFont(new Font("Arial", Font.BOLD, 20));
        label.setForeground(Color.WHITE);

        // Label for instructions
        JLabel instructLabel = new JLabel("Please enter a number between 1-100.");
        instructLabel.setBounds(10, 20, 250, 70);
        JLabel instructLabel1 = new JLabel("You have 5 attempts to try.");
        instructLabel1.setBounds(10, 50, 250, 70);

        // Label for input
        JLabel inputLabel = new JLabel("Enter the number:");
        inputLabel.setBounds(10, 100, 150, 70);

        // Textfield for input
        JTextField inputText = new JTextField(15);
        inputText.setBounds(150, 120, 150, 30);

        // Message label
        JLabel hintLabel = new JLabel();
        hintLabel.setBounds(10, 130, 450, 100);

        // Guess Button
        JButton button = new JButton();
        button.setText("Guess");
        button.setBounds(30, 210, 150, 30);
        button.setFocusable(false);
        button.setFont(new Font("Comic Sans", Font.BOLD, 20));

        // Restart Button
        JButton button1 = new JButton();
        button1.setText("Restart");
        button1.setFocusable(false);
        button1.setBounds(230, 210, 150, 30);
        button1.setFont(new Font("Comic Sans", Font.BOLD, 20));

        // attempts number label
        JLabel tries = new JLabel();
        tries.setBounds(10, 250, 150, 30);

        // score count label
        JLabel scoreOutput = new JLabel();
        scoreOutput.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        scoreOutput.setVerticalAlignment(JLabel.BOTTOM);
        scoreOutput.setHorizontalAlignment((int) JLabel.LEFT_ALIGNMENT);

        // Create frame and add components
        JFrame frame = new JFrame("Guessing Game");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(450, 400);
        frame.setBackground(Color.blue);
        frame.add(panel);
        panel.add(label);
        frame.add(instructLabel);
        frame.add(instructLabel1);
        frame.add(inputLabel);
        frame.add(inputText);
        frame.add(hintLabel);
        frame.add(button);
        frame.add(button1);
        frame.add(tries);
        frame.add(scoreOutput);
        frame.setResizable(false);
        frame.setVisible(true);

        // Actions of the Guess Button
        button.addActionListener(new ActionListener() {
            String status = "";

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int guess = Integer.parseInt(inputText.getText());

                    count++;
                    tries.setText("Number of attempts: "+ Integer.toString(count));
                    if (count != 5) {
                        if (guess > 100 || guess < 0) {
                            status = "Please enter a number between 0-100";
                        } else if (guess == number - 1 || guess == number + 1) {
                            status = "You are too close";
                        } else if (guess < number) {
                            status = "The number is higher than "+guess;
                        } else if (guess > number) {
                            status = "The number is lower than "+guess;
                        } else {status = "Congratulations!! You won";
                            inputText.setEditable(false);
                            button.setEnabled(false);
                            end = true;
                        }
                        // calculate score
                        if (count == 1) {
                            score = 100;
                        } else {
                            score -= 25;
                        }
                    } else if (guess == number) { // if the user attempts the correct guess at 5th guess
                        status= "Congratulations!! You won";
                        inputText.setEditable(false);
                        button.setEnabled(false);
                        end = true;
                        score = 10; // give 10 scores for the 5th attempt
                    } else if (count == 5) { //  user could not guess the number
                        status = "The answer was " + number + ". You used all the 5 attempts. Better luck next time!";
                        inputText.setEditable(false);
                        button.setEnabled(false);
                        end = true;
                        score = 0; // fail
                    }
                    // display final score after the game ends
                    if (end == true) {
                        scoreOutput.setText("Score: "+ Integer.toString(score));
                    }
                    hintLabel.setText(status);
                    inputText.requestFocus();
                    inputText.selectAll();
                }
                // prevent application from crashing
                catch (Exception exception) {
                    hintLabel.setText("Empty values are not taken. Please enter a number"); // check whether the textfield is empty
                }
            }
        });

        // Actions of the Restart Button
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                number = 1 + (int) (100 * Math.random()); // generate new random number
                hintLabel.setText("");
                inputText.setText("");
                scoreOutput.setText("");
                tries.setText("");
                inputText.setEditable(true);
                button.setEnabled(true);
                inputText.requestFocus();
                count = 0;
                end = false;
            }
        });

    }

    public static void main(String[] args) {
        new GuessingGame();
    }
}
