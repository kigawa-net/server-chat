package net.kigawa.serverchat.command;

import net.kigawa.serverchat.ServerChat;
import net.kigawa.serverchat.util.ChatUtil;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

import java.util.Collections;
import java.util.LinkedList;

public class ServerChatCommand extends Command
{
    private final ServerChat serverChat;

    public ServerChatCommand(ServerChat serverChat)
    {
        super("serverchat");
        this.serverChat = serverChat;
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings)
    {
        var args = new LinkedList<String>();
        Collections.addAll(args, strings);

        if (args.isEmpty()) {
            commandSender.sendMessage(ChatUtil.createErrorMessageComponents(
                    "use /" + getName() + " <sub command>"
            ));
        }

        commandSender.sendMessage(switch (args.get(0)) {
            default -> ChatUtil.createErrorMessageComponents(args.get(0) + " is not exits");
        });
    }
}
