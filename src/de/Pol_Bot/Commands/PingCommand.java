package de.Pol_Bot.Commands;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

public class PingCommand implements ServerCommand
{
	
	@Override
	public void performCommand(Member m, TextChannel channel, Message message)
	{
		Member member = message.getMember();
		User user = message.getAuthor();
		
		channel.sendMessage("Debug yourself you stupid moron").queue();
		//channel.sendMessage(user +" ").queue();
	}

}
