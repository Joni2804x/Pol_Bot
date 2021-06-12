package de.Pol_Bot.Commands;

import java.awt.Color;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Activity.Emoji;
import net.dv8tion.jda.api.entities.Emote;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

public class HelpCommand implements ServerCommand
{

	public static Message messagel;
	public static String ID;
	public static Emote emote;
	public static User user;

	@Override
	public void performCommand(Member m, TextChannel channel, Message message) 
	{
		message.delete().queue();
		EmbedBuilder eb = new EmbedBuilder();
		
		String emoji = "825777383914864712";
		
		emote = message.getGuild().getEmoteById("825777383914864712");
		user = message.getAuthor();
		
		
		eb.setTitle("My Commands");
		eb.setColor(Color.GREEN);
		eb.addField("1.","Comming soonTM", false);
		
		eb.addField("Other functions:", "", false);
		eb.addField("A.", "If a message contains the word [Nigger], I react with <:cool:825777383914864712>", false);
		eb.addField("B.", "If a message contains the word [Mod], I react with " + "🏳️‍🌈", false);
		eb.addBlankField(false);
		eb.addField("", "React with [🗑️] to delete this Message", false);
		
		Message rmessage = channel.sendMessage(eb.build()).complete();
		ID = rmessage.getId();
		messagel = rmessage;
		
		rmessage.addReaction("🗑️").queue();
	}

}
