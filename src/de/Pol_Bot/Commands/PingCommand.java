package de.Pol_Bot.Commands;

import net.dv8tion.jda.api.entities.Emote;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

public class PingCommand implements ServerCommand
{
	
	@Override
	public void performCommand(Member m, TextChannel channel, Message message)
	{
	 channel.sendMessage("Debug yourself you stupid moron").queue();
	}

}
