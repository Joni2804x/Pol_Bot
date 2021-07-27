package de.Pol_Bot.Commands;

import java.awt.Color;
import java.util.concurrent.TimeUnit;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;

public class FFmodeCommand implements ServerCommand
{
	
	public static boolean FFmode;
	@Override
	public void performCommand(Member m, TextChannel channel, Message message)
	{
		Role role = message.getGuild().getRoleById("859103990226878464");
		String[] args = message.getContentDisplay().substring(10).split(" ");
		
		//Aktiviert oder Deaktiviert den Family Friendly Mode (Löscht "böse" Wörter mit der Klasse FFListener)
		if(m.getRoles().contains(role) || m.getUser().getId().equals("371652395861671948"))
		{
		
			if(args.length > 0)
			{
				if(args[0].equalsIgnoreCase("on"))
				{
					FFmode = true;
					message.addReaction("✅").queue();
				}
				else if(args[0].equalsIgnoreCase("off"))
				{
					FFmode = false;
					message.addReaction("✅").queue();
				}
			
			}
		}
		
		else
		{
			Message rMessage = channel.sendMessage("Insufficient rights to acces this command!").complete();
			rMessage.delete().queueAfter(15, TimeUnit.SECONDS);
		}
		
		
		//Zeigt ob der Family Friendly mode Aktiv oder Inaktiv ist
		if(args[0].equalsIgnoreCase("status"))
		{
			EmbedBuilder eb = new EmbedBuilder();
			
			String status;
			
			if(FFmode == true)
			{
				status = "turned on.";
				eb.setColor(Color.green);
			}
			else if(FFmode == false)
			{
				status = "turned off.";
				eb.setColor(Color.red);
			}
			else
			{
				status = "broken. [boolean FFmode is not initialized]";
				eb.setColor(Color.orange);
			}
			
			eb.setTitle("Family Friendly mode is currently " + status);
			Message emassge = channel.sendMessage(eb.build()).complete();
			emassge.delete().queueAfter(15, TimeUnit.SECONDS);
		}
		
		message.delete().queue();
	}

}
