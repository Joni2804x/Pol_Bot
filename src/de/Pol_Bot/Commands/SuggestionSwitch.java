package de.Pol_Bot.Commands;

import java.util.concurrent.TimeUnit;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

public class SuggestionSwitch implements ServerCommand
{
	public static boolean suggestions;
	@Override
	public void performCommand(Member m, TextChannel channel, Message message) 
	{
		//Schaltet die Anfragen ein/aus
		
		suggestions = true;
		String myId = "371652395861671948";
		User me = m.getGuild().getJDA().retrieveUserById(myId).complete();
		
		String[] args = message.getContentDisplay().substring(11).split(" ");
		
		if(m.getUser().equals(me))
		{
			if(args.length > 0)
			{
				//an
				if(args[0].equalsIgnoreCase("on"))
				{
					suggestions = true;
					message.addReaction("✅").queue();
					message.delete().queueAfter(15, TimeUnit.SECONDS);
				}
				//aus
				else if(args[0].equalsIgnoreCase("off"))
				{
					suggestions = false;
					message.addReaction("✅").queue();
					message.delete().queueAfter(15, TimeUnit.SECONDS);
				}
			
			}
		}
	}

}
