/*
 * Copyright (c) 2019 CascadeBot. All rights reserved.
 * Licensed under the MIT license.
 */

package org.cascadebot.cascadebot.commands.subcommands.guild;

import net.dv8tion.jda.core.entities.Member;
import org.cascadebot.cascadebot.commandmeta.CommandContext;
import org.cascadebot.cascadebot.commandmeta.ICommandExecutable;
import org.cascadebot.cascadebot.commandmeta.ISubCommand;
import org.cascadebot.cascadebot.messaging.Messaging;
import org.cascadebot.cascadebot.permissions.CascadePermission;

public class GuildLeaveSubCommand implements ISubCommand {

    @Override
    public void onCommand(Member sender, CommandContext context) {
        Messaging.sendSuccessMessage(context.getChannel(), context.i18n("commands.guild.leave.leave_message"))
                .thenAccept(message -> context.getGuild().leave().queue());
    }

    @Override
    public String command() {
        return "leave";
    }

    @Override
    public String parent() {
        return "guild";
    }

    @Override
    public CascadePermission getPermission() {
        return null;
    }

}
