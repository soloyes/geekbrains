package xyz.shuttle.filebox.frontend.model.registration;

public interface RegistrationService {
    boolean register(String username, String password);

    boolean isRegistered();

    boolean unRegister();
}
