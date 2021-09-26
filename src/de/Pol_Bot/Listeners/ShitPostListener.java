package de.Pol_Bot.Listeners;

import java.util.List;
import java.util.Random;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.MessageHistory;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.RestAction;

public class ShitPostListener extends ListenerAdapter
{
	static int m;
	static int j = 0;
	
	//static String smessage;
	
	static MessageChannel channel1;
	
	public void onMessageReceived(MessageReceivedEvent event)
	{
		m = new Random().nextInt(20) + 1;
		//smessage = "";
		
		User PolBot = event.getJDA().getUserById("848855668538867712");
		
		if(event.getMessage().getMentionedUsers().contains(PolBot) && event.getAuthor().isBot() == false)
		{
			
				MessageChannel channel = event.getChannel();
				channel1 = event.getChannel();
				
				MessageHistory mHistory = channel.getHistory();
				RestAction<List<Message>> rawMessages = mHistory.retrievePast(100);
				
				List<Message> messages = rawMessages.complete();
				
				String smessage = " ";
				
				ShitPost(messages, channel, smessage);
			
		}
	}

	public void ShitPost(List<Message> messages, MessageChannel channel, String smessage)
	{	
		
		
		while(m > j)
		{
			int a = new Random().nextInt(10) + 1;
			
			int i = new Random().nextInt(messages.size());
			
			Message message = messages.get(i);
		
			String[] args = message.getContentRaw().split(" ");
			
			int t = new Random().nextInt(args.length);
		
		
			smessage = (smessage + " " + args[t]);
				
			j++;
			
			
		}
		
		//if(m == j)
		//{
			if(!smessage.isEmpty())
			{
				channel.sendMessage(smessage).complete();
				j = 0;
			}
			else
			{
				System.out.println("smessage is empty");
			}
	    //}
		//else
		//{
		//	System.out.println(m + " " + j);
		//}
		
		
		
	}
	
	

	
	
}

