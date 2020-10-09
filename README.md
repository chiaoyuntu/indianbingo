# Let’s Play Indian Bingo
## Game Rules
1. Every player will have a ticket with numbers.
2. Numbers on a single ticket should be unique(ticket can’t have a number twice)
3. The Caller/Dealer calls the randomly generated number/cue one at a time.
4. A number can only be called once.
5. If a number called by Caller appears on your ticket, tap on the number to mark it.
6. Similarly, all other players mark off the numbers on their tickets as the numbers are
called by the Caller.
7. The winner being the first person to mark off all their numbers of a winning combination.
8. A player can have more than one winning combination on a single ticket.
9. If a particular winning combination has been successfully claimed, it cannot be claimed
again.

### Winning combinations
1. Full House - The ticket with all the numbers marked first
2. Early Five - The ticket with first five numbers marked
3. Top Line – The ticket with all numbers in top line.


## Building Projects with Gradle
### Prerequisites
* Gradle runs on all major operating systems and requires only a Java Development Kit version 8 or higher to run. 
* To check, run java -version.

### Install Gradle
Installing with a package manager **Homebrew** for macOS .

`brew install gradle`

### Execute the build
`./gradlew build`

### Run the application
`./gradlew run`

### How to play
1. Input stage and limitation
   1. User can enter the range **less than 90** from 1-n to generate random numbers for the tickets. 
      * Default: 1- 90
   2. User can enter the number of players **less than 9999**
   3. User can enter the ticket size. (N row x M column) **N and M should be less than 100** 
      * Default: 3X10
   4. User can enter numbers per row **less than M (number of column)** 
      * Default: 5
2. Number generation stage:
   * A random number would be generated one at a time by pressing key **'N'** on the keyboard.
3. Game over and print summary:
   1. Press "Q"
   2. All winning combinations are claimed.
   3. Board is full(Dealer called all numbers)