package xyz.shuttle.filebox.frontend.model.auth;

public interface AuthenticationService {
    void login(String login, String password);

    void logout();

    boolean isAuthenticated();
}
