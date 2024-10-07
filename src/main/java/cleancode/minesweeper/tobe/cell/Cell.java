package cleancode.minesweeper.tobe.cell;

public interface Cell {

  String UNCHECKED_SIGN = "□";
  String FLAG_SIGN = "⚑";

  public boolean isLandMine();

  public boolean hasLandMineCount();

  public String getSign();

  public void flag();

  public void open();

  public boolean isChecked();

  public boolean isOpened();
}
