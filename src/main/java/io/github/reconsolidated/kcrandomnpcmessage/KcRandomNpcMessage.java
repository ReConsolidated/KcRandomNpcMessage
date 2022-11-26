package io.github.reconsolidated.kcrandomnpcmessage;

import io.github.reconsolidated.kcrandomnpcmessage.Krnm.KrnmCommand;
import io.github.reconsolidated.kcrandomnpcmessage.Krnm.KrnmService;
import org.bukkit.plugin.java.JavaPlugin;

public final class KcRandomNpcMessage extends JavaPlugin {

    @Override
    public void onEnable() {
        KrnmService krnmService = new KrnmService(this);
        getCommand("krnm").setExecutor(new KrnmCommand(krnmService));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
