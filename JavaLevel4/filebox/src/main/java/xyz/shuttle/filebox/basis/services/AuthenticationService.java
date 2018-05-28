package xyz.shuttle.filebox.basis.services;

public interface AuthenticationService {
    void login(String login, String password);

    void logout();

    boolean isAuthenticated();
}
