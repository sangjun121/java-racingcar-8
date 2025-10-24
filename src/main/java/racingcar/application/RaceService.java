package racingcar.application;

import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.Result;
import racingcar.exception.InvalidInputException;
import racingcar.exception.Message;
import racingcar.util.Validator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RaceService {
    // TODO: 반환 값 Result로 수정
    public void run(String carNamesInput, String tryCountInput) {
        Cars cars = new Cars(carNamesInput);
        int tryCount = parseTryCount(tryCountInput);

        List<Result.RoundResult> roundResults = startRace(cars, tryCount);
    }

    private List<Result.RoundResult> startRace(Cars cars, int tryCount) {
        List<Result.RoundResult> roundResults = new ArrayList<>();

        for (int roundNumber = 1; roundNumber <= tryCount; roundNumber++) {
            Result.RoundResult roundResult = startRound(roundNumber, cars);
            roundResults.add(roundResult);
        }

        return roundResults;
    }

    private Result.RoundResult startRound(int roundNumber, Cars cars) {
        Map<String, Integer> roundResult = new HashMap<>();
        cars.move();

        for (Car car : cars.getCars()) {
            roundResult.put(car.getName(), car.getDistance());
        }

        return new Result.RoundResult(roundNumber, roundResult);
    }

    private int parseTryCount(String input) {
        try {
            int tryCount = Integer.parseInt(input);
            validateTryCount(tryCount);
            return tryCount;
        } catch (NumberFormatException e) {
            throw new InvalidInputException(Message.TRY_COUNT_NOT_AN_INTEGER.getMessage());
        }
    }

    private void validateTryCount(int tryCount) {
        validateTryCountIsPositive(tryCount);
    }

    private void validateTryCountIsPositive(int tryCount) {
        if (Validator.isBiggerThan(tryCount, 0)) return;
        throw new InvalidInputException(Message.TRY_COUNT_NEGATIVE_OR_ZERO.getMessage());
    }
}
