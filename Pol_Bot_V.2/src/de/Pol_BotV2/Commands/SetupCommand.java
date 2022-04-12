package de.Pol_BotV2.Commands;

import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import de.Pol_BotV2.Main;
import de.Pol_BotV2.ServerCommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.exceptions.ParsingException;

public class SetupCommand implements ServerCommand
{
	static String type;
	static String name;
	static long id;

	@Override
	public void performCommand(Member m, TextChannel channel, Message message) 
	{
		String[] args = message.getContentDisplay().split(" ");
		
		List<String> roleIDs = new ArrayList<>();
		roleIDs.add("server_staff");
		roleIDs.add("unfunny_retard");
		
		List<String> channelIDs = new ArrayList<>();
		channelIDs.add("announcements");
		channelIDs.add("logs");
		channelIDs.add("polder");
		
		//---------------------- trennt hier den Befehl in 2 Unterbefehle ---------------------------
		
		//Befehl 1:
		//Sendet eine Liste mit allen Rollen und Channeln und ihren zugehörigen IDs falls diese schon zugewiesen sind. Falls nicht steht der ID Eintrag auf 
		//"nicht zugewiesen"
		
		if(args.length == 1)
		{
			EmbedBuilder eb = new EmbedBuilder();
			eb.setTitle("Channels/Roles assigned for this server:");
			eb.addBlankField(false);
			eb.setColor(Color.cyan);
			eb.setThumbnail(m.getUser().getAvatarUrl());
			
			for(int i = 0; i < roleIDs.size(); i++)
			{
				long id = retrieveRole(message.getGuild().getIdLong(), roleIDs.get(i));
				
				if(id != 0)
				{
					eb.addField("ID for role " + roleIDs.get(i) + " is set to:", Long.toString(id), false);
					eb.addBlankField(false);
				}
				if(id == 0)
				{
					eb.addField("ID for role " + roleIDs.get(i) + " has not been set on this Server!", "Use [pol!setup role name role ID] to set up the role!", false);
					eb.addBlankField(false);
				}
			}
			
			for(int i = 0; i < channelIDs.size(); i++)
			{
				long id = retrieveChannel(message.getGuild().getIdLong(), channelIDs.get(i));
				
				if(id != 0)
				{
					eb.addField("ID for channel " + channelIDs.get(i) + " is set to:", Long.toString(id), false);
					eb.addBlankField(false);
				}
				if(id == 0)
				{
					eb.addField("ID for channel " + channelIDs.get(i) + " has not been set on this Server!", "Use [pol!setup channel name channel ID] to set up the channel!", false);
					eb.addBlankField(false);
				}
			}
			
			eb.addField("", "Use pol!setup [channel/role] [name] [ID] to setup the role/channel", false);
			Message smessage = channel.sendMessage(eb.build()).complete();
			//smessage.delete().queueAfter(20, TimeUnit.SECONDS);
		}
		
		
		//Befehl 2:
		//Wird verwendet um einem Channel oder einer Rolle eine ID zuzuweisen mit dem Befehlaufbau
		//[Befehl] [typ, channel oder Rolle] [Name des Typs wie er in der Liste steht (WICHTIG!)] [ID]"
		
		else if(args.length == 4)
		{

			long guildID = message.getGuild().getIdLong();
			
			try
			{
				type = args[1];
			}
			catch(Exception e)
			{
				System.out.println(e);
				channel.sendMessage(args[1] + " is not a channel type. allowed types: channel, role").queue();
			}
			
			if(roleIDs.contains(args[2]) || channelIDs.contains(args[2]))
			{
				try
				{
					name = args[2];
				}
				catch(Exception e)
				{
					System.out.println(e);
					
				}
			}
			else
			{
				channel.sendMessage(args[2] + " is not in the setup list!").queue();
			}
			
			try
			{
				id = Long.parseLong(args[3]);
			}
			catch(ParsingException e)
			{
				System.out.print(e);
				channel.sendMessage(args[3] + " is not a valid ID!").queue();
			}
			
			if(exist(type, name, id, guildID) == false)
			{
				addID(type, name, id, guildID);
				Message cmessage = channel.sendMessage(type + " " + name + " has been set up!").complete();
				cmessage.delete().queueAfter(15, TimeUnit.SECONDS);
			}
			else
			{
				Message rmessage = channel.sendMessage(type + " " + name + " has already been set up!").complete();
				rmessage.delete().queueAfter(15, TimeUnit.SECONDS);
			}
			
		}
		
		else
		{
			channel.sendMessage("Wrong amount of arguments supplied!").queue();
		}
	}

	

	private void addID(String type, String name, long id, long guildID) 
	{
		System.out.println(type);
		String sql = "INSERT INTO " + type + "(Name, guildID, " + type + "ID) VALUES(?, ?, ?)";
		System.out.println(sql);
		
		try
		{
			PreparedStatement p = Main.conn.prepareStatement(sql);
			
			p.setString(1, name);
			p.setLong(2, guildID);
			p.setLong(3, id);
			p.executeUpdate();
		}
		catch(SQLException e)
		{
			System.out.println(e);
		}
	}



	public long retrieveChannel(long idLong, String name) 
	{
		String sql = "SELECT channelID FROM channel WHERE GuildID = ? AND Name = ?";
		
		try
		{
			PreparedStatement p = Main.conn.prepareStatement(sql);
			
			p.setLong(1, idLong);
			p.setString(2, name);
			ResultSet rs = p.executeQuery();
			
			return rs.getLong("channelID");
		}
		catch (SQLException e)
		{
			System.out.println(e);
			return 0;
		}
	}
	
	public long retrieveRole(long idLong, String name) 
	{
		String sql = "SELECT RoleID FROM role WHERE GuildID = ? AND Name = ?";
		
		try
		{
			PreparedStatement p = Main.conn.prepareStatement(sql);
			
			p.setLong(1, idLong);
			p.setString(2, name);
			ResultSet rs = p.executeQuery();
			
			return rs.getLong("RoleID");
		}
		catch (SQLException e)
		{
			System.out.println(e);
			return 0;
		}
	}
	
	public boolean exist(String type, String name, long id, long guildID)
	{
		String sql = "SELECT EXISTS(SELECT 1 FROM " + type + " WHERE Name = ? AND GuildID = ?)";
		
		try
		{
			PreparedStatement p = Main.conn.prepareStatement(sql);
			
			p.setString(1, name);
			p.setLong(2, guildID);
			ResultSet rs = p.executeQuery();
			
			System.out.println(rs.getBoolean(1));
			return rs.getBoolean(1);
		}
		catch(SQLException e)
		{
			System.out.println(e);
			return true;
		}
	}

}
