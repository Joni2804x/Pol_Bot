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
		Emote emote1 = message.getGuild().getEmoteById("825777383914864712");
		Emote emote2 = message.getGuild().getEmoteById("813546229710979113");

		if(m.contains("nigger") || m.contains("Nigger"))
		{
			message.addReaction(emote1).queue();
		}
		
		if(m.contains("mod") || m.contains("Mod") || m.contains("mods") || m.contains("Mods"))
		{
			message.addReaction("🏳️‍🌈").queue();
		}
		
		if(m.contains("sus") || m.contains("Sus") || m.contains("amogus") || m.contains("Amogus"))
		{
			message.addReaction(emote2).queue();
		}
	}
}
