package de.Pol_Bot.Listeners;

import java.awt.Color;
import java.util.List;
import java.util.concurrent.TimeUnit;

import de.Pol_Bot.Commands.LockdownCommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class LockdownListener extends ListenerAdapter
{
	
	public static User user;
	public static Member target;
	
	
	public void onGuildMemberJoin(GuildMemberJoinEvent event)
	{
		target = event.getMember();
		user = event.getUser();
	}
	
	public void onMessageReceived(MessageReceivedEvent event)
	{
		Message message = event.getMessage();
			
		if(message.getChannel().getId().equals("730101040564928573"))
		{
			if(LockdownCommand.inLockdown == true)
			{
				if(!message.getAuthor().isBot())
				{
					EmbedBuilder eb = new EmbedBuilder();
					
					eb.setTitle("You hav been kicked due to an ongoing Lockdown!");
					eb.setColor(Color.red);
					eb.addField("", "We are currently experienceing a problem that has forced us into Lockdown. Please try to join later.", false);
					
					
					user.openPrivateChannel().complete().sendMessage(eb.build()).complete();
					target.kick().queue();
					
					
				}
			}
		}
	}
	
	public void onMessageReactionAdd(MessageReactionAddEvent event)
	{
		if(event.getMessageId().equals(LockdownCommand.ID))
		{
			String emoji = event.getReactionEmote().getEmoji();
			User user = event.getUser();
			User author = LockdownCommand.author;
			
			if(emoji.equals("🛑"))
			{
				if(user.equals(author))
				{
				LockdownCommand.inLockdown = false;
				event.getChannel().sendMessage("The Lockdown has been lifted!").complete().delete().queueAfter(20, TimeUnit.SECONDS);
				
				LockdownCommand.rmessage.delete().queue();
				LockdownCommand.r2message.delete().queue();
				LockdownCommand.r3message.delete().queue();
				}
				else if(user.isBot())
				{
					return;
				}
				else
				{
					event.getChannel().sendMessage("You didnt initiate the Lockdown " + event.getUser().getName() + "!").queue();
				}
			}
		}
	}
}
