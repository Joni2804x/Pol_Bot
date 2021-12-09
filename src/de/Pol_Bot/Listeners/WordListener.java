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
		Member PolBotM = event.getGuild().retrieveMemberById("848855668538867712").complete();
		//User PolBot = PolBotM.getUser();
		Member member = event.getMember();
		User user = event.getAuthor();
		TextChannel channel = event.getTextChannel();
		Message message = event.getMessage();
		String m = message.getContentDisplay().toLowerCase();
		Emote emote1 = message.getGuild().getEmoteById("866814975884132372");
		Emote emote2 = message.getGuild().getEmoteById("867230210499280916");
		
		int i = new Random().nextInt(50) + 1;
		
		MessageChannel wChannel = event.getGuild().getTextChannelById("859104173411663914");

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
		
		if(m.contains("dead chat") || m.contains("deadchat"))
		{
			if(!user.isBot())
			{
				event.getChannel().sendMessage(">be me\r\n" + 
						">600lb healthy frame, mommy's best boy\r\n" + 
						">just redeemed my GBP for tendies, hunny mussy, and dewey\r\n" + 
						">go on discord to look for staceys to catfish\r\n" + 
						">see favorite autism server, click and go to general for the first time in days\r\n" + 
						">message hasn't been sent for a few minutes\r\n" + 
						">type 'dead chat'\r\n" + 
						">lay back and admire my creativity, wit, and charisma as i shit in my diapy").queue();
			}
		}
		
		if(m.contains("retard") || m.contains("retarded"))
		{
			if(message.getChannel().equals(wChannel))
			{
				if(!user.isBot())
				{
					wChannel.sendMessage("<@&859104002901147669>").queue();
				}
			}
		}
		if(i == 1)
		{
			
			if(!m.contains("@everyone") && !m.contains("@here"))
			{
				if(m.startsWith("im") && !user.isBot())
				{
					String t = m.substring(2);
					message.getChannel().sendMessage("Hi" + t + ", I'm dad!").queue();
				}
				else if(m.startsWith("i'm") || m.startsWith("iam") && !user.isBot())
				{
					String t = m.substring(3);
					message.getChannel().sendMessage("Hi" + t + ", I'm dad!").queue();
				}
				else if(m.startsWith("i am") && !user.isBot())
				{
					String t = m.substring(4);
					message.getChannel().sendMessage("Hi" + t + ", I'm dad!").queue();
				}
			}
		}
		
		//if(event.getMessage().getMentionedUsers().contains(PolBot))
		//{
		//	channel.sendMessage("https://tenor.com/view/ping-who-pinged-me-disturbed-gif-14162073").queue();		
		//}

		
	}

}
