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
import org.bukkit.inventory.InventoryHolder;
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
        if (Objects.requireNonNull(Objects.requireNonNull(event.getCurrentItem()).getItemMeta()).getDisplayName().equals(ChatColor.YELLOW + "Toggle Chat Filter"))
        {
            Player player = (Player) event.getWhoClicked();
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
        event.setCancelled(true);
    }

    public void openSettingsGUI(Player player)
    {
        final Inventory inventory = Bukkit.createInventory((InventoryHolder) null, 9, ChatColor.YELLOW + "Settings");

        final ItemStack chatFilterSetting = new ItemStack(Material.BOOK);
        final ItemMeta chatFilterSettingMeta = chatFilterSetting.getItemMeta();
        assert chatFilterSettingMeta != null;
        chatFilterSettingMeta.setDisplayName(ChatColor.YELLOW + "Toggle Chat Filter");
        final List<String> chatFilterLore = new ArrayList<String>();
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
        chatFilterSettingMeta.setLore(chatFilterLore);
        chatFilterSetting.setItemMeta(chatFilterSettingMeta);
        inventory.setItem(0, chatFilterSetting);

        player.openInventory(inventory);
    }

}
