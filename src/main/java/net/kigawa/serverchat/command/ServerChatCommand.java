package net.kigawa.serverchat.command;

import net.kigawa.serverchat.ServerChat;
import net.kigawa.serverchat.util.ChatUtil;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.plugin.Command;

import java.util.Collections;
import java.util.LinkedList;

public class ServerChatCommand extends Command
{
    private final ServerChat serverChat = ServerChat.getServerChat();

    public ServerChatCommand()
    {
        super("serverchat");
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings)
    {
        var args = new LinkedList<String>();
        Collections.addAll(args, strings);

        if (args.isEmpty()) {
            commandSender.sendMessage(ChatUtil.createErrorMessage(
                    "use /" + getName() + " <sub command>"
            ));
        }

        commandSender.sendMessage(new ComponentBuilder(switch (args.get(0)) {
            case "list" -> list();
            case "set" -> set();
            case "get" -> get();
        }).create());
    }

    private String list()
    {
        return "";
    }

    private String set()
    {
        return "";
    }

    private String get()
    {
        return "";
    }
}
