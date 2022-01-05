package de.Pol_BotV2;

import java.util.concurrent.ConcurrentHashMap;

import de.Pol_BotV2.Commands.DebugCommand;
import de.Pol_BotV2.Commands.LockdownCommand;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

public class CommandManager 
{
	public ConcurrentHashMap<String, ServerCommand> commands;
	
	public CommandManager()
	{
		this.commands = new ConcurrentHashMap<>();
		
		commands.put("debug", new DebugCommand());
		commands.put("lockdown", new LockdownCommand());
	}
	
	public void perform(String messageContent, Message message, TextChannel channel, Member member)
	{
		commands.get(messageContent).performCommand(member, channel, message);
	}
}
