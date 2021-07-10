package de.Pol_Bot.Listeners;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import de.Pol_Bot.Commands.FFmodeCommand;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class FFListener extends ListenerAdapter
{
	public void onMessageReceived(MessageReceivedEvent event)
	{	

		if(FFmodeCommand.FFmode == true)
		{		
			TextChannel channel = event.getTextChannel();
			Message message = event.getMessage();
			User author = message.getAuthor();
			
			String oMessage = message.getContentDisplay();
			String sMessage = oMessage.toLowerCase();
			
			
			if(sMessage.equalsIgnoreCase("testword") || sMessage.contains(" testword") || sMessage.contains("testword ") || sMessage.contains(" testword "))
			{
				message.delete().queue();
				Message dMessage = channel.sendMessage("No bad words " + "<@" + author.getId() + ">" + "!").complete();
				dMessage.delete().queueAfter(15, TimeUnit.SECONDS);
			}
		}
		else
		{
			return;
		}
		

	}
}
