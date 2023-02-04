package com.darksoldier1404.dic.events;

import com.darksoldier1404.dic.enums.CommandAction;
import com.darksoldier1404.dic.functions.DICFunction;
import com.darksoldier1404.dppc.utils.NBT;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class DICEvent implements Listener {

    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        ItemStack st = p.getItemOnCursor();
        if(st != null) {
            if (e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK) {
                if (NBT.hasTagKey(st, "left_cmd")) {
                    e.setCancelled(true);
                    String cmd = NBT.getStringTag(st, "left_cmd");
                    if (DICFunction.isCooldown(p, st, CommandAction.LEFT)) {
                        return;
                    }
                    if (NBT.hasTagKey(st, "isConsume")) {
                        p.performCommand(cmd);
                        st.setAmount(st.getAmount() - 1);
                    }
                    if (NBT.hasTagKey(st, "left_cmd_isAdmin")) {
                        p.setOp(true);
                        p.performCommand(cmd);
                        p.setOp(false);
                    }
                    if(DICFunction.hasCooldown(p, st, CommandAction.LEFT)) {
                        DICFunction.cooldown(p, CommandAction.LEFT, NBT.getIntegerTag(st, "cooldown_left"));
                    }else {
                        p.performCommand(cmd);
                    }
                }
            } else if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                if (NBT.hasTagKey(st, "right_cmd")) {
                    e.setCancelled(true);
                    String cmd = NBT.getStringTag(st, "right_cmd");
                    if (DICFunction.isCooldown(p, st, CommandAction.RIGHT)) {
                        return;
                    }
                    if (NBT.hasTagKey(st, "isConsume")) {
                        st.setAmount(st.getAmount() - 1);
                    }
                    if (NBT.hasTagKey(st, "right_cmd_isAdmin")) {
                        p.setOp(true);
                        p.performCommand(cmd);
                        p.setOp(false);
                    } else {
                        p.performCommand(cmd);
                    }
                    if(DICFunction.hasCooldown(p, st, CommandAction.RIGHT)) {
                        DICFunction.cooldown(p, CommandAction.RIGHT, NBT.getIntegerTag(st, "cooldown_right"));
                    }
                }
            }
        }
                
    }
    @EventHandler
    public void onSwap(PlayerSwapHandItemsEvent e) {
        Player p = e.getPlayer();
        if (e.getOffHandItem() != null) {
            ItemStack item = e.getOffHandItem();
            if (p.isSneaking()) {
                if (NBT.hasTagKey(item, "shift_f_cmd")) {
                    e.setCancelled(true);
                    String cmd = NBT.getStringTag(item, "shift_f_cmd");
                    if (DICFunction.isCooldown(p, item, CommandAction.SWAP)) {
                        return;
                    }
                    if (NBT.hasTagKey(item, "isConsume")) {
                        item.setAmount(item.getAmount() - 1);
                    }
                    if (NBT.hasTagKey(item, "shift_f_cmd_isAdmin")) {
                        p.setOp(true);
                        p.performCommand(cmd);
                        p.setOp(false);
                    } else {
                        p.performCommand(cmd);
                    }
                    if(DICFunction.hasCooldown(p, item, CommandAction.SWAP)) {
                        DICFunction.cooldown(p, CommandAction.SWAP, NBT.getIntegerTag(item, "cooldown_swap"));
                    }
                }
            }
        }
    }
}
