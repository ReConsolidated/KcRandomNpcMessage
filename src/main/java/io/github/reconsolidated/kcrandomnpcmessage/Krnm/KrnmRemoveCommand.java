package io.github.reconsolidated.kcrandomnpcmessage.Krnm;

import io.github.reconsolidated.kcrandomnpcmessage.CommandManagement.InformException;
import io.github.reconsolidated.kcrandomnpcmessage.CommandManagement.SubCommand;
import org.bukkit.command.CommandSender;

import java.util.List;

public class KrnmRemoveCommand implements SubCommand {
    private final KrnmService krnmService;

    public KrnmRemoveCommand(KrnmService krnmService) {
        this.krnmService = krnmService;
    }

    @Override
    public String getName() {
        return "remove";
    }

    @Override
    public String getDescription() {
        return "Remove a message from holder";
    }

    @Override
    public String getSyntax() {
        return "/krnm remove <holder> [number]";
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        if (args.length == 3) {
            String holder = args[1];
            int number = Integer.parseInt(args[2]);
            krnmService.removeMessage(holder, number);
            sender.sendMessage("Removed message from list");
        } else if (args.length == 2) {
            String holder = args[1];
            List<String> messages = krnmService.getHolderMessages(holder).orElseThrow(() ->
                    new InformException("Holder %s not found".formatted(holder))
            );
            for (int i = 0; i < messages.size(); i++) {
                sender.sendMessage((i+1) + ". " + messages.get(i));
            }
        } else {
            sender.sendMessage("Correct syntax: " + getSyntax());
        }
    }
}
