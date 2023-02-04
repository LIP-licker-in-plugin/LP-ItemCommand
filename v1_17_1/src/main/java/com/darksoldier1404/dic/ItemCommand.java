package com.darksoldier1404.dic;

import com.darksoldier1404.dic.commands.DICCommand;
import com.darksoldier1404.dic.enums.CommandAction;
import com.darksoldier1404.dic.events.DICEvent;
import com.darksoldier1404.dppc.lang.DLang;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@SuppressWarnings("all")
public class ItemCommand extends JavaPlugin {
    private static ItemCommand plugin;
    public static final String prefix = "§f[ §aDIC §f] ";
    public static final Map<UUID, Map<UUID, Map<CommandAction, Integer>>> cooldown = new HashMap<>();
    // key is player uuid, value is map of command action and cooldown time for each items
    public static DLang lang;

    public static ItemCommand getInstance() {
        return plugin;
    }

    @Override
    public void onEnable() {
        plugin = this;
        lang = new DLang("Korean", plugin);
        plugin.getServer().getPluginManager().registerEvents(new DICEvent(), plugin);
        getCommand("dic").setExecutor(new DICCommand());
    }
}
