package cloud.timothy.genericplugin.chat.filter;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class FilterCommand implements CommandExecutor
{

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if (!(sender.hasPermission("shmoney.admin")))
        {
            sender.sendMessage(ChatColor.RED + "No Permission.");
            return true;
        }
        if (args.length > 2 || args.length == 0 || args[0].equals("help"))
        {
            sender.sendMessage(ChatColor.YELLOW + "" + ChatColor.STRIKETHROUGH + "--" + ChatColor.RESET + ChatColor.RED + " Filter Command Help " + ChatColor.YELLOW + "" + ChatColor.STRIKETHROUGH + "--");
            sender.sendMessage(ChatColor.YELLOW + " - " + ChatColor.RED + "/filter add <String>");
            sender.sendMessage(ChatColor.YELLOW + " - " + ChatColor.RED + "/filter remove <String>");
            sender.sendMessage(ChatColor.YELLOW + " - " + ChatColor.RED + "/filter list");
            sender.sendMessage(ChatColor.YELLOW + " - " + ChatColor.RED + "/filter help");
            return true;
        }
        if (args[0].equals("list"))
        {
            sender.sendMessage(ChatColor.RED + "Filtered Words: " + Filter.filteredWords.toString().substring(1).replace(']', '.'));
            return true;
        }
        boolean add = false;
        if (args[0].equals("add"))
        {
            add = true;
        }
        String filteredPhrase = args[1];
        if (add)
        {
            if ((Filter.filteredWords.contains(filteredPhrase)))
            {
                sender.sendMessage(ChatColor.RED + "The word " + ChatColor.YELLOW + "'" + filteredPhrase + "'" + ChatColor.RED + " is already being filtered.");
                sender.sendMessage(ChatColor.YELLOW + "To get a list of filtered words run /filter list.");
                return true;
            }
            Filter.filteredWords.add(filteredPhrase);
            sender.sendMessage(ChatColor.GREEN + "Added word " + ChatColor.WHITE + "'" + filteredPhrase + "'" + ChatColor.GREEN + " to the filtered word list.");
        }
        else
        {
            if (!(Filter.filteredWords.contains(filteredPhrase)))
            {
                sender.sendMessage(ChatColor.RED + "The word " + ChatColor.YELLOW + "'" + filteredPhrase + "'" + ChatColor.RED + " is not being filtered.");
                sender.sendMessage(ChatColor.YELLOW + "To get a list of filtered words run /filter list.");
                return true;
            }
            Filter.filteredWords.remove(filteredPhrase);
            sender.sendMessage(ChatColor.GREEN + "Removed word " + ChatColor.WHITE + "'" + filteredPhrase + "'" + ChatColor.GREEN + " from the filtered word list.");
        }
        return true;
    }

}
