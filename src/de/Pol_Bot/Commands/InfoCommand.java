package de.Pol_Bot.Commands;

import java.awt.Color;
import java.util.concurrent.TimeUnit;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

public class InfoCommand implements ServerCommand
{

	@Override
	public void performCommand(Member m, TextChannel channel, Message message) 
	{
		//Schickt eine Nachricht mit Informationen über den Bot
		EmbedBuilder eb = new EmbedBuilder();
		Guild guild = message.getGuild();
		
		eb.setTitle("Bot information:");
		eb.setColor(Color.CYAN);
		eb.setThumbnail(guild.getJDA().getSelfUser().getAvatarUrl());
		eb.addField("Bot creator:", "Joni#4291", false);
		eb.addField("Bot version:", "Version 1.3.1", false);
		eb.addField("Last update:", "10.30.2021", false);
		
		Message eMessage = channel.sendMessage(eb.build()).complete();
		eMessage.delete().queueAfter(15, TimeUnit.SECONDS);
		
		message.delete().queue();
	}

}
