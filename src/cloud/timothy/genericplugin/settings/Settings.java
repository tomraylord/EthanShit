package cloud.timothy.genericplugin.settings;

import cloud.timothy.genericplugin.settings.data.FilterEnabled;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Settings implements CommandExecutor, Listener
{

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if (!(sender instanceof Player))
        {
            return true;
        }
        openSettingsGUI((Player) sender);
        return true;
    }

    @EventHandler
    public void guiClick(InventoryClickEvent event)
    {
        if (!(event.getView().getTitle().equals(ChatColor.YELLOW + "Settings")))
        {
            return;
        }
        if (event.getCurrentItem() == null)
        {
            return;
        }
        Player player = (Player) event.getWhoClicked();
        if (Objects.requireNonNull(Objects.requireNonNull(event.getCurrentItem()).getItemMeta()).getDisplayName().equals(ChatColor.YELLOW + "Toggle Chat Filter"))
        {
            if (FilterEnabled.filterEnabled.contains(event.getWhoClicked().getUniqueId().toString()))
            {
                FilterEnabled.filterEnabled.remove(event.getWhoClicked().getUniqueId().toString());
            }
            else
            {
                FilterEnabled.filterEnabled.add(event.getWhoClicked().getUniqueId().toString());
            }
            openSettingsGUI(player);
        }
        if (Objects.requireNonNull(Objects.requireNonNull(event.getCurrentItem()).getItemMeta()).getDisplayName().equals(ChatColor.YELLOW + "Toggle Scoreboard"))
        {
            player.chat("/sgs");
        }
        if (Objects.requireNonNull(Objects.requireNonNull(event.getCurrentItem()).getItemMeta()).getDisplayName().equals(ChatColor.RED + "Click to close."))
        {
            player.closeInventory();
        }
        event.setCancelled(true);
    }

    public void openSettingsGUI(Player player)
    {
        final Inventory inventory = Bukkit.createInventory(null, 9, ChatColor.YELLOW + "Settings");

        final ItemStack chatFilterSetting = new ItemStack(Material.BOOK);
        final ItemMeta chatFilterSettingMeta = chatFilterSetting.getItemMeta();
        assert chatFilterSettingMeta != null;
        chatFilterSettingMeta.setDisplayName(ChatColor.YELLOW + "Toggle Chat Filter");
        final List<String> chatFilterLore = new ArrayList<>();
        chatFilterLore.add("");
        chatFilterLore.add(ChatColor.GRAY + "The Chat Filter will remove all");
        chatFilterLore.add(ChatColor.GRAY + "inappropriate messages from the");
        chatFilterLore.add(ChatColor.GRAY + "chat. Perfect for streamers.");
        chatFilterLore.add("");
        chatFilterLore.add(ChatColor.GRAY + "Click to toggle.");
        chatFilterLore.add("");
        if (FilterEnabled.filterEnabled.contains(player.getUniqueId().toString()))
        {
            chatFilterLore.add(ChatColor.GREEN + " ▶ Enabled");
            chatFilterLore.add(ChatColor.GRAY + " ▶ Disabled");
        }
        else {
            chatFilterLore.add(ChatColor.GRAY + " ▶ Enabled");
            chatFilterLore.add(ChatColor.RED + " ▶ Disabled");
        }
        chatFilterLore.add("");
        chatFilterSettingMeta.setLore(chatFilterLore);
        chatFilterSetting.setItemMeta(chatFilterSettingMeta);
        inventory.setItem(0, chatFilterSetting);

        final ItemStack toggleScoreboard = new ItemStack(Material.PAPER);
        final ItemMeta toggleScoreboardMeta = toggleScoreboard.getItemMeta();
        assert toggleScoreboardMeta != null;
        toggleScoreboardMeta.setDisplayName(ChatColor.YELLOW + "Toggle Scoreboard");
        final List<String> toggleScoreboardMetaLore = new ArrayList<>();
        toggleScoreboardMetaLore.add("");
        toggleScoreboardMetaLore.add(ChatColor.GRAY + "Choose weather you want");
        toggleScoreboardMetaLore.add(ChatColor.GRAY + "the sidebar to be shown");
        toggleScoreboardMetaLore.add(ChatColor.GRAY + "or hidden.");
        toggleScoreboardMetaLore.add("");
        toggleScoreboardMetaLore.add(ChatColor.GRAY + "Click to toggle.");
        toggleScoreboardMetaLore.add("");
        toggleScoreboardMeta.setLore(toggleScoreboardMetaLore);
        toggleScoreboard.setItemMeta(toggleScoreboardMeta);
        inventory.setItem(1, toggleScoreboard);

        final ItemStack exit = new ItemStack(Material.BARRIER);
        final ItemMeta exitMeta = exit.getItemMeta();
        assert exitMeta != null;
        exitMeta.setDisplayName(ChatColor.RED + "Click to close.");
        exit.setItemMeta(exitMeta);
        inventory.setItem(8, exit);

        player.openInventory(inventory);
    }

}
