package racingcar.controller.dto;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public record Result(
        List<RoundResult> roundResults,
        List<String> winners
) {

    public Result {
        roundResults = List.copyOf(roundResults);
        winners = List.copyOf(winners);
    }

    public record RoundResult(int roundNumber,
                              Map<String, Integer> carPositions) {

        public RoundResult {
            carPositions = Collections.unmodifiableMap(new LinkedHashMap<>(carPositions));
        }
    }
}
