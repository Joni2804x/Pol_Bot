package de.Pol_Bot.Listeners;

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
			
			//Eine Liste an Wörtern die solange der Family Friendly mode aktiviert ist gelöscht werden soll
			//wenn sie in einer Nachricht gefunden werden
			if(sMessage.contains("nigger"))
			{
				message.delete().queue();
				Message dMessage = channel.sendMessage("No bad words " + "<@" + author.getId() + ">" + "!").complete();
				dMessage.delete().queueAfter(15, TimeUnit.SECONDS);
			}

			if(sMessage.contains("nigga"))
			{
				message.delete().queue();
				Message dMessage = channel.sendMessage("No bad words " + "<@" + author.getId() + ">" + "!").complete();
				dMessage.delete().queueAfter(15, TimeUnit.SECONDS);
			}

			if(sMessage.contains("faggot"))
			{
				message.delete().queue();
				Message dMessage = channel.sendMessage("No bad words " + "<@" + author.getId() + ">" + "!").complete();
				dMessage.delete().queueAfter(15, TimeUnit.SECONDS);
			}

			if(sMessage.contains("kike"))
			{
				message.delete().queue();
				Message dMessage = channel.sendMessage("No bad words " + "<@" + author.getId() + ">" + "!").complete();
				dMessage.delete().queueAfter(15, TimeUnit.SECONDS);
			}

			if(sMessage.contains("sandnigger"))
			{
				message.delete().queue();
				Message dMessage = channel.sendMessage("No bad words " + "<@" + author.getId() + ">" + "!").complete();
				dMessage.delete().queueAfter(15, TimeUnit.SECONDS);
			}
			
			if(sMessage.equalsIgnoreCase("fag") || sMessage.contains(" fag") || sMessage.contains("fag ") || sMessage.contains(" fag "))
			{
				message.delete().queue();
				Message dMessage = channel.sendMessage("No bad words " + "<@" + author.getId() + ">" + "!").complete();
				dMessage.delete().queueAfter(15, TimeUnit.SECONDS);
			}
			
			if(sMessage.equalsIgnoreCase("spic") || sMessage.contains(" spic") || sMessage.contains("spic ") || sMessage.contains(" spic "))
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
