package cleancode.minesweeper.tobe.cell;

import java.util.Objects;

public class CellSnapshot {

  private final CellSnapshotStatus status;
  private final int nearbyLandMineCount;

  public CellSnapshotStatus getStatus() {
    return status;
  }

  public int getNearbyLandMineCount() {
    return nearbyLandMineCount;
  }

  private CellSnapshot(CellSnapshotStatus status, int nearbyLandMineCount) {
    this.status = status;
    this.nearbyLandMineCount = nearbyLandMineCount;
  }

  public static CellSnapshot of(CellSnapshotStatus status, int nearbyLandMineCount) {
    return new CellSnapshot(status, nearbyLandMineCount);
  }

  public static CellSnapshot ofEmpty() {
    return of(CellSnapshotStatus.EMPTY, 0);
  }

  public static CellSnapshot ofFlag() {
    return of(CellSnapshotStatus.FLAG, 0);
  }

  public static CellSnapshot ofLandMine() {
    return of(CellSnapshotStatus.LAND_MINE, 0);
  }

  public static CellSnapshot ofNumber(int nearbyLandMineCount) {
    return of(CellSnapshotStatus.NUMBER, nearbyLandMineCount);
  }

  public static CellSnapshot ofUnchecked() {
    return of(CellSnapshotStatus.UNCHECKED, 0);
  }

  public boolean isSameStatus(CellSnapshotStatus cellSnapshotStatus) {
    return this.status == cellSnapshotStatus;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof CellSnapshot that)) {
      return false;
    }
    return nearbyLandMineCount == that.nearbyLandMineCount && status == that.status;
  }

  @Override
  public int hashCode() {
    return Objects.hash(status, nearbyLandMineCount);
  }
}
