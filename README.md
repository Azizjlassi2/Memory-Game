# Memory Game

## Description

Memory Game is a simple card-based game developed in Java using Swing. The objective of the game is to uncover all the cards in ascending order within a given time limit.

## Features

- User-friendly graphical interface using Java Swing.
- Configurable number of cards (default: 10 cards).
- Timer-based gameplay (default: 60 seconds).
- Cards are shuffled and hidden initially.
- Players must uncover cards in ascending order.
- Automatic reset of the game if the time runs out or a mistake is made.

## How to Play

1. When you start the game, you will be prompted to enter the number of cards (an even number).
2. Cards will be displayed face down on the screen.
3. Click on a card to reveal its value.
4. Reveal cards in ascending order starting from `1`.
5. If you reveal a card out of order, all cards will be hidden, and you will need to start over.
6. The game ends when you successfully uncover all the cards in ascending order or when the timer reaches zero.

## Requirements

- Java Development Kit (JDK) 8 or higher.

## How to Run

1. Clone or download the source code.
2. Compile the `MemoryGame.java` file using the following command:
   ```
   javac MemoryGame.java
   ```
3. Run the program using:
   ```
   java MemoryGame
   ```

## Code Overview

### Main Components

1. **MemoryGame Class**: The main class that initializes the game window, timer, and game logic.
2. **CardClickListener**: Inner class handling the click events of the cards.

### Key Methods

- `initializeGame()`: Sets up the main game window and layout.
- `setupGame()`: Generates shuffled cards and displays them.
- `startTimer()`: Starts the countdown timer.
- `resetGame()`: Resets the game state after a win, loss, or error.
- `hideAllCards()`: Resets all cards to their hidden state.

## Future Improvements

- Add difficulty levels with varying timer durations.
- Implement a scoring system.
- Enhance UI with better graphics.
- Support multiplayer mode.
