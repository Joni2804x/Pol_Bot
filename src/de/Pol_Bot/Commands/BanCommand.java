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

public class BanCommand implements ServerCommand
{

	@Override
	public void performCommand(Member m, TextChannel channel, Message message) 
	{
		//Verbannt einen User von dem Server und schickt eine Nachricht mit Informationen 
		//warum und von wem
		Role role = message.getGuild().getRoleById("859103990226878464");
		Member member = message.getMember();
		
		if(member.getRoles().contains(role))
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
				
				eb.setTitle("You have been Banned from the Political Compass Memes Discord Server!");
				eb.setColor(Color.RED);
				eb.addField("Reason:", String.join(" ", Arrays.copyOfRange(args, 2, args.length - 0)), false);
				
					user.openPrivateChannel().complete().sendMessage(
					eb.build()).complete();
					
				eb2.setTitle("A user has been Banned!");
				eb2.setColor(Color.RED);
				eb2.addField("User Banned: ", sUser, false);
				eb2.addField("Banned by: ", author,false);
				eb2.addField("Reason: ", String.join(" ", Arrays.copyOfRange(args, 2, args.length - 0)), false);
				
				logChannel.sendMessage(eb2.build()).complete();
			}
				
			message.getGuild().ban(target, 0).queueAfter(1, TimeUnit.SECONDS);
			
			//message.getChannel().sendMessage("✅").complete().delete().queueAfter(3, TimeUnit.SECONDS);
			
			message.getChannel().sendMessage("Banned " + user.getName() + ", Reason: " + String.join(" ", Arrays.copyOfRange(args, 2, args.length - 0))).complete().delete().queueAfter(10, TimeUnit.SECONDS);
			
			
			
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
