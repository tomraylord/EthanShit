package cloud.timothy.genericplugin.utils;

import cloud.timothy.genericplugin.GenericPluginClass;

import java.util.List;

public class GenericData
{

    public void saveData(String s, List<String> list)
    {
        GenericPluginClass.getPlugin().getConfig().set(s, list);
        GenericPluginClass.getPlugin().saveConfig();
    }

    public List<String> loadData(String s)
    {
        return GenericPluginClass.getPlugin().getConfig().getStringList(s);
    }

}
