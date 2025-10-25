package racingcar.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import racingcar.controller.dto.Result;
import racingcar.exception.InvalidInputException;
import racingcar.exception.Message;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RaceServiceTest {
    private static final String VALID_CAR_NAMES_INPUT = "pobi,woni,jun";
    private static final String VALID_TRY_COUNT_INPUT = "5";

    private RaceService raceService;

    @BeforeEach
    void setUp() {
        raceService = new RaceService();
    }

    @Test
    void 올바른_자동차_이름_문자열이_입력된_경우() {
        String carNamesInput = "pobi,woni,jun";
        String tryCountInput = VALID_TRY_COUNT_INPUT;

        Result result = raceService.run(carNamesInput, tryCountInput);
        List<String> actualCarNames = result.roundResults().getFirst()
                .carPositions().keySet().stream().toList();

        assertThat(String.join(",", actualCarNames)).isEqualTo(carNamesInput);
    }

    @Test
    void 올바른_시도_횟수_문자열이_입력된_경우() {
        String carNamesInput = VALID_CAR_NAMES_INPUT;
        String tryCountInput = "5";

        Result result = raceService.run(carNamesInput, tryCountInput);
        int actualTryCount = result.roundResults().size();

        assertThat(actualTryCount).isEqualTo(Integer.parseInt(tryCountInput));
    }

    @Test
    void 자동차_이름_문자열이_null인_경우_예외_발생() {
        String carNamesInput = null;
        String tryCountInput = VALID_TRY_COUNT_INPUT;

        assertThatThrownBy(() -> raceService.run(carNamesInput, tryCountInput))
                .isInstanceOf(InvalidInputException.class)
                .hasMessage(Message.CAR_NAMES_NULL_OR_BLANK.getMessage());
    }

    @Test
    void 자동차_이름_문자열이_빈값인_경우_예외_발생() {
        String carNamesInput = "";
        String tryCountInput = VALID_TRY_COUNT_INPUT;

        assertThatThrownBy(() -> raceService.run(carNamesInput, tryCountInput))
                .isInstanceOf(InvalidInputException.class)
                .hasMessage(Message.CAR_NAMES_NULL_OR_BLANK.getMessage());
    }

    @Test
    void 자동차_이름_문자열이_공백인_경우_예외_발생() {
        String carNamesInput = " ";
        String tryCountInput = VALID_TRY_COUNT_INPUT;

        assertThatThrownBy(() -> raceService.run(carNamesInput, tryCountInput))
                .isInstanceOf(InvalidInputException.class)
                .hasMessage(Message.CAR_NAMES_NULL_OR_BLANK.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {",pobi,woni,jun", "pobi,woni,jun,"})
    void 자동차_이름들_문자열에_쉼표가_맨앞_혹은_맨뒤에_있는_경우_예외_발생(String carNamesInput) {
        String tryCountInput = VALID_TRY_COUNT_INPUT;

        assertThatThrownBy(() -> raceService.run(carNamesInput, tryCountInput))
                .isInstanceOf(InvalidInputException.class)
                .hasMessage(Message.CAR_NAMES_COMMA_AT_START_OR_END.getMessage());
    }

    @Test
    void 자동차_이름들_문자열에_쉼표가_연속으로_있는_경우_예외_발생() {
        String carNamesInput = "pobi,,woni,jun";
        String tryCountInput = VALID_TRY_COUNT_INPUT;

        assertThatThrownBy(() -> raceService.run(carNamesInput, tryCountInput))
                .isInstanceOf(InvalidInputException.class)
                .hasMessage(Message.CAR_NAMES_CONSECUTIVE_COMMA_PRESENT.getMessage());
    }

    @Test
    void 시도_횟수_입력값이_null_값인_경우_예외_발생() {
        String carNamesInput = VALID_CAR_NAMES_INPUT;
        String tryCountInput = null;

        assertThatThrownBy(() -> raceService.run(carNamesInput, tryCountInput))
                .isInstanceOf(InvalidInputException.class)
                .hasMessage(Message.TRY_COUNT_NOT_AN_INTEGER.getMessage());
    }

    @Test
    void 시도_횟수_입력값이_빈_문자인_경우_예외_발생() {
        String carNamesInput = VALID_CAR_NAMES_INPUT;
        String tryCountInput = "";

        assertThatThrownBy(() -> raceService.run(carNamesInput, tryCountInput))
                .isInstanceOf(InvalidInputException.class)
                .hasMessage(Message.TRY_COUNT_NOT_AN_INTEGER.getMessage());
    }

    @Test
    void 시도_횟수_입력값이_공백인_경우_예외_발생() {
        String carNamesInput = VALID_CAR_NAMES_INPUT;
        String tryCountInput = " ";

        assertThatThrownBy(() -> raceService.run(carNamesInput, tryCountInput))
                .isInstanceOf(InvalidInputException.class)
                .hasMessage(Message.TRY_COUNT_NOT_AN_INTEGER.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "one"})
    void 시도_횟수_입력값이_숫자가_아닌_경우_예외_발생(String tryCountInput) {
        String carNamesInput = VALID_CAR_NAMES_INPUT;

        assertThatThrownBy(() -> raceService.run(carNamesInput, tryCountInput))
                .isInstanceOf(InvalidInputException.class)
                .hasMessage(Message.TRY_COUNT_NOT_AN_INTEGER.getMessage());
    }

    @Test
    void 시도_횟수_입력값이_정수가_아닌_경우_예외_발생() {
        String carNamesInput = VALID_CAR_NAMES_INPUT;
        String tryCountInput = "1.1";

        assertThatThrownBy(() -> raceService.run(carNamesInput, tryCountInput))
                .isInstanceOf(InvalidInputException.class)
                .hasMessage(Message.TRY_COUNT_NOT_AN_INTEGER.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "-0", "-100"})
    void 시도_횟수_입력값이_양수가_아닌_경우_예외_발생(String tryCountInput) {
        String carNamesInput = VALID_CAR_NAMES_INPUT;

        assertThatThrownBy(() -> raceService.run(carNamesInput, tryCountInput))
                .isInstanceOf(InvalidInputException.class)
                .hasMessage(Message.TRY_COUNT_NEGATIVE_OR_ZERO.getMessage());
    }
}
