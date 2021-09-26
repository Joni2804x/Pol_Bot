package de.Pol_Bot.Commands;

import java.util.Scanner;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

public class PingCommand implements ServerCommand
{
	
	@Override
	public void performCommand(Member m, TextChannel channel, Message message)
	{
		//Debug Command
		Member member = message.getMember();
		User user = message.getAuthor();
		
		Message rmessage = channel.sendMessage("Debug yourself you stupid moron").complete();
		
		//rmessage.addReaction("2" + "⃣").queue();
		
		
	}

}
