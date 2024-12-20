package interface_adapter.change_password;

import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInViewModel;
import use_case.change_password.ChangePasswordInputData;
import use_case.change_password.ChangePasswordOutputBoundary;
import use_case.change_password.ChangePasswordOutputData;

/**
 * The Presenter for the Change Password Use Case.
 */
public class ChangePasswordPresenter implements ChangePasswordOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final ChangePasswordViewModel changePasswordViewModel;
    private final LoggedInViewModel loggedInViewModel;

    public ChangePasswordPresenter(ViewManagerModel viewManagerModel,
                                   ChangePasswordViewModel changePasswordViewmodel,
                                   LoggedInViewModel loggedInViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.changePasswordViewModel = changePasswordViewmodel;
        this.loggedInViewModel = loggedInViewModel;
    }

    @Override
    public void prepareSuccessView(ChangePasswordOutputData outputData) {
        // currently there isn't anything to change based on the output data,
        // since the output data only contains the username, which remains the same.
        // We still fire the property changed event, but just to let the view know that
        // it can alert the user that their password was changed successfully..
        changePasswordViewModel.firePropertyChanged("password");

    }

    @Override
    public void prepareFailView(String error) {
        // note: this use case currently can't fail
    }

    @Override
    public void switchToChangePasswordView(ChangePasswordInputData changePasswordInputData) {
        final ChangePasswordState changePasswordState = changePasswordViewModel.getState();
        changePasswordState.setUsername(changePasswordInputData.getUsername());
        changePasswordState.setPassword(changePasswordInputData.getPassword());
        changePasswordViewModel.setState(changePasswordState);
        changePasswordViewModel.firePropertyChanged();

        viewManagerModel.setState(changePasswordViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToMainMenuView() {
        // Update the state in the ViewManagerModel
        viewManagerModel.setState(loggedInViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

}
