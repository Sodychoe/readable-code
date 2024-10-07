package cleancode.minesweeper.tobe;

import cleancode.minesweeper.tobe.cell.Cell;
import cleancode.minesweeper.tobe.cell.EmptyCell;
import cleancode.minesweeper.tobe.cell.LandMineCell;
import cleancode.minesweeper.tobe.cell.NumberCell;
import cleancode.minesweeper.tobe.gameLevel.GameLevel;
import cleancode.minesweeper.tobe.position.CellPosition;
import cleancode.minesweeper.tobe.position.RelativePosition;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class GameBoard {

  private final Cell[][] board;
  private final int landMineCount;

  public GameBoard(GameLevel gameLevel) {
    int rowSize = gameLevel.getRowSize();
    int colSize = gameLevel.getColSize();
    board = new Cell[rowSize][colSize];

    this.landMineCount = gameLevel.getLandMineCount();
  }

  public void initializeGame() {
    int rowSIze = getRowSize();
    int colSIze = getColSize();

    for (int row = 0; row < rowSIze; row++) {
      for (int col = 0; col < colSIze; col++) {
        board[row][col] = new EmptyCell();
      }
    }
    for (int i = 0; i < landMineCount; i++) {
      int landMineCol = new Random().nextInt(colSIze);
      int landMineRow = new Random().nextInt(rowSIze);
      board[landMineRow][landMineCol] = new LandMineCell();

    }
    for (int row = 0; row < rowSIze; row++) {
      for (int col = 0; col < colSIze; col++) {
        CellPosition cellPosition = CellPosition.of(row, col);

        if (isLandMineCellAt(cellPosition)) {
          continue;
        }
        int count = countNearbyLandMines(cellPosition);
        if (count == 0) {
          continue;
        }
        board[row][col] = new NumberCell(count);
      }
    }
  }

  public int getRowSize() {
    return board.length;
  }

  public int getColSize() {
    return board[0].length;
  }

  public String getSign(CellPosition cellPosition) {
    Cell cell = findCell(cellPosition);
    return cell.getSign();
  }

  private Cell findCell(CellPosition cellPosition) {
    return board[cellPosition.getRowIndex()][cellPosition.getColIndex()];
  }

  public void flagAt(CellPosition cellPosition) {
    Cell cell = findCell(cellPosition);
    cell.flag();
  }

  public boolean isLandMineCellAt(CellPosition cellPosition) {
    return findCell(cellPosition).isLandMine();
  }

  public void openAt(CellPosition cellPosition) {
    Cell cell = findCell(cellPosition);
    cell.open();
  }

  public void openSurroundedCells(CellPosition cellPosition) {
    if (isOpenedCell(cellPosition)){
      return;
    }
    if (isLandMineCellAt(cellPosition)){
      return;
    }

    openAt(cellPosition);

    if (doesCellHaveLandMineCount(cellPosition)) {
      return;
    }

    List<CellPosition> surroundedPositions = calculateSurroundedPositions(cellPosition,
        getRowSize(), getColSize());
    surroundedPositions.forEach(this::openSurroundedCells);
  }

  public boolean isAllCellChecked() {
    return Arrays.stream(board)
        .flatMap(Arrays::stream)
        .allMatch(Cell::isChecked);
  }


  private boolean doesCellHaveLandMineCount(CellPosition cellPosition) {
    return findCell(cellPosition).hasLandMineCount();
  }

  private boolean isOpenedCell(CellPosition cellPosition) {
    return findCell(cellPosition).isOpened();
  }


  private int countNearbyLandMines(CellPosition cellPosition) {
    int rowSIze = getRowSize();
    int colSIze = getColSize();

    long count = calculateSurroundedPositions(cellPosition, rowSIze, colSIze).stream()
        .filter((this::isLandMineCellAt))
        .count();

    return (int) count;
  }

  private List<CellPosition> calculateSurroundedPositions(CellPosition cellPosition, int rowSIze,
      int colSIze) {
    return RelativePosition.RELATIVE_POSITIONS.stream()
        .filter((cellPosition::canCalculatePositionBy))
        .map(cellPosition::calculatePositionBy)
        .filter(position -> position.isRowIndexLessThan(rowSIze))
        .filter(position -> position.isColIndexLessThan(colSIze))
        .toList();
  }

  public boolean isInvalidCellPosition(CellPosition position) {
    int rowSize = getRowSize();
    int colSize = getColSize();

    return position.isRowIndexMoreThanOrEqual(rowSize) || position.isColIndexMoreThanOrEqual(
        colSize);
  }
}
