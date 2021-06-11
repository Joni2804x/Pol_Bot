package de.Pol_Bot.Commands;

import java.awt.Color;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

public class LockdownCommand implements ServerCommand
{

	public static boolean inLockdown;
	public static String ID;
	
	@Override
	public void performCommand(Member m, TextChannel channel, Message message) 
	{
		inLockdown = true;
		
		EmbedBuilder eb = new EmbedBuilder();
		
		eb.setTitle("The server is now in Lockdown!");
		eb.setColor(Color.red);
		eb.addField("", "to end the Lockdown, react to this message with 🔚", false);
		
		Message rmessage = message.getChannel().sendMessage(eb.build()).complete();
		
		rmessage.addReaction("🔚").queue();
		
		ID = rmessage.getId();
		
	}

}
