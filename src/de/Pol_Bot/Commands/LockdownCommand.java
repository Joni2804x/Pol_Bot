package de.Pol_Bot.Commands;

import java.awt.Color;
import java.util.concurrent.TimeUnit;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

public class LockdownCommand implements ServerCommand
{

	public static boolean inLockdown;
	public static String ID;
	public static Message rmessage;
	public static Message r2message;
	public static Message r3message;
	public static User author;
	

	@Override
	public void performCommand(Member m, TextChannel channel, Message message) 
	{
		
		if(m.hasPermission(Permission.ADMINISTRATOR) || m.getUser().getId().equals("371652395861671948"))
		{
		//Dieser boolean hier Aktiviert den Lockdown
		inLockdown = true;
		
		author = m.getUser();
			
		TextChannel channelA = message.getGuild().getTextChannelById("859104132925095946");
		
		EmbedBuilder eb2 = new EmbedBuilder();
		
		//Schickt eine nachricht in den announcements channel mit dem Hinweis, dass
		//der Server sich jetzt im Lockdown befindet und pingt @everyone
		eb2.setColor(Color.red);
		eb2.setTitle("The Server is currently in Lockdown!");
		eb2.addField("What does this mean?", "Due to a problem that has overwhelmed the staff Team,"
				+ " everyone that tries to join the server is beeing kicked. Please await further information!", false);
		
		
		r2message = channelA.sendMessage(eb2.build()).complete();
		r3message = channelA.sendMessage("@everyone").complete();
		
		EmbedBuilder eb = new EmbedBuilder();
		
		//Schickt eine Nachricht in den Channel in dem der Befehl für den Lockdown eingegeben wurde 
		//mit welcher man den Lockdown durch das reagieren mit dem richtigen Emoji
		eb.setTitle("The server is now in Lockdown!");
		eb.setColor(Color.red);
		eb.addField("", "to end the Lockdown, react to this message with 🛑", false);
		
		rmessage = message.getChannel().sendMessage(eb.build()).complete();
		
		rmessage.addReaction("🛑").queue();
		
		ID = rmessage.getId();

	}
		else
		{
			Message rMessage = channel.sendMessage("Insufficient right's to use this Command!").complete();
			rMessage.delete().queueAfter(15, TimeUnit.SECONDS);
		}
		
	}
}
