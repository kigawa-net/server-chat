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
        var player = ChannelMember.getChannelMember(event.getPlayer());
        var currentServerChannel = api.getChannel(serverChatConfig.getChannel(event.getPlayer().getServer().getInfo().getName()));
        var targetServerChannel = api.getChannel(serverChatConfig.getChannel(event.getTarget().getName()));

        if (api.getChannelsByPlayer(player.getName()).contains(currentServerChannel)) {
            currentServerChannel.removeMember(player);
            targetServerChannel.addMember(player);
        }
    }
}
