package cleancode.studycafe.mission.io;

import cleancode.studycafe.mission.model.StudyCafeLockerPass;
import cleancode.studycafe.mission.model.StudyCafePass;
import cleancode.studycafe.mission.model.StudyCafePassType;
import java.util.List;

public interface StudyCafeInitHandler {

  List<StudyCafePass> readStudyCafePasses();

  List<StudyCafeLockerPass> readLockerPasses();

  List<StudyCafePass> getSelectablePassesForUserSelection(StudyCafePassType passTypeSelectingUserAction);
}
