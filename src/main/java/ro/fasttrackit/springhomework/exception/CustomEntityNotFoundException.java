package ro.fasttrackit.springhomework.exception;

public class CustomEntityNotFoundException extends RuntimeException {
    public CustomEntityNotFoundException(String entityName, String entityId) {
        super(String.format("%s with id %s not found", entityName, entityId));
    }
}
