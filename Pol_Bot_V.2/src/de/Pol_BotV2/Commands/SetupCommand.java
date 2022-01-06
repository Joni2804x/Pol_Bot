package de.Pol_BotV2.Commands;

import java.awt.Color;
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
		
		if(args.length == 1)
		{
			EmbedBuilder eb = new EmbedBuilder();
			eb.setTitle("Channels/Roles assigned for this server:");
			eb.setColor(Color.cyan);
			eb.setThumbnail(m.getUser().getAvatarUrl());
			
			for(int i = 0; i < roleIDs.size(); i++)
			{
				long id = retrieveRole(message.getGuild().getIdLong(), roleIDs.get(i));
				
				if(id != 0)
				{
					eb.addField("ID for channel " + roleIDs.get(i) + " is set to:", Long.toString(id), false);
				}
				if(id == 0)
				{
					eb.addField("ID for channel " + roleIDs.get(i) + " has not been set on this Server!", "Use [pol!setup channel name channel ID] to set up the channel!", false);
				}
			}
			
			for(int i = 0; i < channelIDs.size(); i++)
			{
				long id = retrieveChannel(message.getGuild().getIdLong(), channelIDs.get(i));
				
				if(id != 0)
				{
					eb.addField("ID for role " + channelIDs.get(i) + " is set to:", Long.toString(id), false);
				}
				if(id == 0)
				{
					eb.addField("ID for role " + channelIDs.get(i) + " has not been set on this Server!", "Use [pol!setup role name role ID] to set up the role!", false);
				}
			}
		}
		
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
			
			addID(type, name, id, guildID);
			Message cmessage = channel.sendMessage(type + name + " has been set up!").complete();
			cmessage.delete().queueAfter(15, TimeUnit.SECONDS);
		}
		
		else
		{
			channel.sendMessage("Wrong amount of arguments supplied!").queue();
		}
	}

	

	private void addID(String type, String name, long id, long guildID) 
	{
		String sql = "INSERT INTO ?ID(Name, guildID, ?ID) VALUES(?, ?, ?)";
		
		try
		{
			PreparedStatement p = Main.conn.prepareStatement(sql);
			
			p.setString(1, type);
			p.setString(2, type);
			p.setString(3, name);
			p.setLong(4, guildID);
			p.setLong(5, id);
			p.executeUpdate();
		}
		catch(SQLException e)
		{
			System.out.println(e);
		}
	}



	public long retrieveChannel(long idLong, String name) 
	{
		String sql = "SELECT channelID FROM channel WHERE Guild = ? AND Name = ?";
		
		try
		{
			PreparedStatement p = Main.conn.prepareStatement(sql);
			
			p.setLong(1, idLong);
			p.setString(2, name);
			ResultSet rs = p.executeQuery();
			
			return rs.getLong("ID");
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

}
