package de.Pol_Bot.Listeners;

import de.Pol_Bot.Commands.HelpCommand;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class ReactionListener extends ListenerAdapter 
{
	public void onMessageReactionAdd(MessageReactionAddEvent event)
	{
		
		if(!event.getUser().isBot())
		{
			String emoji = event.getReactionEmote().getEmoji();
			Message message = HelpCommand.messagel;
			String ID = HelpCommand.ID;
			User user = HelpCommand.user;
			
			if(event.getMessageId().equals(ID))
			{
				if(emoji.equals("🗑️") && event.getUser().equals(user))
				{
					message.delete().queue();
				}
			}
		}
	}
}
