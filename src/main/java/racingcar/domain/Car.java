package racingcar.domain;

public class Car {
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
    }
}
