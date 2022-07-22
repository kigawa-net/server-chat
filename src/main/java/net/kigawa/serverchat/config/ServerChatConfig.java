package net.kigawa.serverchat.config;

import net.kigawa.serverchat.ServerChat;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ServerChatConfig
{
    private final ServerChat serverChat;
    private Configuration configuration;
    private File config;
    private final Map<String, String> channels = new HashMap<>();

    public ServerChatConfig(ServerChat serverChat)
    {
        this.serverChat = serverChat;
    }

    public void onEnable() throws IOException
    {
        serverChat.getDataFolder().mkdirs();
        config = new File(serverChat.getDataFolder(), "config.yml");
        config.createNewFile();

        loadConfig();
    }

    public void loadConfig() throws IOException
    {
        configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(config);

        for (var server : serverChat.getProxy().getServers().keySet()) {
            if (!configuration.contains(server)) {
                configuration.set(server, server);
            }
        }
        saveConfig();
    }

    public void saveConfig() throws IOException
    {
        ConfigurationProvider.getProvider(YamlConfiguration.class).save(configuration, config);
    }

    public String getChannel(String serverName)
    {
        return configuration.getString(serverName);
    }

    public void setChannel(String serverName, String channel)
    {
        configuration.set(serverName, channel);
    }

    public Map<String, String> getChannels()
    {
        var map = new HashMap<String, String>();
        for (var key : configuration.getKeys()) {
            map.put(key, getChannel(key));
        }
        return map;
    }
}
