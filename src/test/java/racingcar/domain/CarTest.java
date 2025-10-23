package racingcar.domain;

import org.junit.jupiter.api.Test;
import racingcar.exception.InvalidCarException;
import racingcar.exception.Message;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CarTest {

    @Test
    void 올바른_자동차_이름으로_자동차를_생성한다() {
        String name = "junhi";

        Car car = new Car(name);
        String result = car.getName();

        assertThat(result).isEqualTo(name);
    }

    @Test
    void 자동차_이름이_null값이면_예외가_발생한다() {
        String name = null;

        assertThatThrownBy(() -> new Car(name))
                .isInstanceOf(InvalidCarException.class)
                .hasMessage(Message.CAR_NAME_NULL_OR_BLANK.getMessage());
    }

    @Test
    void 자동차_이름이_빈_문자이면_예외가_발생한다() {
        String name = "";

        assertThatThrownBy(() -> new Car(name))
                .isInstanceOf(InvalidCarException.class)
                .hasMessage(Message.CAR_NAME_NULL_OR_BLANK.getMessage());
    }

    @Test
    void 자동차_이름이_공백_문자열이면_예외가_발생한다() {
        String name = "  ";

        assertThatThrownBy(() -> new Car(name))
                .isInstanceOf(InvalidCarException.class)
                .hasMessage(Message.CAR_NAME_NULL_OR_BLANK.getMessage());
    }

    @Test
    void 자동차_이름이_5자를_초과하면_예외가_발생한다() {
        String name = "junjun";

        assertThatThrownBy(() -> new Car(name))
                .isInstanceOf(InvalidCarException.class)
                .hasMessage(Message.CAR_NAME_LENGTH_OUT_OF_RANGE.getMessage());
    }
}
