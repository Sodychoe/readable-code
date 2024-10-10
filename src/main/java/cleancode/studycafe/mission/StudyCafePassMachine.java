package cleancode.studycafe.mission;

import cleancode.studycafe.mission.exception.AppException;
import cleancode.studycafe.mission.io.InputHandler;
import cleancode.studycafe.mission.io.OutputHandler;
import cleancode.studycafe.mission.io.StudyCafeInitHandler;
import cleancode.studycafe.mission.model.StudyCafeLockerPass;
import cleancode.studycafe.mission.model.StudyCafePass;
import cleancode.studycafe.mission.model.StudyCafePassType;
import java.util.List;

public class StudyCafePassMachine {

    private final InputHandler inputHandler;
    private final OutputHandler outputHandler;
    private final StudyCafeInitHandler studyCafeInitHandler;

    public StudyCafePassMachine(InputHandler inputHandler, OutputHandler outputHandler,
        StudyCafeInitHandler studyCafeInitHandler) {
        this.inputHandler = inputHandler;
        this.outputHandler = outputHandler;
        this.studyCafeInitHandler = studyCafeInitHandler;
    }

    public void run() {
        try {
            outputHandler.showAnnouncementMessageAtFirst();

            StudyCafePassType passTypeSelectingUserAction = inputHandler.getPassTypeSelectingUserAction();
            List<StudyCafePass> selectablePassesForUserSelection = studyCafeInitHandler.getSelectablePassesForUserSelection(
                passTypeSelectingUserAction);
            outputHandler.showPassListForUserSelection(selectablePassesForUserSelection);

            StudyCafePass selectedPassForUser = inputHandler.getSelectPass(selectablePassesForUserSelection);
            StudyCafeLockerPass selectableLockerPassForUserSelection = studyCafeInitHandler.getSelectableLockerPassForUserSelection(
                selectedPassForUser);

            boolean isLockerPurchased = isLockerPurchasedIfExists(selectableLockerPassForUserSelection);

            if (isLockerPurchased) {
                outputHandler.showPassOrderSummaryIfLockerPass(selectedPassForUser, selectableLockerPassForUserSelection);
            }

            outputHandler.showPassOrderSummary(selectedPassForUser);
        } catch (AppException e) {
            outputHandler.showSimpleMessage(e.getMessage());
        } catch (Exception e) {
            outputHandler.showSimpleMessage("알 수 없는 오류가 발생했습니다.");
        }
    }

    private boolean isLockerPurchasedIfExists(StudyCafeLockerPass selectableLockerPassForUserSelection) {
        outputHandler.askLockPassIfExists(selectableLockerPassForUserSelection);
        boolean isLockerPurchased = false; // null 이면 false
        isLockerPurchased = inputHandler.getLockerSelection();
        return isLockerPurchased;
    }

}
