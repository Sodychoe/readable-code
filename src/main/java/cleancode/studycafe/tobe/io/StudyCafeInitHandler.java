package cleancode.studycafe.tobe.io;

import cleancode.studycafe.tobe.model.StudyCafeLockerPass;
import cleancode.studycafe.tobe.model.StudyCafePass;
import java.util.List;

public interface StudyCafeInitHandler {

  List<StudyCafePass> readStudyCafePasses();

  List<StudyCafeLockerPass> readLockerPasses();

}
