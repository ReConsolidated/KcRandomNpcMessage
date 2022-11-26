package io.github.reconsolidated.kcrandomnpcmessage.CommandManagement;

public class NoCommandException extends RuntimeException {
    public NoCommandException() {
        super("No command found");
    }
}
