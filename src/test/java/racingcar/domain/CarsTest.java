package racingcar.domain;

import org.junit.jupiter.api.Test;
import racingcar.exception.InvalidCarException;
import racingcar.exception.Message;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CarsTest {

    @Test
    void 자동차_이름들이_올바른_경우(){
        List<String> carNames = List.of("pobi", "woni", "jun");

        Cars cars = new Cars(carNames);

        assertThat(cars.getCarNames()).isEqualTo(carNames);
    }

    @Test
    void 자동차_이름이_중복되는_경우_예외_발생() {
        List<String> carNames = List.of("pobi", "pobi", "jun");

        assertThatThrownBy(() -> new Cars(carNames))
                .isInstanceOf(InvalidCarException.class)
                .hasMessage(Message.CAR_NAME_DUPLICATED.getMessage());
    }
}
