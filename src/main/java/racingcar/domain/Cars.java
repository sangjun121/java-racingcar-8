package racingcar.domain;

import racingcar.exception.InvalidCarException;
import racingcar.exception.Message;
import racingcar.util.RandomNumberGenerator;
import racingcar.util.Validator;

import java.util.List;
import java.util.stream.Collectors;

public class Cars {
    private final List<Car> cars;

    public Cars(List<String> carNames) {
        validateCarNames(carNames);
        this.cars = createCars(carNames);
    }

    public void move() {
        for (Car car : cars) {
            car.move(RandomNumberGenerator.generate());
        }
    }

    public List<String> getCarNamesWithMaxDistance() {
        int maxDistance = findMaxDistance(cars);
        return findMaxDistanceCarNames(cars, maxDistance);
    }

    public List<String> getCarNames() {
        return cars.stream().map(Car::getName).collect(Collectors.toList());
    }

    public List<Car> getCars() {
        return cars;
    }

    private List<Car> createCars(List<String> carNames) {
        return carNames.stream().map(Car::new).toList();
    }

    private int findMaxDistance(List<Car> cars) {
        return cars.stream()
                .mapToInt(Car::getDistance)
                .max()
                .orElse(0);
    }

    private List<String> findMaxDistanceCarNames(List<Car> cars, int maxDistance) {
        return cars.stream()
                .filter(car -> car.getDistance() == maxDistance)
                .map(Car::getName)
                .toList();
    }

    private void validateCarNames(List<String> carNames) {
        checkNoDuplicateNames(carNames);
    }
    private void checkNoDuplicateNames(List<String> carNames) {
        if (!Validator.hasDuplicates(carNames)) return;
        throw new InvalidCarException(Message.CAR_NAME_DUPLICATED.getMessage());
    }
}
