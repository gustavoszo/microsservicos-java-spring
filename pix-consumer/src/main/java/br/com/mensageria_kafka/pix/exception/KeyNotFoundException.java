package br.com.mensageria_kafka.pix.exception;

public class KeyNotFoundException extends RuntimeException {

    public KeyNotFoundException(String message) {
        super(message);
    }

}
