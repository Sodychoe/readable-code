package cleancode.minesweeper.tobe;

import cleancode.minesweeper.tobe.gameLevel.Begineer;
import cleancode.minesweeper.tobe.gameLevel.GameLevel;

public class GameApplication {
    public static void main(String[] args) {
        GameLevel gameLevel = new Begineer();

        Minesweeper minesweeper = new Minesweeper(gameLevel);
        minesweeper.run();
    }
}
