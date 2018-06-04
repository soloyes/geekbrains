package xyz.shuttle.filebox.basis.domain;

public enum UserShareField {
    USER_FROM("userFrom"), USER_TO("userTo"), FILENAME("filename");

    private final String field;

    UserShareField(String field) {
        this.field = field;
    }

    public String field() {
        return field;
    }
}
