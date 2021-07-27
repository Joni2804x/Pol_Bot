package de.Pol_Bot.Commands;

import java.awt.Color;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

public class StaffHelp implements ServerCommand
{
	
	public static String ID2;
	public static User user2;
	public static Message messagel2;
	
	@Override
	public void performCommand(Member m, TextChannel channel, Message message)
	{
		Role role = message.getGuild().getRoleById("769768279765286962");
		Member member = message.getMember();
		user2 = message.getAuthor();
	
		//Zeigt eine Liste an Commands die von Staff Mitgliedern verwendet werden kann
		if(member.getRoles().contains(role) || m.getUser().getId().equals("371652395861671948"))
		{
		EmbedBuilder eb = new EmbedBuilder();
		
		eb.setTitle("Staff Commands:");
		eb.setColor(Color.CYAN);
		eb.addBlankField(false);
		eb.setThumbnail("https://cdn.discordapp.com/attachments/848801565951131678/863433738858528784/2560px-Gay_Pride_Flag.png");
		
		eb.addField("1. Lockdown:", "pol!lockdown will put the Server into lockdown. Every new User that tries to join the server will be kicked and informed about the lockdown.", false);
		eb.addField("2. Kick:", "pol!kick [@User] [Reason], does exactly what it says", false);
		eb.addField("3. Ban:", "pol!ban [@User] [Reason], does exactly what it says", false);
		eb.addField("4. Clear", "pol!clear [amount of messages to be deletetd], deletes the last specified amount of messages", false);
		eb.addField("5. Family Friendly mode", "pol!ffmodeon will activate the family friendly mode, which will delete all new messages containing no-no words. pol!modeoff will deactivate it.", false);
		eb.addField("6. FF mode status", "pol!ffmodestatus will show if the family friendly mode ist turned on or off", false);
		
		Message rmessage = channel.sendMessage(eb.build()).complete();
		ID2 = rmessage.getId();
		messagel2 = rmessage;
		
		rmessage.addReaction("🗑️").queue();
		}
		else
		{
			channel.sendMessage("Insufficient rights to acces this command").queue();
		}
		
	}

}
