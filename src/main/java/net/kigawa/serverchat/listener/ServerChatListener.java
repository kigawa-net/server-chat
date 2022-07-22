package net.kigawa.serverchat.listener;

import com.github.ucchyocean.lc3.member.ChannelMember;
import net.kigawa.serverchat.ServerChat;
import net.kigawa.serverchat.config.ServerChatConfig;
import net.md_5.bungee.api.event.ServerConnectEvent;
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
    public void onConnect(ServerConnectEvent event)
    {
        var api = plugin.getLunaChatAPI();
        var member = ChannelMember.getChannelMember(event.getPlayer());
        var targetServerChannel = api.getChannel(serverChatConfig.getChannel(event.getTarget().getName()));

        var currentServer = event.getPlayer().getServer();
        if (currentServer == null) {
            targetServerChannel.addMember(member);
            return;
        }

        var currentServerChannel = api.getChannel(serverChatConfig.getChannel(currentServer.getInfo().getName()));
        if (api.getChannelsByPlayer(member.getName()).contains(currentServerChannel)) {
            currentServerChannel.removeMember(member);
            targetServerChannel.addMember(member);
        }
    }
}
