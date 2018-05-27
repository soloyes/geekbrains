package xyz.shuttle.filebox.frontend.services;

public interface AuthenticationService {
    void login(String login, String password);

    void logout();

    boolean isAuthenticated();
}
