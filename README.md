# PocketBeasts

A simple Java card game project created for my second year university module Software Design Patterns, demonstrating object-oriented programming and design patterns with a graphical interface.

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
  
## The PocketBeasts

Ashen Lamb

<img width="200" height="200" alt="Ashen_Lamb" src="https://github.com/user-attachments/assets/83f154d4-bfd8-4d79-bb82-58a47cadbe86" />


Aqua Serpent

<img width="200" height="200" alt="Aqua_Serpent" src="https://github.com/user-attachments/assets/23d64b86-8282-4ad0-bcb0-f152762d8867" />


Moss Golem

<img width="200" height="200" alt="Moss_Golem" src="https://github.com/user-attachments/assets/63c9861c-80f2-4c72-8477-966193654874" />


Fire Whelp

<img width="200" height="200" alt="Fire_Whelp" src="https://github.com/user-attachments/assets/4eb13afd-08fd-435f-aa29-385610c9c41e" />


Thunder Hawk

<img width="200" height="200" alt="Thunder_Hawk" src="https://github.com/user-attachments/assets/876c6d3f-505c-4ea9-acf1-9719d3264f31" />


Frost Lynx

<img width="200" height="200" alt="Frost_Lynx" src="https://github.com/user-attachments/assets/99af412f-e792-42cc-baac-82d29ec470fd" />


Stone Rhino
Shadow Bat

Sun Sprite
Wind Sprite

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
- **Cleaner, more readable code:** The codebase is more modular and easier to understand.

## How to Run

- Open the project in your Java IDE.
- Run `Main.java` to start the GUI game.

## Testing

- JUnit tests are in the `test` folder.

## Screenshots
