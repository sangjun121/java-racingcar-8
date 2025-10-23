package racingcar.view;

public class OutputView {
    private static final String RACE_RESULT_GUIDE_MESSAGE = "실행 결과";
    private static final String NAME_DISTANCE_SEPARATOR = " : ";
    private static final String DISTANCE_SYMBOL = "-";

    public void printRaceResultGuideMessage() {
        System.out.println(RACE_RESULT_GUIDE_MESSAGE);
    }

    public void printRoundResult(String carName, int distance) {
        System.out.println(carName + NAME_DISTANCE_SEPARATOR + DISTANCE_SYMBOL.repeat(distance));
    }
}
