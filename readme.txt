Game Of Ur recreated in java!


As part of an assignment at Karel de Grote college from Belgium, we had to create a game in java in duo. We started from a given design document that contains how to play the game and the technical requirements. Our time limit was 6 weeks, we had to create the project while we were learning how javaFX worked. My partner and I understand most parts of the game, but my focus laid on the model part, while my partner's focus laid on the view part of the game.

Technical requirements include:
- Playable by two players (CHECK)
- Player can choose which piece to move, and can't perform illegal moves (CHECK)
- Decide score based on amount of moves needed to win (CHECK)
- Save 5 best scores to external file (writing to external file is bugged)
- Scores can be read and shown ingame (CHECK)
- Amount of moves has to be recorded, and it must be possible to undo your last move (CHECK, except small bug where if you take a piece of the enemy, and then undo, the enemies piece doesn't get restored)

Addional features (not required):
- Unfinished game can be saved and continued later on (You can save and load during the same session, but you lose your progress upon exit, the external writer/reader doesn't work)
- Basic AI for singleplayer (CHECK)

Bugs:
- Game crashes upon finishing
- Reported hard crash during the game (not been able to reproduce)
- Undo doesn't reset the opponents piece if it was taken
- External saving of highscores or board doesn't work

The game had to be created using the MVP pattern of Java: Model - View - Presenter.
The Model is smart, but ugly: it can't contain any javaFX code, and must perform most of the logic.
The View is pretty, but dumb: it doesn't contain any logic, but it does have a lot of javaFX to design the layout of the screens (views)
The Presenter is the middleman, he takes an event from the view, gives it through to the model, and then takes the answer of the model and gives it back to the view. Thus dividing those two components.

The feedback we have received over our game was mainly user usability, our background clashed with the board and the button pressing was tedious. The button pressing isn't something we can shorten, but we did remove our background already as it was very obnoxious.

Credits:
- William Van Bouwel : programming
- Anouk As : programming
- TeaJay : art (pieces, tiles and dices)

Folder extras contains:
	- GameOfUr.jar (game build)
	- wireframe at start of project
	- presentation of project for class
