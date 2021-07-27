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
	
	//pulic static damit die Nachricht in der Klasse ReactionListener gelöscht werden kann
	public static Message messagel;
	public static String ID;
	public static Emote emote;
	public static User user;

	@Override
	public void performCommand(Member m, TextChannel channel, Message message) 
	{
		//Zeigt eine Liste an Commands die von allen verwendet werden können
		message.delete().queue();
		EmbedBuilder eb = new EmbedBuilder();
		
		String emoji = "825777383914864712";
		
		emote = message.getGuild().getEmoteById("825777383914864712");
		user = message.getAuthor();
		
		
		eb.setTitle("My Commands");
		eb.setColor(Color.GREEN);
		eb.addField("1. Info Command", "pol!info will show some informations about the bot [work in progress]", false);
		eb.addField("2. FF mode status", "pol!ffmodestatus will show if the family friendly mode ist turned on or off", false);
		eb.addBlankField(false);
		
		eb.addField("Other functions:", "", false);
		eb.addField("A.", "If a message contains the word [Nigger], I react with <:cool:825777383914864712>", false);
		eb.addField("B.", "If a message contains the word [Mod], I react with 🏳️‍🌈", false);
		eb.addField("C.", "I will sometimes post random shitposts, brace yourselves", false);
		eb.addBlankField(false);
		eb.addField("", "React with [🗑️] to delete this Message", false);
		
		Message rmessage = channel.sendMessage(eb.build()).complete();
		ID = rmessage.getId();
		messagel = rmessage;
		
		rmessage.addReaction("🗑️").queue();
	}

}
