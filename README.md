#  Snake Game Using Java

##  Project Overview
This project is a **classic Snake Game** implemented in **Java** with a graphical interface built using **Java Swing**.  
The player controls a snake that moves around the screen, eating food to grow longer while avoiding collisions with walls or itself.

---

##  Game Objective
The goal is to:
- Control the snake's movement.
- Eat food items to grow longer.
- Avoid collisions with the walls or the snake's own body.

---

##  Game Mechanics

### Snake Movement
- The snake moves continuously in one direction.
- The player changes the direction using:
  - ⬆ Up Arrow — Move Up
  - ⬇ Down Arrow — Move Down
  - ⬅ Left Arrow — Move Left
  - ➡ Right Arrow — Move Right

### Food
- Appears at random positions on the board.
- Eating food increases snake length.
- A new piece of food is generated after each one is eaten.

### Collision Detection
- The game ends if the snake collides with:
  - The wall boundaries.
  - Its own body.

---

##  Technical Implementation

### Language & Framework
- **Java**
- **Java Swing** for GUI

### Data Structure Used
- `ArrayList` to store the coordinates of each segment of the snake's body.
- Each element in the `ArrayList` represents one segment (as a `Point` object or custom coordinate class).

### Key Concepts
- Object-Oriented Programming (OOP)
- Event Handling with **KeyListener**
- Game loop for continuous movement and event updates
- Collision detection algorithms

---

##  How to Run

1. **Clone this repository**
   git clone https://github.com/varshith-kumar-2310/snake_Game_Using_JAVA.git
   cd snake_Game_Using_JAVA

2. **Compile the files**
   javac filename.java
3. **Run the game**
   java SnakeGame

## Author
**Varshith Kumar Thota**  
📧 Email: thotavarshith23@email.com  
🔗 [LinkedIn](https://www.linkedin.com/in/varshith-kumar-thota-812579242/) | [GitHub](https://github.com/varshith-kumar-2310)

---



