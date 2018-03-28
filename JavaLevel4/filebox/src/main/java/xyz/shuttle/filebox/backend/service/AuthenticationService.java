package xyz.shuttle.filebox.backend.service;

public interface AuthenticationService {
    boolean login(String login, String password);

    void logout();
}
