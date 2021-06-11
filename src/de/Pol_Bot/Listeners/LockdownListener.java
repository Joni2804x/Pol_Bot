package de.Pol_Bot.Listeners;

import java.awt.Color;
import java.util.List;
import java.util.concurrent.TimeUnit;

import de.Pol_Bot.Commands.LockdownCommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class LockdownListener extends ListenerAdapter
{
	public static String user;
	public static User userChannel;
	
	public void onGuildMemberJoin(GuildMemberJoinEvent event)
	{
		user = event.getMember().getAsMention();
		userChannel = event.getUser();
	}
	
	public void onMessageReceived(MessageReceivedEvent event)
	{
		Message message = event.getMessage();
		
		
		if(message.getChannel().getId().equals("730101040564928573"))
		{
			System.out.println("debug1");
			if(LockdownCommand.inLockdown == true)
			{
				System.out.println("debug2");
				if(!message.getAuthor().isBot())
				{
					System.out.println("debug3");
					EmbedBuilder eb = new EmbedBuilder();
					
					eb.setTitle("You hav been kicked due to an ongoing Lockdown!");
					eb.setColor(Color.red);
					eb.addField("", "We are currently experienceing a problem that has forced us into Lockdown. Please try to join later.", false);
					
					
					userChannel.openPrivateChannel().complete().sendMessage(eb.build()).queue();
					message.getGuild().kick(user).queue();
					
					
				}
			}
		}
	}
	
	public void onMessageReactionAdd(MessageReactionAddEvent event)
	{
		if(event.getMessageId().equals(LockdownCommand.ID))
		{
			if(event.getReaction().equals("🔚"))
			{
				LockdownCommand.inLockdown = false;
				event.getChannel().sendMessage("The Lockdown has been lifted!").complete().delete().queueAfter(20, TimeUnit.SECONDS);
			}
		}
	}
}
