package net.kigawa.serverchat;

import com.github.ucchyocean.lc3.LunaChatAPI;
import com.github.ucchyocean.lc3.LunaChatBungee;
import net.kigawa.serverchat.command.ServerChatCommand;
import net.kigawa.serverchat.config.ServerChatConfig;
import net.kigawa.serverchat.database.ServerChatDatabase;
import net.kigawa.serverchat.listener.ServerChatListener;
import net.md_5.bungee.api.plugin.Plugin;

public class ServerChat extends Plugin
{
    private static ServerChat serverChat;
    public final ServerChatDatabase serverChatDatabase = new ServerChatDatabase();
    public final ServerChatConfig serverChatConfig = new ServerChatConfig();
    public final ServerChatListener serverChatListener = new ServerChatListener();
    public final ServerChatCommand serverChatCommand = new ServerChatCommand();
    private LunaChatAPI lunaChatAPI;


    public static ServerChat getServerChat()
    {
        return serverChat;
    }

    public ServerChat()
    {
        serverChat = this;
    }

    @Override
    public void onEnable()
    {
        LunaChatBungee lunaChat = (LunaChatBungee) getProxy().getPluginManager().getPlugin("LunaChat");
        lunaChatAPI = lunaChat.getLunaChatAPI();

        getProxy().getPluginManager().registerListener(this, serverChatListener);
        getProxy().getPluginManager().registerCommand(this, serverChatCommand);
    }

    public LunaChatAPI getLunaChatAPI()
    {
        return lunaChatAPI;
    }
}
