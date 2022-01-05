package de.Pol_BotV2.Commands;

import de.Pol_BotV2.ServerCommand;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

public class ClearCommand implements ServerCommand
{

	@Override
	public void performCommand(Member m, TextChannel channel, Message message) 
	{
		String[] args = message.getContentDisplay().split(" ");
		
		try 
		{
			int i = Integer.parseInt(args[1]);
		}
		catch (NumberFormatException e)
		{
			System.out.println(e);
			channel.sendMessage("Number of messages not specified!").queue();
		}
	
		channel.getHistory()
		
		
	}

}
