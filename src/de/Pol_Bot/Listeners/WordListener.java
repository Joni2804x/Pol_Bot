package de.Pol_Bot.Listeners;

import net.dv8tion.jda.api.entities.Emote;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class WordListener extends ListenerAdapter
{
	public void onMessageReceived(MessageReceivedEvent event)
	{
		Message message = event.getMessage();
		String m = message.getContentDisplay();
		Emote emote = message.getGuild().getEmoteById("825777383914864712");

		if(m.contains("nigger") || m.contains("Nigger"))
		{
			message.addReaction(emote).queue();
		}
		
		if(m.contains("mod") || m.contains("Mod") || m.contains("mods") || m.contains("Mods"))
		{
			message.addReaction("🏳️‍🌈").queue();
		}
	}
}
