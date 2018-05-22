package xyz.shuttle.filebox.frontend.services;

public interface RegistrationService {
    boolean register(String username, String password);

    boolean isRegistered();

    boolean unRegister();
}
