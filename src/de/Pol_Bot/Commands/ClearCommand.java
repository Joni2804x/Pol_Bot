package de.Pol_Bot.Commands;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;

public class ClearCommand implements ServerCommand
{

	@Override
	public void performCommand(Member m, TextChannel channel, Message message)
	{
		Role role = message.getGuild().getRoleById("769768279765286962");
		Member member = message.getMember();
		
		if(member.getRoles().contains(role))
		{
		String[] args =  message.getContentDisplay().split(" ");
		
		try
		{
			int amount = Integer.parseInt(args[1]);
			channel.purgeMessages(get(channel, amount));
			channel.sendMessage(amount + " messages deleted").complete().delete().queueAfter(3, TimeUnit.SECONDS);
			return;
		}
		catch(NumberFormatException e)
		{
			e.printStackTrace();
		}
		
		}
		else
		{
			channel.sendMessage("Insufficient rights to acces this command").queue();
		}
			
	}
	
	public List<Message> get(MessageChannel channel, int amount)
	{
		List<Message> messages = new ArrayList<>();
		int i = amount + 1;
		
		for(Message message : channel.getIterableHistory().cache(false)) 
		{
			if(!message.isPinned()) 
			{
				messages.add(message);				
			}
			if(--i <= 0) break;
		}
		
		return messages;
	}

}
