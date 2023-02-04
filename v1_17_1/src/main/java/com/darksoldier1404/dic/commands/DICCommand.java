package com.darksoldier1404.dic.commands;

import com.darksoldier1404.dic.ItemCommand;
import com.darksoldier1404.dic.enums.CommandAction;
import com.darksoldier1404.dic.functions.DICFunction;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

@SuppressWarnings("all")
public class DICCommand implements CommandExecutor, TabCompleter {
    private final ItemCommand plugin = ItemCommand.getInstance();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(!sender.isOp()) {
            sender.sendMessage(plugin.prefix + plugin.lang.get("cmd_msg_only_opreator_can_use"));
            return false;
        }
        if(!(sender instanceof Player)) {
            sender.sendMessage(plugin.prefix + plugin.lang.get("cmd_msg_only_player_can_use"));
            return false;
        }
        Player p = (Player) sender;
        if(args.length == 0) {
            p.sendMessage(plugin.prefix + plugin.lang.get("cmd_info_L"));
            p.sendMessage(plugin.prefix + plugin.lang.get("cmd_info_R"));
            p.sendMessage(plugin.prefix + plugin.lang.get("cmd_info_F"));
            p.sendMessage(plugin.prefix + plugin.lang.get("cmd_info_A"));
            p.sendMessage(plugin.prefix + plugin.lang.get("cmd_info_D"));
            p.sendMessage(plugin.prefix + plugin.lang.get("cmd_info_C"));
            p.sendMessage(plugin.prefix + plugin.lang.get("cmd_info_CD"));
            return false;
        }
        if(args[0].equalsIgnoreCase("L")) {
            if(args.length == 1) {
                p.sendMessage(plugin.prefix + plugin.lang.get("cmd_msg_command_required"));
                return false;
            }
            DICFunction.addLeftCommand(p, args);
            return false;
        }
        if(args[0].equalsIgnoreCase("R")) {
            if(args.length == 1) {
                p.sendMessage(plugin.prefix + plugin.lang.get("cmd_msg_command_required"));
                return false;
            }
            DICFunction.addRightCommand(p, args);
            return false;
        }
        if(args[0].equalsIgnoreCase("F")) {
            if(args.length == 1) {
                p.sendMessage(plugin.prefix + plugin.lang.get("cmd_msg_command_required"));
                return false;
            }
            DICFunction.addShiftSwapCommand(p, args);
            return false;
        }
        if(args[0].equalsIgnoreCase("A")) {
            if(args.length == 1) {
                p.sendMessage(plugin.prefix + plugin.lang.get("cmd_msg_action_required"));
                return false;
            }
            if(args.length == 2) {
                if(args[1].equalsIgnoreCase("L")) {
                    DICFunction.makeAdminLeftCommand(p);
                    return false;
                }
                if(args[1].equalsIgnoreCase("R")) {
                    DICFunction.makeAdminRightCommand(p);
                    return false;
                }
                if(args[1].equalsIgnoreCase("F")) {
                    DICFunction.makeAdminSwapCommand(p);
                    return false;
                }
                p.sendMessage(plugin.prefix + plugin.lang.get("cmd_msg_action_required"));
                return false;
            }
        }
        if(args[0].equalsIgnoreCase("D")) {
            if(args.length == 1) {
                p.sendMessage(plugin.prefix + plugin.lang.get("cmd_msg_command_required"));
                return false;
            }
            if(args.length == 2) {
                if(args[1].equalsIgnoreCase("L")) {
                    DICFunction.removeLeftCommand(p);
                    return false;
                }
                if(args[1].equalsIgnoreCase("R")) {
                    DICFunction.removeRightCommand(p);
                    return false;
                }
                if(args[1].equalsIgnoreCase("F")) {
                    DICFunction.removeShiftSwapCommand(p);
                    return false;
                }
                p.sendMessage(plugin.prefix + plugin.lang.get("cmd_msg_command_required"));
                return false;
            }
        }
        if(args[0].equalsIgnoreCase("C")) {
            DICFunction.makeConsuming(p);
            return false;
        }
        if(args[0].equalsIgnoreCase("CD")) {
            if(args.length == 1) {
                p.sendMessage(plugin.prefix + plugin.lang.get("cmd_msg_cooldown_required"));
                return false;
            }
            if(args.length == 2) {
                p.sendMessage(plugin.prefix + plugin.lang.get("cmd_msg_cooldown_required"));
                return false;
            }
            if(args.length == 3) {
                try{
                    float cooldown = Float.parseFloat(args[2]);
                    CommandAction ca = null;
                    if(args[1].equalsIgnoreCase("L")) {
                        ca = CommandAction.LEFT;
                    }
                    else if(args[1].equalsIgnoreCase("R")) {
                        ca = CommandAction.RIGHT;
                    }
                    else if(args[1].equalsIgnoreCase("F")) {
                        ca = CommandAction.SWAP;
                    }
                    if(ca == null) {
                        p.sendMessage(plugin.prefix + plugin.lang.get("cmd_msg_action_required"));
                        return false;
                    }
                    DICFunction.makeCooldown(p, cooldown, ca);
                    return false;
                }catch (Exception e){
                    p.sendMessage(plugin.prefix + plugin.lang.get("cmd_msg_number_required"));
                    return false;
                }
            }
        }
        return false;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(args.length == 1) {
            return Arrays.asList("L", "R", "F", "A", "D", "C", "CD");
        }
        if(args.length == 2) {
            if(args[0].equalsIgnoreCase("A") || args[0].equalsIgnoreCase("D") || args[0].equalsIgnoreCase("CD")) {
                return Arrays.asList("L", "R", "F");
            }
        }
        return null;
    }
}
