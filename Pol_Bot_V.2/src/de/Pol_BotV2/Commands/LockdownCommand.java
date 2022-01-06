package de.Pol_BotV2.Commands;

import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import de.Pol_BotV2.Main;
import de.Pol_BotV2.ServerCommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Emote;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.RestAction;

public class LockdownCommand extends ListenerAdapter implements ServerCommand 
{
	//Connection conn = Main.conn;
	static LockdownCommand lc = new LockdownCommand(); 
	//static Guild guild;
	
	@Override
	public void performCommand(Member m, TextChannel channel, Message message) 
	{
		//lc = new LockdownCommand();
		//guild = message.getGuild();
		Role role = m.getGuild().getRoleById(lc.getRole(message.getGuild().getIdLong(), "Staff_role"));
		User me = m.getJDA().getUserById("371652395861671948");
		
		TextChannel an = m.getGuild().getTextChannelById(lc.getAnChannel(message.getGuild().getIdLong(), "announcements"));
	
		
		String reason = message.getContentDisplay().substring(12);
		
		if(m.getRoles().contains(role) || m.getUser().equals(me))
		{
			if(lc.lockdownStatus(message.getGuild().getIdLong()) == false)
			{
				EmbedBuilder eb = new EmbedBuilder();
			
				eb.setTitle("A new Lockdown has been initiated by " + m.getEffectiveName() + "!");
				
				if(!reason.isEmpty())
				{
					eb.addField("", reason, false);
				}
				else
				{
					eb.addField("", "[No reason given]", false);
				}
				
				eb.setColor(Color.red);
				eb.setThumbnail(m.getUser().getAvatarUrl());
			
				Message lmessage = channel.sendMessage(eb.build()).complete();
				lmessage.addReaction("🛑").complete();
				
				an.sendMessage(eb.build()).complete();
				
				lc.insert(message.getGuild().getIdLong(), lmessage.getIdLong(), an.getIdLong() ,m.getUser().getIdLong());
				message.delete().complete();
			}
			else
			{
				channel.sendMessage("This server is currently already in a lockdown!").queue();
			}
		}
		else
		{
			channel.sendMessage("You do not have the required permissions to start a Lockdown!").queue();
		}
	}
	
	
	//Kickt jeden neuen User der joint sofern der Lockdown aktiviert ist
	public void onGuildMemberJoin(GuildMemberJoinEvent event)
	{
		if(lc.lockdownStatus(event.getGuild().getIdLong()) == true)
		{
			Member target = event.getMember();
			
			EmbedBuilder eb2 = new EmbedBuilder();
			
			eb2.setTitle("You have been kicked due to an ongoing Lockdown!");
			eb2.addField("What does this mean?", "Currently there is a problem that has forced us to no accept new members. Try joining again later!", false);
			eb2.setColor(Color.red);
			
			target.getUser().openPrivateChannel().complete().sendMessage(eb2.build()).complete();
		}
	}
	
	
	//Löscht die zum Lockdown gehörenden Nachrichten und Löscht den Lockdown Eintrag in der Datenbank
	public void onMessageReactionAdd(MessageReactionAddEvent event)
	{
		if(lc.lockdownStatus(event.getGuild().getIdLong()) == true)
		{
			String emoji = event.getReactionEmote().getEmoji();
			
			if(emoji.equals("🛑") && !event.getUser().isBot())
			{
				if(event.getMessageIdLong() == lc.endLockdownCheck(event.getGuild().getIdLong()))
				{				
					if(event.getUser().getIdLong() == lc.LockdownAuthor(event.getGuild().getIdLong()))
					{
						lc.deleteLockdown(event.getGuild().getIdLong());
						
						TextChannel an = event.getGuild().getTextChannelById(lc.getAnChannel(event.getGuild().getIdLong(), "announcements"));
					
						RestAction<Message> zMessage = event.getChannel().retrieveMessageById(event.getMessageIdLong());
						Message message = zMessage.complete();
						message.delete().complete();
						
						Message eMessage = event.getChannel().sendMessage("The lockdown has been lifted!").complete();
						
						Message aMessage = an.sendMessage("The lockdown has been lifted!").complete();
						eMessage.delete().completeAfter(15, TimeUnit.SECONDS);
						aMessage.delete().completeAfter(15, TimeUnit.SECONDS);
						
						
					}
				}
			}
		}
	}


	public void insert(long guild, long messageID, long anID ,long authorID)
	{
		String sql = "INSERT INTO activeLockdowns(Guild, Message, AnnouncementID, Author) VALUES(?,?,?,?)";
		
		try
		{
			PreparedStatement p = Main.conn.prepareStatement(sql);
			
			p.setLong(1, guild);
			p.setLong(2, messageID);
			p.setLong(3, anID);
			p.setLong(4, authorID);
			p.executeUpdate();
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	public boolean lockdownStatus(long l)
	{
		String sql = "SELECT EXISTS(SELECT 1 FROM activeLockdowns WHERE Guild = ?)";		
		try
		{
			PreparedStatement p = Main.conn.prepareStatement(sql);
			p.setLong(1, l);
			ResultSet rs = p.executeQuery();
			boolean found = rs.getBoolean(1);
			return found;
			
		}
		catch (SQLException e)
		{
			System.out.println(e.getMessage());	
			return true;
		}
		
	}
	
	public long getRole(long id, String name)
	{
		String sql = "SELECT roleID FROM roles WHERE guildID = ? AND Name = ?";
		
		try
		{
			PreparedStatement p = Main.conn.prepareStatement(sql);
			
			p.setLong(1, id);
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
	
	public long endLockdownCheck(long idLong) 
	{
		String sql = "SELECT Message FROM activeLockdowns WHERE Guild = ?";
		
		try
		{
			PreparedStatement p = Main.conn.prepareStatement(sql);
			
			p.setLong(1, idLong);
			ResultSet rs = p.executeQuery();
			
			return rs.getLong("Message");
		}
		catch(SQLException e)
		{
			System.out.println(e);
			return 0;
		}
	}
	
	public long getAnChannel(long idLong, String name) 
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
		catch(SQLException e)
		{
			System.out.println(e);
			return 0;
		}
	}
	
	public void deleteLockdown(long idLong) 
	{
		String sql = "DELETE FROM activeLockdowns WHERE Guild = ?";
		
		try
		{
			PreparedStatement p = Main.conn.prepareStatement(sql);
			
			p.setLong(1, idLong);
			p.executeUpdate();
		}
		catch (SQLException e)
		{
			System.out.println(e);
		}
	}
	
	public long LockdownAuthor(long idLong) 
	{
		String sql = "SELECT Author FROM activeLockdowns WHERE Guild = ?";
		
		try
		{
			PreparedStatement p = Main.conn.prepareStatement(sql);
			
			p.setLong(1, idLong);
			ResultSet rs = p.executeQuery();
			
			return rs.getLong("Author");
		}
		catch(SQLException e)
		{
			System.out.println(e);
			return 0;
		}
	}
}
