package cleancode.minesweeper.tobe.minesweeper.board.cell;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CellsTest {

  @Test
  @DisplayName("모든 지뢰 셀에 깃발이 꽂혀 있지 않으면 거짓을 반환한다")
  void isNotAllLandMineCellChecked(){
    // given
    Cell[][] cells = new Cell[10][10];


    // when
    int rowSize = cells.length;
    int colSize = cells[0].length;

    for (int i = 0; i < rowSize; i++) {
      for (int j = 0; j < colSize; j++) {
        LandMineCell cell = new LandMineCell();

        if (j % 2 == 0) {
          cell.open();
        } else {
          cell.flag();
        }
        cells[i][j] = cell;
      }
    }

    Cells cells1 = Cells.from(cells);


    // then
    assertThat(cells1.isALLChecked()).isFalse();
  }

  @Test
  @DisplayName("모든 지뢰 셀에 깃발이 꽂혀 있으면 참을 반환한다")
  void isAllLandMineCellChecked(){
    // given
    Cell[][] cells = new Cell[10][10];


    // when
    int rowSize = cells.length;
    int colSize = cells[0].length;

    for (int i = 0; i < rowSize; i++) {
      for (int j = 0; j < colSize; j++) {
        LandMineCell cell = new LandMineCell();
        cell.flag();
        cells[i][j] = cell;
      }
    }

    Cells cells1 = Cells.from(cells);


    // then
    assertThat(cells1.isALLChecked()).isTrue();
  }

  @Test
  @DisplayName("지뢰 셀이 아니라면 셀이 열렸으면 참을 반환한다")
  void isAllCellChecked(){
    // given
    Cell[][] cells = new Cell[10][10];


    // when
    int rowSize = cells.length;
    int colSize = cells[0].length;

    for (int i = 0; i < rowSize; i++) {
      for (int j = 0; j < colSize; j++) {
        if (i < rowSize / 2 && j < colSize / 2) {
          Cell cell = new EmptyCell();
          cell.open();
        }

        Cell cell = new NumberCell(1);
        cell.open();

        cells[i][j] = cell;
      }
    }

    Cells cells1 = Cells.from(cells);


    // then
    assertThat(cells1.isALLChecked()).isTrue();
  }

}