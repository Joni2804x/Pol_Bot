package de.Pol_BotV2.Listeners;

import de.Pol_BotV2.CommandManager;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class CommandListener extends ListenerAdapter
{
	CommandManager cmd = new CommandManager();
	public void onMessageReceived(MessageReceivedEvent event)
	{
		Message message = event.getMessage();
		Member member = event.getMember();
		
		if(message.isFromType(ChannelType.TEXT))
		{
		TextChannel channel = event.getTextChannel();
		
			if(message.getContentDisplay().startsWith("pot!"))
			{
				String[] args = message.getContentDisplay().toLowerCase().substring(4).split(" ");
				cmd.perform(args[0], message, channel, member);
			}
		}
		
	}
}
