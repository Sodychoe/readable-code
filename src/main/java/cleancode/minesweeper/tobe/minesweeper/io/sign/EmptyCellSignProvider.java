package cleancode.minesweeper.tobe.minesweeper.io.sign;

import cleancode.minesweeper.tobe.minesweeper.board.cell.CellSnapshot;
import cleancode.minesweeper.tobe.minesweeper.board.cell.CellSnapshotStatus;

public class EmptyCellSignProvider implements CellSignProvidable {


  @Override
  public boolean supports(CellSnapshot cellSnapshot) {
    return cellSnapshot.isSameStatus(CellSnapshotStatus.EMPTY);
  }

  @Override
  public String provide(CellSnapshot cellSnapshot) {
    return "";
  }
}
