package de.Pol_BotV2;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

public interface ServerCommand 
{
	public void performCommand(Member m, TextChannel channel, Message message);
}