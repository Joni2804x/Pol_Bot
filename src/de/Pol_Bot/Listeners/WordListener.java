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
		Member PolBotM = event.getGuild().getMemberById("848855668538867712");
		User PolBot = PolBotM.getUser();
		Member member = event.getMember();
		User user = event.getAuthor();
		TextChannel channel = event.getTextChannel();
		Message message = event.getMessage();
		String m = message.getContentDisplay().toLowerCase();
		Emote emote1 = message.getGuild().getEmoteById("866814975884132372");
		Emote emote2 = message.getGuild().getEmoteById("867230210499280916");

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
		
		//if(event.getMessage().getMentionedUsers().contains(PolBot))
		//{
		//	channel.sendMessage("https://tenor.com/view/ping-who-pinged-me-disturbed-gif-14162073").queue();		
		//}

		
	}

}
