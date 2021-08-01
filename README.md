# "Dice Game"  



## How to Run 

This application is packaged as a jar. You run it using the ```java -jar``` command.

* Clone this repository 
* Make sure you are using JDK 1.8 
* You can build the project and run the tests by running ```mvn clean package```
* Once successfully built, you can run the service by below mentioned command:
* Service takes 2 command line arguments N and M 
* N = No of Players playing
* M = Min score required to Win
```
        java -jar target/dice-game-1.0.jar <N> <M>
eg:     java -jar target/dice-game-1.0.jar 3 12
```


Once the application runs you should see something like this

```
Player-3's turn. Press Enter to roll the dice
```

On Pressing the ENTER key, you will see the dice value followed by Rank table
```
Player-3 got 1 in diceRoll

=======================
Rank | Player   | Score
=======================
1    | Player-3 | 1
2    | Player-1 | 0
2    | Player-2 | 0
=======================

```
# Assumptions
* Two players will have same rank in case both have same score and both are still playing.
* Once player finished the game, its rank won't change even if other player scored more than this player in next turn(eg given below with N =3 M =12). 

```
=======================
Rank | Player   | Score
=======================
1    | Player-1 | 11
2    | Player-3 | 10
2    | Player-2 | 10
=======================

Player-3's turn. Press Enter to roll the dice
Player-3 got 2 in diceRoll

=======================
Rank | Player   | Score
=======================
1    | Player-3 | 12
2    | Player-1 | 11
3    | Player-2 | 10
=======================

Player-1's turn. Press Enter to roll the dice
Player-1 got 5 in diceRoll

=======================
Rank | Player   | Score
=======================
1    | Player-3 | 12
2    | Player-1 | 16
3    | Player-2 | 10
=======================

```
