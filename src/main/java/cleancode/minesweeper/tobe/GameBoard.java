package cleancode.minesweeper.tobe;

import java.util.Arrays;
import java.util.Random;

public class GameBoard {

  private static final int LAND_MINE_COUNT = 10;
  private final Cell[][] board;

  public GameBoard(int rowSize, int colSize) {
    board = new Cell[rowSize][colSize];
  }

  public void initializeGame() {
    int rowSIze = board.length;
    int colSIze = board[0].length;

    for (int row = 0; row < rowSIze; row++) {
      for (int col = 0; col < colSIze; col++) {
        board[row][col] = Cell.create();
      }
    }
    for (int i = 0; i < LAND_MINE_COUNT; i++) {
      int landMineCol = new Random().nextInt(colSIze);
      int landMineRow = new Random().nextInt(rowSIze);
      Cell landMineCell = findCell(landMineRow, landMineCol);
      landMineCell.turnOnLandMine();
    }
    for (int row = 0; row < rowSIze; row++) {
      for (int col = 0; col < colSIze; col++) {
        if (isLandMineCell(row, col)) {
          continue;
        }
        int count = countNearbyLandMines(row, col);
        Cell cell = findCell(row, col);
        cell.updateNearbyLandMineCount(count);
      }
    }
  }

  public int getRowSize() {
    return board.length;
  }

  public int getColSize() {
    return board[0].length;
  }

  public String getSign(int rowIndex, int colIndex) {
    Cell cell = findCell(rowIndex, colIndex);
    return cell.getSign();
  }

  private Cell findCell(int rowIndex, int colIndex) {
    return board[rowIndex][colIndex];
  }

  public void flag(int rowIndex, int colIndex) {
    Cell cell = findCell(rowIndex, colIndex);
    cell.flag();
  }

  public boolean isLandMineCell(int selectedRowIndex, int selectedColIndex) {
    return findCell(selectedRowIndex, selectedColIndex).isLandMine();
  }

  public void open(int rowIndex, int colIndex) {
    Cell cell = findCell(rowIndex, colIndex);
    cell.open();
  }

  public void openSurroundedCells(int row, int col) {
    if (row < 0 || row >= getRowSize() || col < 0 || col >= getColSize()) {
      return;
    }
    if (isOpenedCell(row, col)){
      return;
    }
    if (isLandMineCell(row, col)) {
      return;
    }

    open(row, col);

    if (doesCellHaveLandMineCount(row, col)) {
      return;
    }


    openSurroundedCells(row - 1, col - 1);
    openSurroundedCells(row - 1, col);
    openSurroundedCells(row - 1, col + 1);
    openSurroundedCells(row, col - 1);
    openSurroundedCells(row, col + 1);
    openSurroundedCells(row + 1, col - 1);
    openSurroundedCells(row + 1, col);
    openSurroundedCells(row + 1, col + 1);
  }


  public boolean isAllCellChecked() {
    return Arrays.stream(board)
        .flatMap(Arrays::stream)
        .allMatch(Cell::isChecked);
  }

  private boolean doesCellHaveLandMineCount(int row, int col) {
    return findCell(row, col).hasLandMineCount();
  }

  private boolean isOpenedCell(int row, int col) {
    return findCell(row, col).isOpened();
  }


  private int countNearbyLandMines(int row, int col) {
    int count = 0;
    int rowSIze = getRowSize();
    int colSIze = getColSize();

    if (row - 1 >= 0 && col - 1 >= 0 && isLandMineCell(row - 1, col - 1)) {
      count++;
    }
    if (row - 1 >= 0 && isLandMineCell(row - 1, col)) {
      count++;
    }
    if (row - 1 >= 0 && col + 1 < colSIze && isLandMineCell(row - 1, col + 1)) {
      count++;
    }
    if (col - 1 >= 0 && isLandMineCell(row, col - 1)) {
      count++;
    }
    if (col + 1 < colSIze && isLandMineCell(row, col + 1)) {
      count++;
    }
    if (row + 1 < rowSIze && col - 1 >= 0 && isLandMineCell(row + 1, col - 1)) {
      count++;
    }
    if (row + 1 < rowSIze && isLandMineCell(row + 1, col)) {
      count++;
    }
    if (row + 1 < rowSIze && col + colSIze < 10 && isLandMineCell(row + 1, col + 1)) {
      count++;
    }

    return count;
  }
}
