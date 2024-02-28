package az.keytd.expensetracker.exceptions;

public class BadRequestException extends RuntimeException {
    public BadRequestException (String message){
        super(message) ;
    }

    public BadRequestException(){
        super("bad request") ;
    }
}
