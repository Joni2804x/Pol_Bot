package de.Pol_Bot;

import java.util.Scanner;

import javax.security.auth.login.LoginException;

import de.Pol_Bot.Commands.ElectionCommand;
import de.Pol_Bot.Listeners.CommandListener;
import de.Pol_Bot.Listeners.FFListener;
import de.Pol_Bot.Listeners.LockdownListener;
import de.Pol_Bot.Listeners.PolderListener;
import de.Pol_Bot.Listeners.ReactionListener;
import de.Pol_Bot.Listeners.WordListener;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;

public class Main {
	
	public static Main INSTANCE;
	
	public static ShardManager shardMan;
	private CommandManager cmdMan;

	public static void main(String[] args) 
	{
		try {
			new Main();
		} catch (LoginException | IllegalArgumentException e) {
			
			e.printStackTrace();
		}
	}

	public Main() throws LoginException, IllegalArgumentException
	{
		INSTANCE = this;
		
		DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault("");
		
		builder.setActivity(Activity.playing("Use pol!Help to see my Commands"));
		builder.setStatus(OnlineStatus.ONLINE);
		builder.setAutoReconnect(true);
		
		this.cmdMan = new CommandManager();
		
		builder.enableIntents(GatewayIntent.GUILD_MESSAGE_REACTIONS);
		builder.enableIntents(GatewayIntent.GUILD_MESSAGES);
		builder.enableIntents(GatewayIntent.GUILD_MEMBERS);
		
		builder.addEventListeners(new CommandListener());
		builder.addEventListeners(new WordListener());
		builder.addEventListeners(new ReactionListener());
		builder.addEventListeners(new LockdownListener());
		builder.addEventListeners(new FFListener());
		builder.addEventListeners(new PolderListener());
		builder.addEventListeners(new ElectionCommand());
		
		shardMan = builder.build();		
		System.out.println("Bot online");
		
		shutdown();
	}
	
	private static void shutdown()
	{
		Scanner scanner = new Scanner(System.in);
		String a = scanner.nextLine();
		
		if(a.equalsIgnoreCase("exit"))
		{
			shardMan.setStatus(OnlineStatus.OFFLINE);
			shardMan.shutdown();
			System.out.println("Bot offline");
		}
		scanner.close();
	}

	public CommandManager getCmdMan() 
	{
		return cmdMan;
	}

}
