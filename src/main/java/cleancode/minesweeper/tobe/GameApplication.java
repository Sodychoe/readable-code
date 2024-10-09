package cleancode.minesweeper.tobe;

import cleancode.minesweeper.tobe.minesweeper.Minesweeper;
import cleancode.minesweeper.tobe.minesweeper.config.GameConfig;
import cleancode.minesweeper.tobe.minesweeper.gameLevel.Begineer;
import cleancode.minesweeper.tobe.minesweeper.io.ConsoleInputHandler;
import cleancode.minesweeper.tobe.minesweeper.io.ConsoleOutputHandler;

public class GameApplication {

  public static void main(String[] args) {

    GameConfig gameConfig = new GameConfig(
        new Begineer(),
        new ConsoleInputHandler(),
        new ConsoleOutputHandler()
    );

    Minesweeper minesweeper = new Minesweeper(gameConfig);
    minesweeper.initialize();
    minesweeper.run();
  }
}
