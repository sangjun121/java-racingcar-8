package racingcar.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import racingcar.exception.InvalidCarsException;
import racingcar.exception.Message;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CarsTest {

    @Test
    void 올바른_자동차_이름_문자열이_입력된_경우() {
        String carNames = "pobi,woni,jun";

        Cars cars = new Cars(carNames);
        String result = String.join(",", cars.getCarNames());

        assertThat(result).isEqualTo(carNames);
    }

    @ParameterizedTest
    @ValueSource(strings = {",pobi,woni,jun", "pobi,woni,jun,"})
    void 자동차_이름들_문자열에_쉼표가_맨앞_혹은_맨뒤에_있는_경우_예외_발생(String carNames) {
        assertThatThrownBy(() -> new Cars(carNames))
                .isInstanceOf(InvalidCarsException.class)
                .hasMessage(Message.CAR_NAMES_COMMA_AT_START_OR_END.getMessage());
    }

    @Test
    void 자동차_이름들_문자열에_쉼표가_연속으로_있는_경우_예외_발생() {
        String carNames = "pobi,,woni,jun";

        assertThatThrownBy(() -> new Cars(carNames))
                .isInstanceOf(InvalidCarsException.class)
                .hasMessage(Message.CAR_NAMES_CONSECUTIVE_COMMA_PRESENT.getMessage());
    }

    @Test
    void 자동차_이름이_중복되는_경우_예외_발생() {
        String carNames = "pobi,pobi,jun";

        assertThatThrownBy(() -> new Cars(carNames))
                .isInstanceOf(InvalidCarsException.class)
                .hasMessage(Message.CAR_NAME_DUPLICATED.getMessage());
    }
}
