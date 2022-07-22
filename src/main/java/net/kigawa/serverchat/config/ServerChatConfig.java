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
    private final Configuration configuration;
    private final Map<String, String> channels = new HashMap<>();

    public ServerChatConfig(ServerChat serverChat)
    {
        this.serverChat = serverChat;
        try {
            configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(this.serverChat.getDataFolder(), "config.yml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (var server : this.serverChat.getProxy().getServers().keySet()) {
            var channel = configuration.getString(server);
            if (channel.equals("")) {
                channel = server;
                configuration.set(server, channel);
            }

            channels.put(server, channel);
        }
    }

    public String getChannel(String serverName)
    {
        return channels.get(serverName);
    }

    public void setChannel(String serverName, String channel)
    {
        channels.put(serverName, channel);
        configuration.set(serverName, channel);
    }
}
