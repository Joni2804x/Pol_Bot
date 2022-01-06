package de.Pol_BotV2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import javax.security.auth.login.LoginException;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.Session;

import de.Pol_BotV2.Commands.LockdownCommand;
import de.Pol_BotV2.Listeners.CommandListener;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;

public class Main 
{
public static Main INSTANCE;
	
	public static ShardManager shardMan;
	public static Connection conn;

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
		
		DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault("Retardation");
		
		builder.setActivity(Activity.playing("Use pol!Help to see my Commands"));
		builder.setStatus(OnlineStatus.ONLINE);
		builder.setAutoReconnect(true);
		
		builder.enableIntents(GatewayIntent.GUILD_MESSAGE_REACTIONS);
		builder.enableIntents(GatewayIntent.GUILD_MESSAGES);
		builder.enableIntents(GatewayIntent.GUILD_MEMBERS);
		
		builder.addEventListeners(new CommandListener());
		builder.addEventListeners(new LockdownCommand());
		
		//-----------------------------------------------------------------------------------------
		//Stellt eine Verbindung zur Datenbank her
		
		try
		{
			conn = getDatabase();
			
			if(conn != null)
			{
				System.out.println("Database has been retrieved!");
			}
			else
			{
				System.out.println("Could not retrieve Database!");
				shardMan.setStatus(OnlineStatus.OFFLINE);
				shardMan.shutdown();
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
			shardMan.setStatus(OnlineStatus.OFFLINE);
			shardMan.shutdown();
		}
	
			
		//-----------------------------------------------------------------------------------------
		
		shardMan = builder.build();		
		System.out.println("Bot online");
		
		shutdown();
		restart();
	}


	private Connection getDatabase() 
	{	
		try 
		{
			DriverManager.registerDriver(new org.sqlite.JDBC());
			
			String url = "jdbc:sqlite:C:/PolBot/PolBot.db";
			
			Connection conn = DriverManager.getConnection(url);
			return conn;
		} catch (SQLException e) 
		{
			e.printStackTrace();
			return null;
		}
		
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
	
	private static void restart() 
	{
		Scanner scanner = new Scanner(System.in);
		String a = scanner.nextLine();
		
		if(a.equalsIgnoreCase("restart"))
		{
			shardMan.restart();
		}
		scanner.close();
	}
	
	public static boolean lockDownStatus()
	{
		return false;
	}
	

}
