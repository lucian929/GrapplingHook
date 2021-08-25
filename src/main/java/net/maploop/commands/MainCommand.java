package net.maploop.commands;

import net.maploop.GrapplingHook;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.maploop.Util;

import java.util.ArrayList;
import java.util.List;

public class MainCommand implements CommandExecutor {
    GrapplingHook plugin;
    public MainCommand(GrapplingHook plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        Player p = (Player) sender;
        if (s.equalsIgnoreCase("grapplinghook") || s.equalsIgnoreCase("gh")) {
            if (p.hasPermission("grapplinghook.admin")) {
                if (args.length == 0) {
                    p.sendMessage(Util.chat("&d&m                                               &r"));
                    p.sendMessage(Util.chat(""));
                    p.sendMessage(Util.chat("&a&lGrapplingHook:"));
                    p.sendMessage(Util.chat("&aCommands:"));
                    p.sendMessage(Util.chat("&d/grapplinghook help &7- Shows this page."));
                    p.sendMessage(Util.chat("&d/grapplinghook give <player> &7- Give the grappling hook item to a player."));
                    p.sendMessage(Util.chat("&d/grapplinghook reload &7- Reload the plugin config."));
                    p.sendMessage("");
                    p.sendMessage("");
                    p.sendMessage(Util.chat("&8Version 1.9 by Maploop & Raindropz"));
                    p.sendMessage(Util.chat("&d&m                                               &r"));
                    return true;
                }
                if (args[0].equalsIgnoreCase("help")) {
                    p.sendMessage(Util.chat("&d&m                                               &r"));
                    p.sendMessage(Util.chat(""));
                    p.sendMessage(Util.chat("&a&lGrapplingHook:"));
                    p.sendMessage(Util.chat("&aCommands:"));
                    p.sendMessage(Util.chat("&d/grapplinghook help &7- Shows this page."));
                    p.sendMessage(Util.chat("&d/grapplinghook give <player> &7- Give the grappling hook item to a player."));
                    p.sendMessage(Util.chat("&d/grapplinghook reload &7- Reload the plugin config."));
                    p.sendMessage("");
                    p.sendMessage("");
                    p.sendMessage(Util.chat("&8Version 1.9 by Maploop & Raindropz"));
                    p.sendMessage(Util.chat("&d&m                                               &r"));
                }
                if (args[0].equalsIgnoreCase("give")) {
                    if (args[1] != null) {
                        Player target = Bukkit.getPlayer(args[1]);
                        if (target != null && target.isOnline()) {
                            ItemStack grappling_hook = new ItemStack(Material.FISHING_ROD);
                            ItemMeta grappling_hook_meta = grappling_hook.getItemMeta();
                            grappling_hook_meta.setDisplayName(Util.chat(plugin.getConfig().getString("grapplinghook.displayname")));
                            List<String> grappling_hook_lore = new ArrayList<String>();
                            if (plugin.getConfig().getBoolean("grapplinghook.lore-enabled")) {
                                for(String agrappling_hook_lore : plugin.getConfig().getStringList("grapplinghook.lore")){
                                    grappling_hook_lore.add(Util.chat(agrappling_hook_lore));
                                }
                            }
                            if (plugin.getConfig().getBoolean("grapplinghook.shiny")) {
                                grappling_hook_meta.addEnchant(Enchantment.LUCK, 1, false);
                            }
                            if (plugin.getConfig().getBoolean("grapplinghook.unbreakable")) {
                                grappling_hook_meta.setUnbreakable(true);
                            }

                            if (plugin.getConfig().getBoolean("grapplinghook.use-custom-model-data")) {
                                grappling_hook_meta.setCustomModelData(plugin.getConfig().getInt("grapplinghook.data"));
                            }

                            grappling_hook_meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
                            grappling_hook_meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                            grappling_hook_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                            grappling_hook_meta.setLore(grappling_hook_lore);

                            grappling_hook.setItemMeta(grappling_hook_meta);
                            target.getInventory().addItem(grappling_hook);
                            p.sendMessage(Util.chat("&aGave " + target.getName() + " &8x1 &eGRAPPLING_HOOK&a."));
                        } else {
                            p.sendMessage(Util.chat("&cPlayer not found."));
                        }
                    }
                }
                if (args[0].equalsIgnoreCase("reload")) {
                    plugin.reloadConfig();
                    p.sendMessage(ChatColor.GREEN + "Config file successfully reloaded.");
                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 2F, 2);
                }

            } else {
                p.sendMessage(Util.chat(plugin.getConfig().getString("messages.no-permission")));
            }
        }
                    return true;
                }

}
