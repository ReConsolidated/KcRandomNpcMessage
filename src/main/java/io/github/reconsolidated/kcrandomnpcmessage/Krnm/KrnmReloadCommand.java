package io.github.reconsolidated.kcrandomnpcmessage.Krnm;

import io.github.reconsolidated.kcrandomnpcmessage.CommandManagement.SubCommand;
import org.bukkit.command.CommandSender;

public class KrnmReloadCommand implements SubCommand {
    private final KrnmService krnmService;

    public KrnmReloadCommand(KrnmService krnmService) {
        this.krnmService = krnmService;
    }

    @Override
    public String getName() {
        return "reload";
    }

    @Override
    public String getDescription() {
        return "Reloads the plugin";
    }

    @Override
    public String getSyntax() {
        return "/krnm reload";
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        if (args.length == 1) {
            krnmService.reload();
            sender.sendMessage("Reloaded!");
        } else {
            sender.sendMessage("Correct syntax: " + getSyntax());
        }
    }
}
