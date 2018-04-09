package xyz.shuttle.filebox.frontend.services.auth;

public interface AuthenticationService {
    boolean login(String login, String password);

    void logout();

    boolean isAuthenticated();
}
