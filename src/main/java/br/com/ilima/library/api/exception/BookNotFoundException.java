package br.com.ilima.library.api.exception;

public class BookNotFoundException extends RuntimeException {

    String message;

    public BookNotFoundException(String message){
        super(message);
    }

    @Override
    public String getMessage() {
        return message;
    }
}
