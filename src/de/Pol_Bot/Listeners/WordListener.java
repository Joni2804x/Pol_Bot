package de.Pol_Bot.Listeners;

import java.util.Random;

import net.dv8tion.jda.api.entities.Emote;
import net.dv8tion.jda.api.entities.Invite.Channel;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class WordListener extends ListenerAdapter
{
	public void onMessageReceived(MessageReceivedEvent event)
	{
		Member member = event.getMember();
		User user = event.getAuthor();
		TextChannel channel = event.getTextChannel();
		Message message = event.getMessage();
		String m = message.getContentDisplay().toLowerCase();
		Emote emote1 = message.getGuild().getEmoteById("868489497795498016");
		Emote emote2 = message.getGuild().getEmoteById("868489606612545576");

		//schaut nach verschieden Wörtern und reagiert dementsprechend auf diese
		if(m.contains("nigger"))
		{
			message.addReaction(emote1).queue();
		}
		
		if(m.contains(" mod") || m.contains("mod ") || m.contains(" mod ") || m.equals("mod"))
		{
			message.addReaction("🏳️‍🌈").queue();
		}
		
		if(m.contains(" mods") || m.contains("mods ") || m.contains(" mods ") || m.equals("mods"))
		{
			message.addReaction("🏳️‍🌈").queue();
		}
		
		if(m.contains("sus"))
		{
			message.addReaction(emote2).queue();
		}
		
		if(m.contains("poop"))
		{
			if(!user.isBot())
			{
			event.getChannel().sendMessage("The Poopenfarten").queue();
			}
		}
		
		if(event.getMessage().getContentRaw().contains("<@848855668538867712>"))
		{
			int i = new Random().nextInt(11);
			
			if(i == 1)
			{
				channel.sendMessage("https://tenor.com/view/ping-who-pinged-me-disturbed-gif-14162073").queue();
			}
		}
		
	}

}
