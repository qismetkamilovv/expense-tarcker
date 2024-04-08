package az.keytd.expensetracker.dto;

// TODO: utilize Generic types for data var.
public class Response {
    private int status;
    private String message;
    private Object data;

    public Response(int status, String message, Object data) {
        this(status, message);
        this.data = data;
    }

    public Response(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
