package de.Pol_Bot;

import java.util.concurrent.ConcurrentHashMap;

import de.Pol_Bot.Commands.BanCommand;
import de.Pol_Bot.Commands.ClearCommand;
import de.Pol_Bot.Commands.HelpCommand;
import de.Pol_Bot.Commands.KickCommand;
import de.Pol_Bot.Commands.PingCommand;
import de.Pol_Bot.Commands.ServerCommand;
import de.Pol_Bot.Commands.StaffHelp;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

public class CommandManager
{
	public ConcurrentHashMap<String, ServerCommand> commands;
	
	public CommandManager()
	{
		this.commands = new ConcurrentHashMap<>();
		
		this.commands.put("ping", new PingCommand());
		this.commands.put("help", new HelpCommand());
		this.commands.put("staffhelp", new StaffHelp());
		this.commands.put("ban", new BanCommand());
		this.commands.put("kick", new KickCommand());
		this.commands.put("clear", new ClearCommand());
	}
	
	//Auch hier muss ich mir das Nochmal anschauen, erkl�rung folgt dann
	public boolean perform(String command, Member m, TextChannel channel, Message message)
	{
		ServerCommand cmd;
		if((cmd = this.commands.get(command.toLowerCase())) != null)
		{
			cmd.performCommand(m, channel, message);
			return true;
		}
		
		return false;
	}
}
