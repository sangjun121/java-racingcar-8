package racingcar.application;

import racingcar.exception.InvalidInputException;
import racingcar.exception.Message;
import racingcar.util.Validator;

public class RaceService {
    // TODO: 반환 값 Result로 수정
    public void run(String carNamesInput, String tryCountInput) {
        int tryCount = parseTryCount(tryCountInput);
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
