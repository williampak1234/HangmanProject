# Hangman

A command-line Hangman game in Java. Four word categories, three difficulty levels,
randomized word selection, ASCII art that updates with remaining chances, and replay
between rounds.

## Running it

```
cd src
javac HangmanGame.java Main.java
java Main
```

## How it works

Pick New Game, choose a category (Cars, Fast Food, Video Games, or Programming
Languages) and a difficulty (Easy, Medium, Hard). The game draws a random word from the
matching list and gives you six chances. After each round it asks whether you want to
play again.

## Notes on the design

The program is split across two classes. `Main` handles menu flow, category and
difficulty selection, input validation on those menus, and the replay loop. `HangmanGame`
holds everything about a single round: the word lists, the hidden word state, remaining
chances, incorrect guesses, and the guess-checking logic.

`Main` creates a new `HangmanGame` for every round rather than resetting an existing one.
Round state is all instance fields with initializers, so a fresh instance starts clean and
there is no reset method to forget to update when a new field is added.

Guess validation sits in one place. `checkGuess` decides whether a guess hit or missed,
updates the revealed letters, decrements chances, and records misses, so the main loop in
`run()` only sequences display, input, check, and win test.

## Known limitations

- The ASCII drawing currently starts complete and loses parts as chances run out, rather
  than building up.
- Guesses are case-sensitive, so a lowercase guess will not match a capitalized letter
  in words like `Toyota`.
- Repeated wrong guesses are not tracked, so guessing the same missed letter twice costs
  two chances.
- Multi-word entries like `Burger King` hide the space along with the letters.
