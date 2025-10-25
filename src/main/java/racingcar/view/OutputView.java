package racingcar.view;

import racingcar.controller.dto.Result;

import java.util.List;
import java.util.Map;

public class OutputView {
    private static final String RACE_RESULT_GUIDE_MESSAGE = "\n실행 결과";
    private static final String RACE_WINNER_GUIDE_MESSAGE = "최종 우승자 : ";
    private static final String NAME_DISTANCE_SEPARATOR = " : ";
    private static final String DISTANCE_SYMBOL = "-";
    private static final String WINNER_SEPARATOR = ", ";

    public void printRaceResult(List<Result.RoundResult> roundResults) {
        System.out.println(RACE_RESULT_GUIDE_MESSAGE);
        for (Result.RoundResult roundResult : roundResults) {
            printRoundResult(roundResult.carPositions());
        }
    }

    public void printWinners(List<String> winners) {
        String winnersString = String.join(WINNER_SEPARATOR, winners);
        System.out.println(RACE_WINNER_GUIDE_MESSAGE + winnersString);
    }

    private void printRoundResult(Map<String, Integer> roundResult) {
        for (Map.Entry<String, Integer> entry : roundResult.entrySet()) {
            System.out.println(entry.getKey() + NAME_DISTANCE_SEPARATOR + DISTANCE_SYMBOL.repeat(entry.getValue()));
        }
        System.out.println();
    }
}
