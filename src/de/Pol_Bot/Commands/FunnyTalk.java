package de.Pol_Bot.Commands;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;

public class FunnyTalk implements ServerCommand
{

	@Override
	public void performCommand(Member m, TextChannel channel, Message message)
	{
		Role role = message.getGuild().getRoleById("859103990226878464");
		
		if(m.getRoles().contains(role))
		{
			String[] args = message.getContentDisplay().substring(8).split(" ");
			
			TextChannel tChannel = m.getGuild().getTextChannelById(args[1]);
			
			String me = "";
			for(int o = 2; o < args.length; o++)
			{
				me = me + args[o] + " ";
			}
			
			tChannel.sendMessage(me).queue();
			message.delete().queue();
					
		}
	}

}
