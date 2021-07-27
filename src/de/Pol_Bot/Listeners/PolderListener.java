package de.Pol_Bot.Listeners;

import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.MessageHistory;
import net.dv8tion.jda.api.entities.MessageHistory.MessageRetrieveAction;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.RestAction;

public class PolderListener extends ListenerAdapter
{
	public void onMessageReceived(MessageReceivedEvent event)
	{
		int i = new Random().nextInt(54);
		
		//if( event.getMessage().getContentDisplay().toLowerCase().equals("poldertest"))
		//{
			
			//Nimmt die letzten 100 Nachrichten aus dem channel #polder
			MessageChannel channel = event.getGuild().getTextChannelById("859104156731047946");
			
			MessageHistory mHistory = channel.getHistory();
			RestAction<List<Message>> rawMessages = mHistory.retrievePast(100);
			
			List<Message> messages = rawMessages.complete();
			
			//System.out.println(messages);
			
			
			//Schickt eine Zufällige Nachricht aus der Liste messages
			if(!messages.isEmpty())
			{
				if(i == 1)
				{
					int r = new Random().nextInt(messages.size());
					event.getChannel().sendMessage(messages.get(r).getContentRaw()).queue();
				}
				else
				{
					System.out.println("i != 1");
				}
			}
			else
			{
				Message eMessage = event.getChannel().sendMessage("<#859104156731047946>" + " is empty!").complete();
				eMessage.delete().queueAfter(15, TimeUnit.SECONDS);
			}
			
			
		//}
	}
}
