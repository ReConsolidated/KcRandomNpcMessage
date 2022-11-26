package io.github.reconsolidated.kcrandomnpcmessage.Krnm;

import io.github.reconsolidated.kcrandomnpcmessage.CommandManagement.InformException;
import org.bukkit.plugin.Plugin;

import java.util.*;

public class KrnmService {
    private final static String HOLDER_PREFIX = "holder-";
    private final Map<String, List<String>> holders = new HashMap<>();
    private final Plugin plugin;

    public KrnmService(Plugin plugin) {
        this.plugin = plugin;
        plugin.getConfig().getKeys(false).forEach(holder -> {
            holders.put(holder, plugin.getConfig().getStringList(holder));
        });
    }

    private void save() {
        holders.forEach((holder, messages) -> {
            plugin.getConfig().set(holder, messages);
        });
        plugin.saveConfig();
    }

    public void addMessage(String holderName, String message) {
        String holderKey = HOLDER_PREFIX + holderName;
        if (holders.containsKey(holderKey)) {
            holders.get(holderKey).add(message);
        } else {
            List<String> list = new ArrayList<>();
            list.add(message);
            holders.put(holderKey, list);
        }
        save();
    }

    public Optional<List<String>> getHolderMessages(String holderName) {
        return Optional.of(holders.get(HOLDER_PREFIX + holderName));
    }

    public List<String> getHolders() {
        List<String> list = new ArrayList<>(holders.keySet());
        list.replaceAll(holder -> holder.replace(HOLDER_PREFIX, ""));
        return list;
    }

    public void removeMessage(String holder, int number) {
        try {
            holders.get(HOLDER_PREFIX + holder).remove(number - 1);
            save();
        } catch (IndexOutOfBoundsException e) {
            throw new InformException("Message number " + number + " does not exist");
        } catch (NullPointerException e) {
            throw new InformException("Holder " + holder + " does not exist");
        }

    }

    public String getRandomMessage(String holder) {
        List<String> messages = holders.get(HOLDER_PREFIX + holder);
        if (messages == null) {
            throw new InformException("Holder " + holder + " does not exist");
        }
        return messages.get((int) (Math.random() * messages.size()));
    }
}
