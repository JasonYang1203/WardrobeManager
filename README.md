ToySaga — Java Graphics Animation Project

ToySaga is a small graphical Java application that simulates moving toys, interactions, and on-screen animations. 
It demonstrates object-oriented programming, inheritance, interfaces, dynamic behavior, and simple mouse-based interaction in a 2D environment.

The project features multiple toy types—cars, teddy bears, hoverballs—each with custom movement and drawing behavior.

------------------------------------------------------------
Features
------------------------------------------------------------
- Object-oriented design using inheritance and interfaces
- Multiple toy types (Car, TeddyBear, Hoverball) with unique movement patterns
- Mouse listener support for clicking and interacting with objects
- Drawable and Movable interfaces for clean class organization
- On-screen callouts and UI components such as SwitchButton
- Lightweight Java graphics and animation loop

------------------------------------------------------------
Tech Stack
------------------------------------------------------------
- Java (JDK 8+)
- Java AWT / basic graphics APIs
- Object-oriented programming
- Event-driven programming (mouse interactions)

------------------------------------------------------------
Project Structure
------------------------------------------------------------
ToySaga.java  
  Main class that initializes the application and handles the animation loop.

GraphicObject.java  
  Parent class for all drawable elements.

Drawable.java  
  Interface for any object that can be drawn on screen.

Movable.java  
  Interface for any object that can move or animate.

Car.java  
  Toy type with car-like movement behavior.

TeddyBear.java  
  Toy type with unique animations.

Hoverball.java  
  Spherical toy with bouncing/hovering motion.

Callout.java  
  UI element for showing on-screen messages.

MouseListener.java  
  Custom listener for handling mouse-based interaction.

SwitchButton.java  
  Simple UI toggle button for switching states.

------------------------------------------------------------
How to Run
------------------------------------------------------------
1. Make sure Java (JDK 8 or later) is installed.

2. Compile all Java files:
   javac *.java

3. Run the application:
   java ToySaga

4. Toys will appear on screen and animate. 
   Clicking on them will trigger interactions depending on toy type.

------------------------------------------------------------
My Role & Contributions
------------------------------------------------------------
I implemented:
- Several toy classes and their movement logic
- Core OOP structure using inheritance, interfaces, and polymorphism
- Animation updates and state changes inside ToySaga
- Interaction handling through custom mouse listener
- On-screen UI components such as callouts and switch buttons

This project demonstrates my experience with object-oriented Java, event-driven programming, 
and building interactive graphical applications.

------------------------------------------------------------
Learning Outcomes
------------------------------------------------------------
Through this project, I strengthened my skills in:
- Java OOP design (inheritance, interfaces, polymorphism)
- Building interactive 2D graphics applications
- Event-driven programming (mouse events)
- Organizing multi-class systems in Java
- Managing simple animation loops and movement logic

------------------------------------------------------------
License
------------------------------------------------------------
MIT License
