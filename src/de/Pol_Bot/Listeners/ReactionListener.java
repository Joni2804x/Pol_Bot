package de.Pol_Bot.Listeners;

import de.Pol_Bot.Commands.HelpCommand;
import de.Pol_Bot.Commands.StaffHelp;
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
			Message message2 = StaffHelp.messagel2;
			String ID = HelpCommand.ID;
			String ID2 = StaffHelp.ID2;
			User user = HelpCommand.user;
			User user2 = StaffHelp.user2;
			
			if(event.getMessageId().equals(ID))
			{
				if(emoji.equals("🗑️") && event.getUser().equals(user))
				{
					message.delete().queue();
				}
			}
			else if(event.getMessageId().equals(ID2))
			{
				if(event.getUser().equals(user2))
				 {
					message2.delete().queue();
				 }
			}
		}
	}
}
