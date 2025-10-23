package racingcar.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Cars {
    private static final String COMMA = ",";
    private final List<Car> cars;

    public Cars(String carNames) {
        validateCarNames(carNames);
        this.cars = createCars(carNames);
    }

    private void validateCarNames(String carNames) {
        validateCommaNotAtEnds(carNames);
        validateNoConsecutiveCommas(carNames);
    }

    private List<Car> createCars(String carNames) {
        List<String> carNameList = Arrays.asList(carNames.split(COMMA));
        validateNoDuplicateNames(carNameList);
        return carNameList.stream().map(Car::new).collect(Collectors.toList());
    }

    private void validateCommaNotAtEnds(String carNames) {
    }

    private void validateNoConsecutiveCommas(String carNames) {
    }

    private void validateNoDuplicateNames(List<String> carNames) {
    }
}
