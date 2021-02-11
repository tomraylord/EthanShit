package cloud.timothy.genericplugin;

import cloud.timothy.genericplugin.chat.ChatEvent;
import cloud.timothy.genericplugin.chat.filter.Filter;
import cloud.timothy.genericplugin.chat.filter.FilterCommand;
import cloud.timothy.genericplugin.settings.Settings;
import cloud.timothy.genericplugin.settings.data.FilterEnabled;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class GenericPluginClass extends JavaPlugin
{

    public static Plugin plugin;

    public Filter filter = new Filter();
    public FilterEnabled filterEnabled = new FilterEnabled();

    public void onEnable()
    {
        plugin = this;
        getConfig().options().copyDefaults(true);
        saveConfig();
        Filter.filteredWords = filter.loadData("filter");
        System.out.println("[ShmoneyPlugin] Loading data (1/2)");
        FilterEnabled.filterEnabled = filterEnabled.loadData("settings.filterenabled");
        System.out.println("[ShmoneyPlugin] Loading data (2/2)");
        getServer().getPluginManager().registerEvents(new ChatEvent(), this);
        getServer().getPluginManager().registerEvents(new Settings(), this);
        getCommand("settings").setExecutor(new Settings());
        getCommand("filter").setExecutor(new FilterCommand());
    }

    public void onDisable()
    {
        filter.saveData("filter", Filter.filteredWords);
        System.out.println("[ShmoneyPlugin] Saving data (1/2)");
        filterEnabled.saveData("settings.filterenabled", FilterEnabled.filterEnabled);
        System.out.println("[ShmoneyPlugin] Saving data (2/2)");
    }

    public static Plugin getPlugin()
    {
        return plugin;
    }

}
