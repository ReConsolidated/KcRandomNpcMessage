package io.github.reconsolidated.kcrandomnpcmessage.Krnm;

import io.github.reconsolidated.kcrandomnpcmessage.CommandManagement.SubCommand;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.List;

public class KrnmAddCommand implements SubCommand {
    private final KrnmService krnmService;

    public KrnmAddCommand(KrnmService krnmService) {
        this.krnmService = krnmService;
    }

    @Override
    public String getName() {
        return "add";
    }

    @Override
    public String getDescription() {
        return "Add a message to the list of messages";
    }

    @Override
    public String getSyntax() {
        return "/krnm add <holder> <message>";
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        if (args.length >= 3) {
            String holder = args[1];
            StringBuilder message = new StringBuilder(args[2]);
            for (int i = 3; i < args.length; i++) {
                message.append(" ").append(args[i]);
            }
            krnmService.addMessage(holder, message.toString());
            sender.sendMessage("Added message to list");
        } else {
            sender.sendMessage(ChatColor.RED + "Correct syntax: " + ChatColor.DARK_AQUA + getSyntax());
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return SubCommand.super.onTabComplete(sender, command, alias, args);
    }
}
