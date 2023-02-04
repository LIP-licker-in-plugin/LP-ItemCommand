package com.darksoldier1404.dic.functions;

import com.darksoldier1404.dic.ItemCommand;
import com.darksoldier1404.dic.enums.CommandAction;
import com.darksoldier1404.dppc.utils.NBT;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.UUID;

import static com.darksoldier1404.dic.ItemCommand.prefix;

@SuppressWarnings("all")
public class DICFunction {
    private static final ItemCommand plugin = ItemCommand.getInstance();

    public static void addLeftCommand(Player p, String[] args) {
        if (isHoldItem(p)) {
            String cmd = getArgs(args, 1);
            p.setItemInHand(NBT.setStringTag(p.getItemInHand(), "left_cmd", cmd));
            p.sendMessage(prefix + plugin.lang.getWithArgs("func_left_cmd_registered", cmd));
        } else {
            p.sendMessage(prefix + plugin.lang.get("func_item_required"));
        }
    }

    public static void addRightCommand(Player p, String[] args) {
        if (isHoldItem(p)) {
            String cmd = getArgs(args, 1);
            p.setItemInHand(NBT.setStringTag(p.getItemInHand(), "right_cmd", cmd));
            p.sendMessage(prefix + plugin.lang.getWithArgs("func_right_cmd_registered", cmd));
        } else {
            p.sendMessage(prefix + plugin.lang.get("func_item_required"));
        }
    }

    public static void addShiftSwapCommand(Player p, String[] args) {
        if (isHoldItem(p)) {
            String cmd = getArgs(args, 1);
            p.setItemInHand(NBT.setStringTag(p.getItemInHand(), "shift_f_cmd", cmd));
            p.sendMessage(prefix + plugin.lang.getWithArgs("func_shift_swap_cmd_registered", cmd));
        } else {
            p.sendMessage(prefix + plugin.lang.get("func_item_required"));
        }
    }

    public static void removeLeftCommand(Player p) {
        if (isHoldItem(p)) {
            p.setItemInHand(NBT.removeTag(p.getItemInHand(), "left_cmd"));
            p.sendMessage(prefix + plugin.lang.get("func_left_cmd_deleted"));
        } else {
            p.sendMessage(prefix + plugin.lang.get("func_item_required"));
        }
    }

    public static void removeRightCommand(Player p) {
        if (isHoldItem(p)) {
            p.setItemInHand(NBT.removeTag(p.getItemInHand(), "right_cmd"));
            p.sendMessage(prefix + plugin.lang.get("func_right_cmd_deleted"));
        } else {
            p.sendMessage(prefix + plugin.lang.get("func_item_required"));
        }
    }

    public static void removeShiftSwapCommand(Player p) {
        if (isHoldItem(p)) {
            p.setItemInHand(NBT.removeTag(p.getItemInHand(), "shift_f_cmd"));
            p.sendMessage(prefix + plugin.lang.get("func_shift_swap_cmd_deleted"));
        } else {
            p.sendMessage(prefix + plugin.lang.get("func_item_required"));
        }
    }

    public static void makeAdminLeftCommand(Player p) {
        if (isHoldItem(p)) {
            if (NBT.hasTagKey(p.getItemInHand(), "left_cmd")) {
                if (NBT.hasTagKey(p.getItemInHand(), "left_cmd_isAdmin")) {
                    p.setItemInHand(NBT.removeTag(p.getItemInHand(), "left_cmd_isAdmin"));
                    p.sendMessage(prefix + plugin.lang.get("func_left_cmd_make_admin"));
                } else {
                    p.setItemInHand(NBT.setStringTag(p.getItemInHand(), "left_cmd_isAdmin", "true"));
                    p.sendMessage(prefix + plugin.lang.get("func_left_cmd_make_admin"));
                }
            } else {
                p.sendMessage(prefix + plugin.lang.get("func_left_cmd_is_not_registered"));
            }
        } else {
            p.sendMessage(prefix + plugin.lang.get("func_item_required"));
        }
    }

    public static void makeAdminRightCommand(Player p) {
        if (isHoldItem(p)) {
            if (NBT.hasTagKey(p.getItemInHand(), "right_cmd")) {
                if (NBT.hasTagKey(p.getItemInHand(), "right_cmd_isAdmin")) {
                    p.setItemInHand(NBT.removeTag(p.getItemInHand(), "right_cmd_isAdmin"));
                    p.sendMessage(prefix + plugin.lang.get("func_right_cmd_make_admin"));
                } else {
                    p.setItemInHand(NBT.setStringTag(p.getItemInHand(), "right_cmd_isAdmin", "true"));
                    p.sendMessage(prefix + plugin.lang.get("func_right_cmd_make_admin"));
                }
            } else {
                p.sendMessage(prefix + plugin.lang.get("func_right_cmd_is_not_registered"));
            }
        } else {
            p.sendMessage(prefix + plugin.lang.get("func_item_required"));
        }
    }

    public static void makeAdminSwapCommand(Player p) {
        if (isHoldItem(p)) {
            if (NBT.hasTagKey(p.getItemInHand(), "shift_f_cmd")) {
                if (NBT.hasTagKey(p.getItemInHand(), "shift_f_cmd_isAdmin")) {
                    p.setItemInHand(NBT.removeTag(p.getItemInHand(), "shift_f_cmd_isAdmin"));
                    p.sendMessage(prefix + plugin.lang.get("func_shift_swap_cmd_make_admin"));
                } else {
                    p.setItemInHand(NBT.setStringTag(p.getItemInHand(), "shift_f_cmd_isAdmin", "true"));
                    p.sendMessage(prefix + plugin.lang.get("func_shift_swap_cmd_make_admin"));
                }
            } else {
                p.sendMessage(prefix + plugin.lang.get("func_shift_swap_cmd_is_not_registered"));
            }
        } else {
            p.sendMessage(prefix + plugin.lang.get("func_item_required"));
        }
    }

    public static void makeConsuming(Player p) {
        if (isHoldItem(p)) {
            if (NBT.hasTagKey(p.getItemInHand(), "isConsume")) {
                p.setItemInHand(NBT.removeTag(p.getItemInHand(), "isConsume"));
                p.sendMessage(prefix + plugin.lang.get("func_make_not_consume_item"));
            } else {
                p.setItemInHand(NBT.setStringTag(p.getItemInHand(), "isConsume", "true"));
                p.sendMessage(prefix + plugin.lang.get("func_make_consume_item"));
            }
        } else {
            p.sendMessage(prefix + plugin.lang.get("func_item_required"));
        }
    }

    public static boolean isHoldItem(Player p) {
        return p.getItemInHand().getType() != Material.AIR;
    }

    public static String getArgs(String[] args, int index) {
        StringBuilder s = new StringBuilder();
        args = Arrays.copyOfRange(args, index, args.length);
        Iterator<String> i = Arrays.stream(args).iterator();
        while (i.hasNext()) {
            s.append(i.next()).append(" ");
        }
        if (s.charAt(s.length() - 1) == ' ') {
            s.deleteCharAt(s.length() - 1);
        }
        return s.toString();
    }

    public static void makeCooldown(Player p, float cooldown, CommandAction ca) {
        if (isHoldItem(p)) {
            if (!NBT.hasTagKey(p.getItemInHand(), "dic_uuid")) {
                p.setItemInHand(NBT.setStringTag(p.getItemInHand(), "dic_uuid", UUID.randomUUID().toString()));
            }
            if (NBT.hasTagKey(p.getItemInHand(), "cooldown") && cooldown == 0) {
                if (ca == CommandAction.LEFT) {
                    p.setItemInHand(NBT.removeTag(p.getItemInHand(), "cooldown_left"));
                }
                if (ca == CommandAction.RIGHT) {
                    p.setItemInHand(NBT.removeTag(p.getItemInHand(), "cooldown_right"));
                }
                if (ca == CommandAction.SWAP) {
                    p.setItemInHand(NBT.removeTag(p.getItemInHand(), "cooldown_swap"));
                }
                p.sendMessage(prefix + plugin.lang.get("func_cooldown_deleted"));
            } else {
                if (ca == CommandAction.LEFT) {
                    p.setItemInHand(NBT.setFloatTag(p.getItemInHand(), "cooldown_left", cooldown));
                }
                if (ca == CommandAction.RIGHT) {
                    p.setItemInHand(NBT.setFloatTag(p.getItemInHand(), "cooldown_right", cooldown));
                }
                if (ca == CommandAction.SWAP) {
                    p.setItemInHand(NBT.setFloatTag(p.getItemInHand(), "cooldown_swap", cooldown));
                }
                p.sendMessage(prefix + plugin.lang.getWithArgs("func_cooldown_set", String.valueOf(cooldown)));
            }
        } else {
            p.sendMessage(prefix + plugin.lang.get("func_item_required"));
        }
    }

    public static boolean isCooldown(Player p, ItemStack item, CommandAction ca) {
        if (!plugin.cooldown.containsKey(p.getUniqueId())) return false;
        if (!plugin.cooldown.get(p.getUniqueId()).containsKey(UUID.fromString(NBT.getStringTag(item, "dic_uuid")))) return false;
        if (!plugin.cooldown.get(p.getUniqueId()).get(UUID.fromString(NBT.getStringTag(item, "dic_uuid"))).containsKey(ca)){
            return false;
        }else{
            return true;
        }
    }

    public static boolean hasCooldown(Player p, ItemStack item, CommandAction ca) {
        String action;
        if (ca == CommandAction.LEFT) {
            action = "cooldown_left";
        } else if (ca == CommandAction.RIGHT) {
            action = "cooldown_right";
        } else {
            action = "cooldown_swap";
        }
        if (NBT.hasTagKey(item, action)) return true;
        return false;
    }

    public static void cooldown(Player p, CommandAction ca, int cooldown) {
        if (!plugin.cooldown.containsKey(p.getUniqueId())) {
            plugin.cooldown.put(p.getUniqueId(), new HashMap<>());
        }
        UUID uuid = null;
        try {
            uuid = UUID.fromString(NBT.getStringTag(p.getItemInHand(), "dic_uuid"));
        } catch (Exception ignored) {
        }
        if (!plugin.cooldown.get(p.getUniqueId()).containsKey(uuid)) {
            plugin.cooldown.get(p.getUniqueId()).put(uuid, new HashMap<>());
        }
        plugin.cooldown.get(p.getUniqueId()).get(uuid).put(ca, cooldown);
        UUID finalUuid = uuid;
        Bukkit.getScheduler().runTaskLater(ItemCommand.getInstance(), () -> {
            if (plugin.cooldown.containsKey(p.getUniqueId())) {
                if (plugin.cooldown.get(p.getUniqueId()).containsKey(finalUuid)) {
                    plugin.cooldown.get(p.getUniqueId()).get(finalUuid).remove(ca);
                }
            }
        }, (long) (cooldown * 20));
    }
}

