package dsw.gerudok.app.gui.swing.error;

public class Error {
    private String message;
    private int type;

    public Error(String message, int type) {
        this.message = message;
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public int getType() {
        return type;
    }

}
