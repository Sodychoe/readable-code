package cleancode.studycafe.mission;

import cleancode.studycafe.mission.io.InputHandler;
import cleancode.studycafe.mission.io.OutputHandler;
import cleancode.studycafe.mission.io.StudyCafeFileHandler;
import cleancode.studycafe.mission.io.StudyCafeInitHandler;

public class StudyCafeApplication {

    public static void main(String[] args) {
        InputHandler inputHandler = new InputHandler();
        OutputHandler outputHandler = new OutputHandler();
        StudyCafeInitHandler studyCafeInitHandler = new StudyCafeFileHandler();

        StudyCafePassMachine studyCafePassMachine = new StudyCafePassMachine(
            inputHandler,
            outputHandler,
            studyCafeInitHandler
        );

        studyCafePassMachine.run();
    }

}
