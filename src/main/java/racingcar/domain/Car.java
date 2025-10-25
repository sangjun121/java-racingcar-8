package racingcar.domain;

import racingcar.exception.InvalidCarException;
import racingcar.exception.Message;
import racingcar.util.Validator;

public class Car {
    private static final int MIN_CAR_NAME_LENGTH = 1;
    private static final int MAX_CAR_NAME_LENGTH = 5;
    private static final int INITIAL_DISTANCE = 0;
    private static final int MOVE_UNIT = 1;
    private static final int MOVE_THRESHOLD = 4;

    private final String name;
    private int distance;

    public Car(String name) {
        validateName(name);
        this.name = name;
        this.distance = INITIAL_DISTANCE;
    }

    public String getName() {
        return name;
    }

    public int getDistance() {
        return distance;
    }

    public void move(int condition) {
        if (canMove(condition)) this.distance += MOVE_UNIT;
    }

    private boolean canMove(int condition) {
        return condition >= MOVE_THRESHOLD;
    }

    private void validateName(String name) {
        checkNotNullOrBlank(name);
        checkNameLength(name);
    }

    private void checkNotNullOrBlank(String name) {
        if (!Validator.isNullOrBlank(name)) return;
        throw new InvalidCarException(Message.CAR_NAME_NULL_OR_BLANK.getMessage());
    }

    private void checkNameLength(String name) {
        if (Validator.isWithinLengthRange(name, MIN_CAR_NAME_LENGTH, MAX_CAR_NAME_LENGTH)) return;
        throw new InvalidCarException(Message.CAR_NAME_LENGTH_OUT_OF_RANGE.getMessage());
    }
}
