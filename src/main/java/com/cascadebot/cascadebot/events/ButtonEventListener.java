/*
 * Copyright (c) 2018 CascadeBot. All rights reserved.
 * Licensed under the MIT license.
 */

package com.cascadebot.cascadebot.events;

import com.cascadebot.cascadebot.objects.GuildData;
import com.cascadebot.cascadebot.utils.buttons.ButtonGroup;
import com.cascadebot.cascadebot.utils.buttons.ButtonsCache;
import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class ButtonEventListener extends ListenerAdapter {
    @Override
    public void onMessageReactionAdd(MessageReactionAddEvent e) {
        if(e.getChannel().getType().equals(ChannelType.TEXT)) {
            TextChannel channel = (TextChannel) e.getChannel();
            GuildData data = GuildData.getGuildData(channel.getGuild().getIdLong());
            ButtonsCache cache = data.getButtonsCache();
            if(cache.containsKey(channel.getIdLong())) {
                if(cache.get(channel.getIdLong()).containsKey(e.getMessageIdLong())) {
                    ButtonGroup group = cache.get(channel.getIdLong()).get(e.getMessageIdLong());
                    e.getChannel().getMessageById(e.getMessageId()).queue(message -> group.hanndleButton(e.getMember(), channel, message, e.getReactionEmote()));
                }
            }
        }
    }
}
