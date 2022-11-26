package io.github.reconsolidated.kcrandomnpcmessage.Krnm;

import io.github.reconsolidated.kcrandomnpcmessage.CommandManagement.CommandManager;

public class KrnmCommand extends CommandManager {

    public KrnmCommand(KrnmService krnmService) {
        super("krnm", "After executing this command, you will receive a random message.");

        addSubCommand(new KrnmAddCommand(krnmService));
        addSubCommand(new KrnmShowCommand(krnmService));
        addSubCommand(new KrnmDisplayCommand(krnmService));
        addSubCommand(new KrnmRemoveCommand(krnmService));
    }
}
