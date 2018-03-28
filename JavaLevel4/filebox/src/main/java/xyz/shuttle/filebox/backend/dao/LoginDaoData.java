package xyz.shuttle.filebox.backend.dao;

import java.util.HashMap;
import java.util.Map;

public class LoginDaoData {
    private Map<String, String> loginPassword;

    public LoginDaoData() {
        loginPassword = new HashMap<>();
        loginPassword.put("A", "A");
        loginPassword.put("B", "B");
    }

    public Map<String, String> getLoginPassword() {
        return loginPassword;
    }
}
