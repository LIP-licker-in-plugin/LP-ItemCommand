package com.licker2689.lic.commands;

import com.licker2689.lic.ItemCommand;
import com.licker2689.lic.enums.CommandAction;
import com.licker2689.lic.functions.LICFunction;
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
public class LICCommand implements CommandExecutor, TabCompleter {
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
            LICFunction.addLeftCommand(p, args);
            return false;
        }
        if(args[0].equalsIgnoreCase("R")) {
            if(args.length == 1) {
                p.sendMessage(plugin.prefix + plugin.lang.get("cmd_msg_command_required"));
                return false;
            }
            LICFunction.addRightCommand(p, args);
            return false;
        }
        if(args[0].equalsIgnoreCase("F")) {
            if(args.length == 1) {
                p.sendMessage(plugin.prefix + plugin.lang.get("cmd_msg_command_required"));
                return false;
            }
            LICFunction.addShiftSwapCommand(p, args);
            return false;
        }
        if(args[0].equalsIgnoreCase("A")) {
            if(args.length == 1) {
                p.sendMessage(plugin.prefix + plugin.lang.get("cmd_msg_action_required"));
                return false;
            }
            if(args.length == 2) {
                if(args[1].equalsIgnoreCase("L")) {
                    LICFunction.makeAdminLeftCommand(p);
                    return false;
                }
                if(args[1].equalsIgnoreCase("R")) {
                    LICFunction.makeAdminRightCommand(p);
                    return false;
                }
                if(args[1].equalsIgnoreCase("F")) {
                    LICFunction.makeAdminSwapCommand(p);
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
                    LICFunction.removeLeftCommand(p);
                    return false;
                }
                if(args[1].equalsIgnoreCase("R")) {
                    LICFunction.removeRightCommand(p);
                    return false;
                }
                if(args[1].equalsIgnoreCase("F")) {
                    LICFunction.removeShiftSwapCommand(p);
                    return false;
                }
                p.sendMessage(plugin.prefix + plugin.lang.get("cmd_msg_command_required"));
                return false;
            }
        }
        if(args[0].equalsIgnoreCase("C")) {
            LICFunction.makeConsuming(p);
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
                    LICFunction.makeCooldown(p, cooldown, ca);
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
