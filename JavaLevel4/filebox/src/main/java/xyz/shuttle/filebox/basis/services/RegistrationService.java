package xyz.shuttle.filebox.basis.services;

public interface RegistrationService {
    boolean register(String username, String password);

    boolean isRegistered();

    boolean unRegister();
}
