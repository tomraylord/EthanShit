package cloud.timothy.genericplugin.color;

import cloud.timothy.genericplugin.GenericPluginClass;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ColorCommand implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player))
        {
            return true;
        }
        Player p = (Player) sender;
        if (args.length != 1 || args[0] == null || args[0].equalsIgnoreCase("help"))
        {
            p.sendMessage(ChatColor.AQUA + "You are shown as " + ChatColor.WHITE + "'" + p.getDisplayName() + ChatColor.WHITE + "'" + ChatColor.AQUA + ".");
            p.sendMessage(ChatColor.YELLOW + "" + ChatColor.STRIKETHROUGH + "--" + ChatColor.RESET + ChatColor.AQUA + " Color Command Help " + ChatColor.YELLOW + "" + ChatColor.STRIKETHROUGH + "--");
            p.sendMessage(ChatColor.YELLOW + " - " + ChatColor.AQUA + "/color <ChatColor>");
            p.sendMessage(ChatColor.YELLOW + " - " + ChatColor.AQUA + "/color help");
            p.sendMessage(ChatColor.YELLOW + " - " + ChatColor.AQUA + "/color list");
            return true;
        }
        if (args[0].equalsIgnoreCase("list"))
        {
            p.sendMessage(ChatColor.AQUA + "Here is a list of ChatColor codes:");
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&44 &cc &66 &ee &22 &aa &bb &33 &11 &99 &dd &55 &ff &77 &88 &00"));
            return true;
        }
        if (!(ChatColor.getByChar(args[0]).isColor()))
        {
            p.sendMessage(ChatColor.AQUA + "You are shown as " + ChatColor.WHITE + "'" + p.getDisplayName() + ChatColor.WHITE + "'" + ChatColor.AQUA + ".");
            p.sendMessage(ChatColor.YELLOW + "" + ChatColor.STRIKETHROUGH + "--" + ChatColor.RESET + ChatColor.AQUA + " Color Command Help " + ChatColor.YELLOW + "" + ChatColor.STRIKETHROUGH + "--");
            p.sendMessage(ChatColor.YELLOW + " - " + ChatColor.AQUA + "/color <ChatColor>");
            p.sendMessage(ChatColor.YELLOW + " - " + ChatColor.AQUA + "/color help");
            p.sendMessage(ChatColor.YELLOW + " - " + ChatColor.AQUA + "/color list");
            return true;
        }
        p.setDisplayName(ChatColor.getByChar(args[0]) + p.getName());
        GenericPluginClass.getPlugin().getConfig().set("settings.color." + p.getUniqueId().toString(), null);
        GenericPluginClass.getPlugin().getConfig().set("settings.color." + p.getUniqueId().toString(), args[0]);
        GenericPluginClass.getPlugin().saveConfig();
        p.sendMessage(ChatColor.GREEN + "You are now shown as " + ChatColor.WHITE + "'" + p.getDisplayName() + ChatColor.WHITE + "'" + ChatColor.GREEN + ".");
        return true;
    }
}
