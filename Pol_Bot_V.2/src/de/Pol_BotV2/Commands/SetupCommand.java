package de.Pol_BotV2.Commands;

import java.awt.Color;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import de.Pol_BotV2.Main;
import de.Pol_BotV2.ServerCommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

public class SetupCommand implements ServerCommand
{

	@Override
	public void performCommand(Member m, TextChannel channel, Message message) 
	{
		String[] args = message.getContentDisplay().split(" ");
		
		List<String> IDs = new ArrayList<>();;
		IDs.add("server_staff");
		IDs.add("announcements");
		IDs.add("logs");
		IDs.add("polder");
		
		if(args.length == 1)
		{
			EmbedBuilder eb = new EmbedBuilder();
			eb.setTitle("Channels/Roles assigned for this server:");
			eb.setColor(Color.cyan);
			eb.setThumbnail(m.getUser().getAvatarUrl());
			
			for(int i = 0; i < IDs.size(); i++)
			{
				long id = retrieveChannel(message.getGuild().getIdLong(), IDs.get(i));
				
				if(id != 0)
				{
					eb.addField("ID for channel " + IDs.get(i) + " is set to:", Long.toString(id), false);
				}
				if(id == 0)
				{
					eb.addField("ID for channel " + IDs.get(i) + " has not been set on this Server!", "Use [pol!setup channel name channel ID] to set up the channel!", false);
				}
			}
			
			for(int i = 0; i < IDs.size(); i++)
			{
				long id = retrieveRole(message.getGuild().getIdLong(), IDs.get(i));
				
				if(id != 0)
				{
					eb.addField("ID for role " + IDs.get(i) + " is set to:", Long.toString(id), false);
				}
				if(id == 0)
				{
					eb.addField("ID for role " + IDs.get(i) + " has not been set on this Server!", "Use [pol!setup role name role ID] to set up the role!", false);
				}
			}
		}
		else if(args.length == 3)
		{
			
		}
		else
		{
			channel.sendMessage("Wrong amount of arguments supplied!").queue();
		}
	}

	

	public long retrieveChannel(long idLong, String name) 
	{
		String sql = "SELECT ID FROM channel WHERE Guild = ? AND Name = ?";
		
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
		
		return rs.getLong("ID");
		}
		catch (SQLException e)
		{
			System.out.println(e);
			return 0;
		}
	}

}
