package az.keytd.expensetracker.exceptions;

public class NotFoundException extends RuntimeException{
     public NotFoundException (String message){
        super(message) ;
    }

    public NotFoundException(){
        // TODO: it would be better to call above constructor with "this(msg)"
        super("not Found") ;
    }
}
