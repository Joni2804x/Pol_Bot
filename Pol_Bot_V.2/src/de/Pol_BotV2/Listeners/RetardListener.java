package de.Pol_BotV2.Listeners;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import de.Pol_BotV2.Main;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRoleAddEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRoleRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class RetardListener extends ListenerAdapter
{
	static RetardListener rl = new RetardListener();
	
	public void onGuildMemberRoleAdd(GuildMemberRoleAddEvent event)
	{
		long id = event.getGuild().getIdLong();
		String name = event.getRoles().get(0).getName();
		long URID = rl.getRole(id, name);
		Role UR = event.getJDA().getRoleById(URID);
		
		if(event.getRoles().get(0) == UR)
		{
			long userID = event.getMember().getUser().getIdLong();
		}
	}
	
	public void onGuildMemberRoleRemove(GuildMemberRoleRemoveEvent event)
	{
		
	}
	
	public void onGuildMemberJoin(GuildMemberJoinEvent event)
	{
		
	}
	
	public long getRole(long id, String name)
	{
		String sql = "SELECT RoleID FROM roles WHERE GuildID = ? AND Name = ?";
		
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
}
