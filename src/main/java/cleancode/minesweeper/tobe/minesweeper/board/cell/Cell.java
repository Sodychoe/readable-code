package cleancode.minesweeper.tobe.minesweeper.board.cell;

public interface Cell {
  public boolean isLandMine();

  public boolean hasLandMineCount();

  public void flag();

  public void open();

  public boolean isChecked();

  public boolean isOpened();

  public CellSnapshot getSnapshot();
}
