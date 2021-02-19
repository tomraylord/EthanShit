package cloud.timothy.genericplugin.color;

import cloud.timothy.genericplugin.GenericPluginClass;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class ColorJoin implements Listener
{

    @EventHandler
    public void onJoin(PlayerJoinEvent event)
    {
        if (!(GenericPluginClass.getPlugin().getConfig().contains("settings.color." + event.getPlayer().getUniqueId().toString())))
        {
            GenericPluginClass.getPlugin().getConfig().set("settings.color." + event.getPlayer().getUniqueId().toString(), "f");
        }
        event.getPlayer().setDisplayName(ChatColor.translateAlternateColorCodes('&', "&" +
                GenericPluginClass.getPlugin().getConfig().get("settings.color." + event.getPlayer().getUniqueId().toString())) + event.getPlayer().getName());
    }

}
