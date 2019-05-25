package org.cascadebot.cascadebot.commands.subcommands.guild;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Member;
import org.cascadebot.cascadebot.CascadeBot;
import org.cascadebot.cascadebot.UnicodeConstants;
import org.cascadebot.cascadebot.commandmeta.CommandContext;
import org.cascadebot.cascadebot.commandmeta.ICommandExecutable;
import org.cascadebot.cascadebot.data.managers.GuildDataManager;
import org.cascadebot.cascadebot.data.objects.Flag;
import org.cascadebot.cascadebot.data.objects.GuildData;
import org.cascadebot.cascadebot.messaging.MessagingObjects;
import org.cascadebot.cascadebot.permissions.CascadePermission;
import org.cascadebot.cascadebot.utils.FormatUtils;

import java.util.Arrays;
import java.util.stream.Collectors;

public class GuildInfoSubCommand implements ICommandExecutable {

    @Override
    public void onCommand(Member sender, CommandContext context) {
        GuildData dataForList = context.getData();
        Guild guildForList = context.getGuild();

        if (context.getArgs().length > 0) {
            guildForList = CascadeBot.INS.getShardManager().getGuildById(context.getArg(0));
            dataForList = GuildDataManager.getGuildData(Long.valueOf(context.getArg(0)));
        }

        if (dataForList == null || guildForList == null) {
            context.getTypedMessaging().replyDanger("We couldn't find that guild!");
            return;
        }

        EmbedBuilder builder = MessagingObjects.getClearThreadLocalEmbedBuilder();
        builder.setTitle(guildForList.getName());
        builder.setThumbnail(guildForList.getIconUrl());


        GuildData finalDataForList = dataForList;
        String flags = Arrays.stream(Flag.values()).map(flag -> FormatUtils.formatEnum(flag) + " - " + (finalDataForList.isFlagEnabled(flag) ? UnicodeConstants.TICK : UnicodeConstants.RED_CROSS)).collect(Collectors.joining("\n"));

        builder.addField("Flags", flags, false);
        builder.addField("Join Date", FormatUtils.formatDateTime(context.getSelfMember().getJoinDate()), false);

        context.getTypedMessaging().replyInfo(builder);
    }

    @Override
    public String command() {
        return "info";
    }

    @Override
    public CascadePermission getPermission() {
        return null;
    }

    @Override
    public String description() {
        return null;
    }

}
