package pch.huellaschile.msconsultasmedicas.domain.exception;

public class OtherVeterinarianException extends Exception {
    private static final long serialVersionUID = -8890080495441147845L;

    private String message;
    private Object[] args;

    public OtherVeterinarianException(String name) {
        this.message = String.format("There is already a pet with the name - %s", name);
    }

    public OtherVeterinarianException(Object[] args) {
        this.args = args;
    }

    public OtherVeterinarianException(String message, Object[] args) {
        this.message = message;
        this.args = args;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }
}
