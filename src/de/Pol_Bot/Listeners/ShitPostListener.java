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
	
	static String smessage;
	
	public void onMessageReceived(MessageReceivedEvent event)
	{
		m = new Random().nextInt(10) + 1;
		smessage = "";
		
		User PolBot = event.getJDA().getUserById("848855668538867712");
		if(event.getMessage().getMentionedUsers().contains(PolBot))
		{
			if(!event.getMessage().getContentDisplay().contains("http"))
			{
				MessageChannel channel = event.getChannel();
				
				MessageHistory mHistory = channel.getHistory();
				RestAction<List<Message>> rawMessages = mHistory.getHistoryBefore(channel, event.getMessageId());
				
				List<Message> messages = rawMessages.complete();
				
				ShitPost(messages, channel);
			}
		}
	}

	public void ShitPost(List<Message> messages, MessageChannel channel)
	{	
		while(m > j)
		{
			int a = new Random().nextInt(10) + 1;
			
			int i = new Random().nextInt(messages.size());
			
			Message message = messages.get(i);
		
			String[] args = message.getContentDisplay().split(" ");
		
			for(int t = 0; a > t ; t++)
			{
				if(args.length > t)
				{
					smessage = smessage + " " + args[t];
				}
				else
				{
					ShitPost(messages, channel);
				}
			}
			j++;
		}
		
		channel.sendMessage(smessage).complete();
		j = 0;
		
	}
	
}
