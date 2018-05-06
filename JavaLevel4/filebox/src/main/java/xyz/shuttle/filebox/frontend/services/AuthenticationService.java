package xyz.shuttle.filebox.frontend.services;

public interface AuthenticationService {
    boolean login(String login, String password);

    void logout();

    boolean isAuthenticated();
}
