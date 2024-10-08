package cleancode.minesweeper.tobe.io.sign;

import cleancode.minesweeper.tobe.cell.CellSnapshot;
import cleancode.minesweeper.tobe.cell.CellSnapshotStatus;

public class EmptyCellSignProvider implements CellSignProvidable{


  @Override
  public boolean supports(CellSnapshot cellSnapshot) {
    return cellSnapshot.isSameStatus(CellSnapshotStatus.EMPTY);
  }

  @Override
  public String provide(CellSnapshot cellSnapshot) {
    return EMPTY_SIGN;
  }
}
