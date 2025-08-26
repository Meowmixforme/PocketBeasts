# PocketBeasts

A simple Java card game project, demonstrating object-oriented programming and design patterns with a graphical interface.

## Features

- Fully object-oriented architecture
- GUI implemented with Java Swing
- Uses Strategy, Decorator, Factory, and Observer patterns
- Easily extensible for new card types and effects

## Design Patterns Used

- **Strategy:** Card attack behaviour (direct, beast-vs-beast, etc).
- **Decorator:** Temporary effects like buffs or debuffs on cards.
- **Factory:** Creation of cards and decks.
- **Observer:** GUI updates when game state changes.

## Gameplay Overview

PocketBeasts is a two-player, turn-based card game. Each player controls a deck of beast cards and battles to reduce the opponent's health to zero.

### How to Play

1. **Start of Game:**  
   Each player draws an initial hand from their deck. Players take turns.
2. **Turn Structure:**  
   On your turn, you:
   - Gain mana (used to play cards).
   - Draw a card from your deck.
   - Play beast cards from your hand onto the battlefield, spending mana.
   - Attack with beasts:  
     - Some beasts can attack directly (target the enemy player).
     - Others engage in beast-vs-beast combat.
   - End your turn to pass play to your opponent.
3. **Attacking:**  
   - Each beast can attack once per turn, following its strategy (direct or beast attack).
   - Direct attacks damage the opponent's health.
   - Beast-vs-beast attacks can defeat opponent beasts and move them to the graveyard.

### End Game Conditions

- The game ends when **either player's health drops to zero or below**.
- The player with remaining health wins.
- If both players lose all health simultaneously or run out of resources (deck, hand, and in-play beasts), the game ends in a draw or by highest remaining health.

## Refactoring & Improvements

This application has been **refactored and improved** since its initial implementation in several ways:
- **Design patterns have been applied throughout:** The game logic is separated from the GUI using the Observer pattern, card behaviors use Strategy, and new cards can be created using Factory.
- **Code modularity:** Classes have been split into logical packages (model, controller, patterns, gui) for maintainability.
- **Extensibility:** New card types and attack strategies can easily be added without major rewrites.
- **GUI improvements:** The user interface updates automatically with game state changes, making the game flow intuitive and interactive.
- **Robust end-game handling:** The game now properly detects all win/loss/draw conditions.
- **Cleaner, more readable code:** The codebase is more modular and easier to understand for future development or university marking.

## How to Run

- Open the project in your Java IDE.
- Run `Main.java` to start the GUI game.

## Testing

- JUnit tests are in the `test` folder.

---

_This project was developed for a second year university module on Software Design Patterns._
