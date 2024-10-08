package cleancode.minesweeper.tobe.minesweeper.user;

public enum UserAction {

  OPEN("셀 열기"),
  FLAG("깃발 꽃기"),
  UNKNOWN("알 수 없음");

  private final String descroption;

  UserAction(String descroption) {
    this.descroption = descroption;
  }
}
