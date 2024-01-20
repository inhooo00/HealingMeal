package gsc.healingmeal.member.execption;

public class InvalidUserException extends RuntimeException{

    public InvalidUserException(final String message){
        super(message);
    }
}
