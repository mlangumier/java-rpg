package rpg.exceptions;

public class ItemNotAvailable extends RuntimeException {
    public ItemNotAvailable(String message) {
        super(message);
    }
}
