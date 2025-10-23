package racingcar.domain;

import racingcar.exception.InvalidCarsException;
import racingcar.exception.Message;
import racingcar.util.StringValidator;

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

    public List<String> getCarNames() {
        return cars.stream().map(Car::getName).collect(Collectors.toList());
    }

    public List<Car> getCars() {
        return cars;
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
        if (!StringValidator.startsOrEndsWith(carNames, COMMA)) return;
        throw new InvalidCarsException(Message.CAR_NAMES_COMMA_AT_START_OR_END.getMessage());
    }

    private void validateNoConsecutiveCommas(String carNames) {
        if (!StringValidator.containsConsecutiveSubstring(carNames, COMMA)) return;
        throw new InvalidCarsException(Message.CAR_NAMES_CONSECUTIVE_COMMA_PRESENT.getMessage());
    }

    private void validateNoDuplicateNames(List<String> carNames) {
        if (!StringValidator.hasDuplicates(carNames)) return;
        throw new InvalidCarsException(Message.CAR_NAME_DUPLICATED.getMessage());
    }
}
