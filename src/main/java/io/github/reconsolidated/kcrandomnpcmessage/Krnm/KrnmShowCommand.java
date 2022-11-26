package io.github.reconsolidated.kcrandomnpcmessage.Krnm;

import io.github.reconsolidated.kcrandomnpcmessage.CommandManagement.InformException;
import io.github.reconsolidated.kcrandomnpcmessage.CommandManagement.SubCommand;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.List;

public class KrnmShowCommand implements SubCommand {

    private final KrnmService krnmService;

    public KrnmShowCommand(KrnmService krnmService) {
        this.krnmService = krnmService;
    }

    @Override
    public String getName() {
        return "show";
    }

    @Override
    public String getDescription() {
        return "Show available holders or messages";
    }

    @Override
    public String getSyntax() {
        return "/krnm show [holder]";
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        if (args.length == 1) {
            sender.sendMessage(ChatColor.AQUA + "Available holders: ");
            for (String holder : krnmService.getHolders()) {
                sender.sendMessage(" - " + ChatColor.LIGHT_PURPLE + holder);
            }
        } else {
            String holderName = args[1];
            List<String> list = krnmService.getHolderMessages(holderName).orElseThrow(() ->
                            new InformException("Holder %s not found".formatted(holderName)));

            sender.sendMessage(ChatColor.AQUA + "Available messages for holder " +
                    ChatColor.LIGHT_PURPLE + holderName + ChatColor.AQUA + ": ");
            for (String message : list) {
                sender.sendMessage(ChatColor.LIGHT_PURPLE + " - " + ChatColor.WHITE + ChatColor.translateAlternateColorCodes('&', message));
            }
        }
    }
}
