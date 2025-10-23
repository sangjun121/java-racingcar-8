package racingcar.domain;

import racingcar.exception.InvalidCarException;
import racingcar.util.StringValidator;

public class Car {
    private static final int MIN_CAR_NAME_LENGTH = 1;
    private static final int MAX_CAR_NAME_LENGTH = 5;
    private static final int INITIAL_DISTANCE = 0;
    private static final int MOVE_UNIT = 1;

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

    public void move() {
        distance = distance + MOVE_UNIT;
    }

    private void validateName(String name) {
        validateNameNotNullOrBlank(name);
        validateNameLength(name);
    }

    private void validateNameNotNullOrBlank(String name) {
        if (!StringValidator.isNullOrBlank(name)) return;
        throw new InvalidCarException("자동차의 이름은 빈 값이거나 공백 문자열일 수 없습니다.");
    }

    private void validateNameLength(String name) {
        if (StringValidator.isWithinLengthRange(name, MIN_CAR_NAME_LENGTH, MAX_CAR_NAME_LENGTH)) return;
        throw new InvalidCarException("자동차의 이름은 1자 이상 5자 이하여야 합니다.");
    }
}
