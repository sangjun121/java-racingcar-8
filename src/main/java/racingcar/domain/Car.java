package racingcar.domain;

import racingcar.exception.InvalidCarException;
import racingcar.exception.Message;
import racingcar.util.Validator;

import java.util.Objects;

public final class Car {
    private static final int MIN_CAR_NAME_LENGTH = 1;
    private static final int MAX_CAR_NAME_LENGTH = 5;
    private static final int INITIAL_DISTANCE = 0;
    private static final int MOVE_UNIT = 1;

    private final String name;
    private final int distance;

    public Car(String name) {
        validateName(name);
        this.name = name;
        this.distance = INITIAL_DISTANCE;
    }

    private Car(String name, int distance) {
        validateName(name);
        validateDistance(distance);
        this.name = name;
        this.distance = distance;
    }

    public String getName() {
        return name;
    }

    public int getDistance() {
        return distance;
    }

    public Car move() {
        return new Car(this.name, this.distance + MOVE_UNIT);
    }

    private void validateName(String name) {
        validateNameNotNullOrBlank(name);
        validateNameLength(name);
    }

    private void validateDistance(int distance) {
        validateDistanceIsNonNegative(distance);
    }

    private void validateNameNotNullOrBlank(String name) {
        if (!Validator.isNullOrBlank(name)) return;
        throw new InvalidCarException(Message.CAR_NAME_NULL_OR_BLANK.getMessage());
    }

    private void validateNameLength(String name) {
        if (Validator.isWithinLengthRange(name, MIN_CAR_NAME_LENGTH, MAX_CAR_NAME_LENGTH)) return;
        throw new InvalidCarException(Message.CAR_NAME_LENGTH_OUT_OF_RANGE.getMessage());
    }

    private void validateDistanceIsNonNegative(int distance) {
        if (!Validator.isLessThan(distance, INITIAL_DISTANCE)) return;
        throw new InvalidCarException(Message.CAR_DISTANCE_NEGATIVE.getMessage());
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Car other)) return false;
        return distance == other.distance
                && Objects.equals(name, other.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, distance);
    }
}
