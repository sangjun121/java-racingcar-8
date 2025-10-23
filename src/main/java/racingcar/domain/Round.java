package racingcar.domain;

import camp.nextstep.edu.missionutils.Randoms;

public class Round {
    private static final int MOVE_THRESHOLD = 4;
    private static final int RANDOM_MIN = 0;
    private static final int RANDOM_MAX = 9;

    private final Cars cars;

    public Round(Cars cars) {
        this.cars = cars;
    }

    public Cars run() {
        for (Car car : cars.getCars()) {
            int randomNumber = generateRandomNumber();
            if (canMove(randomNumber)) car.move();
        }
        return cars;
    }

    private int generateRandomNumber() {
        return Randoms.pickNumberInRange(RANDOM_MIN, RANDOM_MAX);
    }

    private boolean canMove(int condition) {
        return condition >= MOVE_THRESHOLD;
    }
}
