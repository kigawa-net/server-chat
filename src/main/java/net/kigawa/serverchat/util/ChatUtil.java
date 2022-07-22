package net.kigawa.serverchat.util;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;

public class ChatUtil
{
    public static BaseComponent[] createErrorMessage(String message)
    {
        return new ComponentBuilder("Error: " + message).color(ChatColor.RED).create();
    }
}
