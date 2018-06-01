package xyz.shuttle.filebox.basis.model.auth;

public interface AuthenticationService {
    void login(String login, String password);

    void logout();

    boolean isAuthenticated();
}
