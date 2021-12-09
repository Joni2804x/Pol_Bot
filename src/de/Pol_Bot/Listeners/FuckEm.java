package de.Pol_Bot.Listeners;

import java.util.Random;

import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class FuckEm extends ListenerAdapter
{
	public void onMessageReceived(MessageReceivedEvent event)
	{
		MessageChannel channel = event.getChannel();
		User victim = event.getMember().getUser();
		
		int i = new Random().nextInt(300) + 1;
		
		if(i == 1)
		{
			channel.sendMessage(victim.getName() + " is a horny degenerate!").queue();
		}
		
		if(i == 2)
		{
			channel.sendMessage("I fucked " + victim.getName() + "'s Mom last night!").queue();
		}
		
		if(i == 3)
		{
			int one = new Random().nextInt(255);
			int two = new Random().nextInt(255);
			int three = new Random().nextInt(255);
			int four = new Random().nextInt(255);
			
			String name = victim.getName();
			String ip = one + "." + two + "." + three + "." + four;
			
			channel.sendMessage("Doxx " + name + "\r\n" + ip).queue();
		}
	}
}
