package be.pxl.olympicgames.multithreading;

public class DidNotFinishException extends RuntimeException {
    public DidNotFinishException(String message) {
        super(message);
    }
}
