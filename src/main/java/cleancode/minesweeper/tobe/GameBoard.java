package cleancode.minesweeper.tobe;

import cleancode.minesweeper.tobe.cell.Cell;
import cleancode.minesweeper.tobe.cell.CellSnapshot;
import cleancode.minesweeper.tobe.cell.Cells;
import cleancode.minesweeper.tobe.cell.EmptyCell;
import cleancode.minesweeper.tobe.cell.LandMineCell;
import cleancode.minesweeper.tobe.cell.NumberCell;
import cleancode.minesweeper.tobe.gameLevel.GameLevel;
import cleancode.minesweeper.tobe.position.CellPosition;
import cleancode.minesweeper.tobe.position.CellPositions;
import cleancode.minesweeper.tobe.position.RelativePosition;
import java.util.List;

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
    CellPositions cellPositions = CellPositions.from(board);

    initializeEmptyCells(cellPositions);

    List<CellPosition> landMinePositions = cellPositions.extractRandomPositions(landMineCount);
    initializeLandMineCells(landMinePositions);

    List<CellPosition> numberPositionCandidates = cellPositions.subtract(landMinePositions);
    initializeNumberCells(numberPositionCandidates);
  }

  private void initializeNumberCells(List<CellPosition> numberPositionCandidates) {
    for (CellPosition candidatePosition : numberPositionCandidates) {
      int count = countNearbyLandMines(candidatePosition);
      if (count != 0) {
        updateCellAt(candidatePosition, new NumberCell(count));
      }
    }
  }

  private void initializeLandMineCells(List<CellPosition> landMinePositions) {
    for (CellPosition position : landMinePositions) {
      updateCellAt(position, new LandMineCell());
    }
  }

  private void initializeEmptyCells(CellPositions cellPositions) {
    List<CellPosition> allPositions = cellPositions.getPositions();
    for (CellPosition position : allPositions) {
      updateCellAt(position, new EmptyCell());
    }
  }

  private void updateCellAt(CellPosition position, Cell cell) {
    board[position.getRowIndex()][position.getColIndex()] = cell;
  }

  public int getRowSize() {
    return board.length;
  }

  public int getColSize() {
    return board[0].length;
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
    Cells cells = Cells.from(board);
    return cells.isALLChecked();
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

  public CellSnapshot getSnapshot(CellPosition cellPosition) {
    Cell cell = findCell(cellPosition);
    return cell.getSnapshot();
  }
}
