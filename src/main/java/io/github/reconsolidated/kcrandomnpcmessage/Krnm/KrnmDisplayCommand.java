package io.github.reconsolidated.kcrandomnpcmessage.Krnm;

import io.github.reconsolidated.kcrandomnpcmessage.CommandManagement.InformException;
import io.github.reconsolidated.kcrandomnpcmessage.CommandManagement.SubCommand;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KrnmDisplayCommand implements SubCommand {
        private final KrnmService krnmService;

        public KrnmDisplayCommand(KrnmService krnmService) {
            this.krnmService = krnmService;
        }

        @Override
        public String getName() {
            return "display";
        }

        @Override
        public String getDescription() {
            return "Display a random message from holder";
        }

        @Override
        public String getSyntax() {
            return "/krnm display <holder> <player>";
        }

        @Override
        public void perform(CommandSender sender, String[] args) {
            if (args.length == 3) {
                String holder = args[1];
                String playerName = args[2];
                Player player = Bukkit.getPlayer(playerName);
                if (player != null) {
                    String message = krnmService.getRandomMessage(holder);
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
                } else {
                    throw new InformException("KrnmCommand: Player " + playerName + " not found");
                }
            } else {
                sender.sendMessage("Correct syntax: " + getSyntax());
            }
        }
}
