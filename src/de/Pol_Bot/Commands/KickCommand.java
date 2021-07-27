package de.Pol_Bot.Commands;

import java.awt.Color;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

public class KickCommand implements ServerCommand
{

	@Override
	public void performCommand(Member m, TextChannel channel, Message message) 
	{
		//Kickt einen User von dem Server und schickt eine Nachricht mit Informationen 
		//warum und von wem
		Role role = message.getGuild().getRoleById("859103990226878464");
		Member member = message.getMember();
		
		if(member.getRoles().contains(role) || m.getUser().getId().equals("371652395861671948"))
		{
			if(!message.getAuthor().getName().equals("starlord1209"))
			{
			String[] args = message.getContentRaw().split(" ");
			
			if(args.length < 1)
			{
				message.getChannel().sendMessage("You have to specify a User to kick!").queue();
			return; }
			else if(args.length < 2)
			{
				message.getChannel().sendMessage("You have to specify a reason!").queue();
			return; }
			
			Member target = message.getMentionedMembers().get(0);	
			
			User user = target.getUser();
			String sUser = user.getName() + " (" + user.getId() + ")";
			if(!target.getUser().isBot())
			{
				EmbedBuilder eb = new EmbedBuilder();
				EmbedBuilder eb2 = new EmbedBuilder();
				String channel1 = "859104138562633749";
				String author = message.getAuthor().getName() + " (" + message.getAuthor().getId() + ")";
				MessageChannel logChannel = message.getGuild().getTextChannelById(channel1);
				
				eb.setTitle("You have been kicked from the Political Compass Memes Discord Server!");
				eb.setColor(Color.ORANGE);
				eb.addField("Reason:", String.join(" ", Arrays.copyOfRange(args, 2, args.length - 0)), false);
				
					user.openPrivateChannel().complete().sendMessage(eb.build()).complete();
					
				eb2.setTitle("A user has been kicked!");
				eb2.setColor(Color.ORANGE);
				eb2.addField("User kicked", sUser, false);
				eb2.addField("Kicked by: ", author,false);
				eb2.addField("Reason: ", String.join(" ", Arrays.copyOfRange(args, 2, args.length - 0)), false);
				
				logChannel.sendMessage(eb2.build()).complete();
			}
				
			message.getGuild().kick(target).queueAfter(1, TimeUnit.SECONDS);
			
			//message.getChannel().sendMessage("✅").complete().delete().queueAfter(3, TimeUnit.SECONDS);
			
			message.getChannel().sendMessage("Kicked " + user.getName() + ", Reason: " + String.join(" ", Arrays.copyOfRange(args, 2, args.length - 0))).complete().delete().queueAfter(10, TimeUnit.SECONDS);
			
			
			
			}
			else
			{
				channel.sendMessage("No").queue();
			}
		}
		else
		{
			channel.sendMessage("Insufficient rights to acces this command").queue();
		}
		
	}

}
