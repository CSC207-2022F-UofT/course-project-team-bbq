package quiz_settings_use_case;

import entities.QuizSettings;
import entityRequestModels.QuizSettingsDsGateway;
import entityRequestModels.QuizSettingsDsRequestModel;

public class QuizSettingsInteractor implements QuizSettingsInputBoundary {
    final QuizSettingsDsGateway quizDsGateway;
    final QuizSettingsPresenter quizPresenter;

    public QuizSettingsInteractor(QuizSettingsDsGateway quizDsGateway, QuizSettingsPresenter quizPresenter) {
        this.quizDsGateway = quizDsGateway;
        this.quizPresenter = quizPresenter;
    }

    @Override
    public QuizSettingsResponseModel create(QuizSettingsRequestModel requestModel) {
        QuizSettings quizSettings = new QuizSettings(requestModel.getNumQuestions(), requestModel.isTimerOn(),
                requestModel.getTimerDuration(), requestModel.isMultipleChoiceOn(), requestModel.isTextEntryOn(),
                requestModel.isTrueFalseOn());
        if (!quizSettings.atLeastOneOptionEnabled()) {
            return quizPresenter.prepareFailView("Invalid quiz settings: all question types are disabled.");
        }

        QuizSettingsDsRequestModel quizSettingsDsModel = new QuizSettingsDsRequestModel(
                requestModel.isTimerOn(),
                requestModel.getTimerDuration(),
                requestModel.isMultipleChoiceOn(),
                requestModel.isTextEntryOn(),
                requestModel.isTrueFalseOn());
        quizDsGateway.save(quizSettingsDsModel);

        QuizSettingsResponseModel responseModel = new QuizSettingsResponseModel(quizSettings);
        return quizPresenter.prepareSuccessView();
    }
}
