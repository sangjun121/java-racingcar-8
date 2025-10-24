package racingcar.config;

import racingcar.application.RaceService;
import racingcar.controller.RaceController;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class AppConfig {

    public InputView inputView() {
        return new InputView();
    }

    public OutputView outputView() {
        return new OutputView();
    }

    public RaceService raceService() {
        return new RaceService();
    }

    public RaceController raceController() {
        return new RaceController(inputView(), outputView(), raceService());
    }
}
