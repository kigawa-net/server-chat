package net.kigawa.serverchat.listener;

import com.github.ucchyocean.lc3.member.ChannelMember;
import net.kigawa.serverchat.ServerChat;
import net.kigawa.serverchat.config.ServerChatConfig;
import net.md_5.bungee.api.event.ServerConnectedEvent;
import net.md_5.bungee.api.event.ServerDisconnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class ServerChatListener implements Listener
{
    private final ServerChat plugin;
    private final ServerChatConfig serverChatConfig;

    public ServerChatListener(ServerChat plugin, ServerChatConfig serverChatConfig)
    {
        this.plugin = plugin;
        this.serverChatConfig = serverChatConfig;
    }

    @EventHandler
    public void onConnect(ServerConnectedEvent event)
    {
        var api = plugin.getLunaChatAPI();
        var member = ChannelMember.getChannelMember(event.getPlayer());
        var targetServerChannel = api.getChannel(
                serverChatConfig.getChannel(event.getServer().getInfo().getName())
        );

        targetServerChannel.addMember(member);
    }

    @EventHandler
    public void onLeave(ServerDisconnectEvent event)
    {
        var api = plugin.getLunaChatAPI();
        var currentServer = event.getTarget();
        var member = ChannelMember.getChannelMember(event.getPlayer());
        var currentServerChannel = api.getChannel(serverChatConfig.getChannel(currentServer.getName()));

        currentServerChannel.removeMember(member);
    }
}
