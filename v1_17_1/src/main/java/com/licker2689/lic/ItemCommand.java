package com.licker2689.lic;

import com.licker2689.lic.commands.LICCommand;
import com.licker2689.lic.enums.CommandAction;
import com.licker2689.lic.events.LICEvent;
import com.licker2689.lpc.lang.LLang;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@SuppressWarnings("all")
public class ItemCommand extends JavaPlugin {
    private static ItemCommand plugin;
    public static final String prefix = "§f[ §aLIC §f] ";
    public static final Map<UUID, Map<UUID, Map<CommandAction, Integer>>> cooldown = new HashMap<>();
    // key is player uuid, value is map of command action and cooldown time for each items
    public static LLang lang;

    public static ItemCommand getInstance() {
        return plugin;
    }

    @Override
    public void onEnable() {
        plugin = this;
        lang = new LLang("Korean", plugin);
        plugin.getServer().getPluginManager().registerEvents(new LICEvent(), plugin);
        getCommand("lic").setExecutor(new LICCommand());
    }
}
