package de.Pol_Bot.Commands;

import java.awt.Color;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

public class StaffHelp implements ServerCommand
{
	
	public static String ID2;
	public static User user2;
	public static Message messagel2;
	
	@Override
	public void performCommand(Member m, TextChannel channel, Message message)
	{
		Role role = message.getGuild().getRoleById("769768279765286962");
		Member member = message.getMember();
		user2 = message.getAuthor();
	
		
		if(member.getRoles().contains(role))
		{
		EmbedBuilder eb = new EmbedBuilder();
		
		eb.setTitle("Staff Commands:");
		eb.setColor(Color.RED);
		
		eb.addField("1. Lockdown:", "Comming soonTM", false);
		eb.addField("2. Kick:", "pol!kick [@User] [Reason], does exactly what it says", false);
		eb.addField("3. Ban:", "pol!ban [@User] [Reason], does exactly what it says", false);
		eb.addField("4. Clear", "pol!clear [amount of messages to be deletetd], deletes the last specified amount of messages", false);
		
		Message rmessage = channel.sendMessage(eb.build()).complete();
		ID2 = rmessage.getId();
		messagel2 = rmessage;
		
		rmessage.addReaction("🗑️").queue();
		}
		else
		{
			channel.sendMessage("Insufficient rights to acces this command").queue();
		}
		
	}

}
