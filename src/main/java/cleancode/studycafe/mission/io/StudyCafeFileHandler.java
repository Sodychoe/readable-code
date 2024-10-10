package cleancode.studycafe.mission.io;

import cleancode.studycafe.mission.model.StudyCafeLockerPass;
import cleancode.studycafe.mission.model.StudyCafePass;
import cleancode.studycafe.mission.model.StudyCafePassType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class StudyCafeFileHandler extends StudyCafeInitHandler{

  public static final String STUDYCAFE_PASS_LIST_CSV = "src/main/resources/cleancode/studycafe/pass-list.csv";
  public static final String STUDYCAFE_LOCKER_CSV = "src/main/resources/cleancode/studycafe/locker.csv";

  @Override
  protected final List<StudyCafePass> readStudyCafePasses() {
    try {
      List<String> studyCafePassLines = Files.readAllLines(Paths.get(STUDYCAFE_PASS_LIST_CSV));
      return getStudyCafePasses(studyCafePassLines);
    } catch (IOException e) {
      throw new RuntimeException("파일을 읽는데 실패했습니다.", e);
    }
  }

  private static List<StudyCafePass> getStudyCafePasses(List<String> studyCafePassLines) {
    List<StudyCafePass> studyCafePasses = new ArrayList<>();
    for (String line : studyCafePassLines) {
      String[] values = line.split(",");
      StudyCafePassType studyCafePassType = StudyCafePassType.valueOf(values[0]);
      int duration = Integer.parseInt(values[1]);
      int price = Integer.parseInt(values[2]);
      double discountRate = Double.parseDouble(values[3]);

      StudyCafePass studyCafePass = StudyCafePass.of(studyCafePassType, duration, price, discountRate);
      studyCafePasses.add(studyCafePass);
    }
    return studyCafePasses;
  }

  @Override
  protected final List<StudyCafeLockerPass> readLockerPasses() {
    try {
      List<String> studyCafeLockPassLines = Files.readAllLines(Paths.get(STUDYCAFE_LOCKER_CSV));
      return getStudyCafeLockerPasses(
          studyCafeLockPassLines);
    } catch (IOException e) {
      throw new RuntimeException("파일을 읽는데 실패했습니다.", e);
    }
  }

  private static List<StudyCafeLockerPass> getStudyCafeLockerPasses(List<String> studyCafeLockPassLines) {
    List<StudyCafeLockerPass> lockerPasses = new ArrayList<>();
    for (String line : studyCafeLockPassLines) {
      String[] values = line.split(",");
      StudyCafePassType studyCafePassType = StudyCafePassType.valueOf(values[0]);
      int duration = Integer.parseInt(values[1]);
      int price = Integer.parseInt(values[2]);

      StudyCafeLockerPass lockerPass = StudyCafeLockerPass.of(studyCafePassType, duration, price);
      lockerPasses.add(lockerPass);
    }
    return lockerPasses;
  }

  @Override
  public List<StudyCafePass> getSelectablePassesForUserSelection(StudyCafePassType passTypeSelectingUserAction) {
    List<StudyCafePass> passList = readStudyCafePasses();

    return passList.stream()
        .filter(passType -> passType.getPassType().isSamePassType(passTypeSelectingUserAction))
        .toList();
  }

  @Override
  public StudyCafeLockerPass getSelectableLockerPassForUserSelection(StudyCafePass passSelectingUserAction) {
    List<StudyCafeLockerPass> lockerPassList = readLockerPasses();
    return lockerPassList.stream()
        .filter(option ->
            option.getPassType() == passSelectingUserAction.getPassType()
                && option.getDuration() == passSelectingUserAction.getDuration()
        )
        .findFirst()
        .orElse(null);
  }
}

