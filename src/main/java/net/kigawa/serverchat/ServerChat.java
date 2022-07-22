package net.kigawa.serverchat;

import com.github.ucchyocean.lc3.LunaChatAPI;
import com.github.ucchyocean.lc3.LunaChatBungee;
import net.kigawa.serverchat.command.ServerChatCommand;
import net.kigawa.serverchat.config.ServerChatConfig;
import net.kigawa.serverchat.listener.ServerChatListener;
import net.md_5.bungee.api.plugin.Plugin;

import java.io.IOException;

public class ServerChat extends Plugin
{
    private static ServerChat serverChat;
    public final ServerChatConfig serverChatConfig;
    public final ServerChatListener serverChatListener;
    public final ServerChatCommand serverChatCommand;
    private LunaChatAPI lunaChatAPI;


    public static ServerChat getServerChat()
    {
        return serverChat;
    }

    public ServerChat()
    {
        serverChat = this;
        serverChatConfig = new ServerChatConfig(serverChat);
        serverChatCommand = new ServerChatCommand(serverChat);
        serverChatListener = new ServerChatListener(serverChat, serverChatConfig);
    }

    @Override
    public void onEnable()
    {
        LunaChatBungee lunaChat = (LunaChatBungee) serverChat.getProxy().getPluginManager().getPlugin("LunaChat");
        lunaChatAPI = lunaChat.getLunaChatAPI();

        getProxy().getPluginManager().registerListener(this, serverChatListener);
        getProxy().getPluginManager().registerCommand(this, serverChatCommand);

        try {
            serverChatConfig.onEnable();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (var channel : serverChatConfig.getChannels().values()) {
            if (lunaChatAPI.isExistChannel(channel)) continue;
            lunaChatAPI.createChannel(channel);
        }
    }

    public LunaChatAPI getLunaChatAPI()
    {
        return lunaChatAPI;
    }
}
