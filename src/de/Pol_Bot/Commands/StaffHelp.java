package de.Pol_Bot.Commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

public class StaffHelp implements ServerCommand
{

	@Override
	public void performCommand(Member m, TextChannel channel, Message message)
	{
		EmbedBuilder eb = new EmbedBuilder();
		
		eb.setTitle("Staff Commands:");
		
		eb.addField("1. Lockdown:", "", false);
	}

}
