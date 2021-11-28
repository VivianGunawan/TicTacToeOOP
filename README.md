# TicTacToeOOP
Object Oriented Design for Scalable TicTacToe

Instructions on compilation and execution:
Compilation:
    On your terminal(if you are using a mac)
    javac GameCenter.java
    java GameCenter
Execution:
    When prompted for an input, the datatype are in bracket with their range, 
    there is no guarantee program will function as expected if users fail to input correctly.
    ^It is not that hard to follow instructions and not purposely broke someone's code

Usability of classes:
1. Board -> a class made up of Cell representing a physical board
2. Cell -> a class representing a physical cell of a board
3. Checker -> a class representing a physical checker
4. Player -> a class representing a physical player, he/she/it(and all other pronouns) can "grab" a checker
5. ScoreSheet -> a class representing a physical score sheet to keep track of wins, losses, ties and games played
6. TTTGameEngine -> plays a game of Tic Tac Toe
7. OCGameEngine -> plays a game of Order and Chaos
8. GameCenter -> allows you to play multiple games

Design Choices:
- Order and Chaos additional rule not implemented
- 10 Player Maximum in Game Center
- Tic Tac Toe board size maximum of 20
