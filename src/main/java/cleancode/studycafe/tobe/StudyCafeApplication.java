package cleancode.studycafe.tobe;

import cleancode.studycafe.tobe.io.InputHandler;
import cleancode.studycafe.tobe.io.OutputHandler;
import cleancode.studycafe.tobe.io.StudyCafeFileHandler;
import cleancode.studycafe.tobe.io.StudyCafeInitHandler;

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
