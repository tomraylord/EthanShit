package cloud.timothy.genericplugin.chat;

import cloud.timothy.genericplugin.chat.filter.Filter;
import cloud.timothy.genericplugin.settings.data.FilterEnabled;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatEvent implements Listener
{

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event)
    {
        event.setCancelled(true);
        boolean filtered = false;
        for (int i = 0; Filter.filteredWords.size() > i; i++)
        {
            if (event.getMessage().toLowerCase().contains(Filter.filteredWords.get(i)))
            {
                filtered = true;
            }
        }
        if (!filtered)
        {
            Bukkit.broadcastMessage(event.getPlayer().getDisplayName() + ChatColor.RESET + ": " + event.getMessage());
            return;
        }
        System.out.println("(FILTERED) " + event.getPlayer().getName() + ": " + event.getMessage());
        for (Player player : Bukkit.getOnlinePlayers())
        {
            if (FilterEnabled.filterEnabled.contains(player.getUniqueId().toString()))
            {
                return;
            }
            player.sendMessage(event.getPlayer().getDisplayName() + ChatColor.RESET + ": " + event.getMessage());
        }
    }

}
