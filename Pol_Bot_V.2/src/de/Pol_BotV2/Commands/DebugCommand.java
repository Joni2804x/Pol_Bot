package de.Pol_BotV2.Commands;

import de.Pol_BotV2.ServerCommand;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

public class DebugCommand implements ServerCommand
{

	@Override
	public void performCommand(Member m, TextChannel channel, Message message) 
	{
		channel.sendMessage("Debug yourself you dumb twat").queue();
	}

}
