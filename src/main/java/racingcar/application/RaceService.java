package racingcar.application;

import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.controller.dto.Result;
import racingcar.exception.InvalidInputException;
import racingcar.exception.Message;
import racingcar.util.Validator;

import java.util.*;

public class RaceService {
    private static final String COMMA = ",";

    public Result run(String carNamesInput, String tryCountInput) {
        List<String> carNames = parseCarNames(carNamesInput);
        int tryCount = parseTryCount(tryCountInput);

        Cars cars = new Cars(carNames);
        List<Result.RoundResult> roundResults = startRace(cars, tryCount);
        List<String> winners = findWinners(cars);

        return new Result(roundResults, winners);
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
        Map<String, Integer> roundResult = new LinkedHashMap<>();
        cars.move();

        for (Car car : cars.getCars()) {
            roundResult.put(car.getName(), car.getDistance());
        }

        return new Result.RoundResult(roundNumber, roundResult);
    }

    private List<String> findWinners(Cars cars) {
        return cars.getCarNamesWithMaxDistance();
    }

    private List<String> parseCarNames(String input) {
        validateCarNamesInput(input);
        return List.of(input.split(COMMA));
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
        checkTryCountIsPositive(tryCount);
    }

    private void validateCarNamesInput(String carNamesInput) {
        checkNotNullAndBlank(carNamesInput);
        checkNoCommaAtEnds(carNamesInput);
        checkNoConsecutiveCommas(carNamesInput);
    }

    private void checkNotNullAndBlank(String carNamesInput) {
        if (!Validator.isNullOrBlank(carNamesInput)) {
            return;
        }
        throw new InvalidInputException(Message.CAR_NAMES_NULL_OR_BLANK.getMessage());
    }

    private void checkNoCommaAtEnds(String carNamesInput) {
        if (!Validator.startsOrEndsWith(carNamesInput, COMMA)) {
            return;
        }
        throw new InvalidInputException(Message.CAR_NAMES_COMMA_AT_START_OR_END.getMessage());
    }

    private void checkNoConsecutiveCommas(String carNamesInput) {
        if (!Validator.containsConsecutiveSubstring(carNamesInput, COMMA)) {
            return;
        }
        throw new InvalidInputException(Message.CAR_NAMES_CONSECUTIVE_COMMA_PRESENT.getMessage());
    }

    private void checkTryCountIsPositive(int tryCount) {
        if (Validator.isBiggerThan(tryCount, 0)) {
            return;
        }
        throw new InvalidInputException(Message.TRY_COUNT_NEGATIVE_OR_ZERO.getMessage());
    }
}
