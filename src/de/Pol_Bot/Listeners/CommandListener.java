package de.Pol_Bot.Listeners;

import de.Pol_Bot.Main;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class CommandListener extends ListenerAdapter
{
	
	@Override
	public void onMessageReceived(MessageReceivedEvent event) 
	{
		
		String message = event.getMessage().getContentDisplay();
		
		if(event.isFromType(ChannelType.TEXT)) 
		{
			TextChannel channel = event.getTextChannel();
		
			if(message.startsWith("pol!")) 
			{
				String[] args = message.substring(4).split(" ");

				if(args.length > 0);
				{
					//Ich muss das hier nochmal nachschauen und verstehen, hab ich noch nicht so ganz raus, funktioniert aber fürs erste auch so
						if(!Main.INSTANCE.getCmdMan().perform(args[0], event.getMember(), channel, event.getMessage()))
						{
							channel.sendMessage("Unknown Command").queue();
						}
	
				
				}
			
			}
		}
	}
}
