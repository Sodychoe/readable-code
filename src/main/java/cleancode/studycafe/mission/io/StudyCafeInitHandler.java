package cleancode.studycafe.mission.io;

import cleancode.studycafe.mission.model.StudyCafeLockerPass;
import cleancode.studycafe.mission.model.StudyCafePass;
import cleancode.studycafe.mission.model.StudyCafePassType;
import java.util.List;

public abstract class StudyCafeInitHandler {

  protected abstract List<StudyCafePass> readStudyCafePasses();

  protected abstract List<StudyCafeLockerPass> readLockerPasses();

  public abstract List<StudyCafePass> getSelectablePassesForUserSelection(StudyCafePassType passTypeSelectingUserAction);

  public abstract StudyCafeLockerPass getSelectableLockerPassesForUserSelection(StudyCafePass passSelectingUserAction);
}
