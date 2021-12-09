package de.Pol_Bot.Commands;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageReaction;
import net.dv8tion.jda.api.entities.MessageReaction.ReactionEmote;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.RestAction;

public class ElectionCommand extends ListenerAdapter implements ServerCommand
{
	//Soll Wahlen für polsim starten, obwohl das auch schon tot ist
	
	static Message rmessage;
	static Message emessage;
	static User author;
	
	static String[] args;
	static String ID;
	static int argsLength;
	
	@Override
	public void performCommand(Member m, TextChannel channel, Message message) 
	{
		author = message.getAuthor();
		Role role = message.getGuild().getRoleById("859103990226878464");
		
		//Die Wahl kann nur von Server staff und von mir gestartet werden
		if(m.getRoles().contains(role) || m.getUser().getId().equals("371652395861671948"))
		{
		rmessage = message;
		
		args = message.getContentDisplay().substring(13).split(",");
		
		//pol!election
		
		argsLength = args.length;
		int i = 1;
		
		//baut bis zu 10 Feldern und 10 wahlreaktionen 
		if(args.length > 1 && args.length  < 12)
		{
			EmbedBuilder eb = new EmbedBuilder();
		
			eb.setTitle("Election🗳️");
			eb.setColor(Color.green);
			eb.addField("Election Topic: " + args[0],"", false);
			//eb.addBlankField(false);
		
			while(i < argsLength)
			{
				eb.addField(i + "⃣" + args[i], "", false);
				i++;
			}
			
			emessage = channel.sendMessage(eb.build()).complete();
			ID = emessage.getId();
			
			i = 1;
			
			while(i < argsLength)
			{
				emessage.addReaction(i + "⃣").queue(); 
				i++;
			}
			
			emessage.addReaction("🛑").queue();
			
			message.delete().queue();
		}
		
		//wenn man zu viele Optionen für die Wahl hat kommt das
		else if(args.length > 12)
		{
			Message pmessage = channel.sendMessage("Too many arguments!").complete();
			
			message.delete().queue();
			
			pmessage.delete().queueAfter(15, TimeUnit.SECONDS);
		}
		
		}
		
	}

	//dafür da, dass wenn mit dem Stopschild emoji reagiert wird, hört die wahl auf und die
	//Ergebnisse werden in eine Nachricht gepackt und öffentlich losgeschickt
	public void onMessageReactionAdd(MessageReactionAddEvent event)
	{
		if(event.getReactionEmote().isEmoji())
		{
			if(event.getReactionEmote().getEmoji().equals("🛑"))
			{
				System.out.println("debug");
				if(event.getUser().equals(author))
				{

					int i = 1;
					
					List<User> res = new ArrayList<>();;
					
					RestAction<Message> ra = event.getChannel().retrieveMessageById(ID);
					Message tmessage = ra.complete();

					EmbedBuilder eb2 = new EmbedBuilder();
				
					eb2.setTitle("The Election has ended!");
					eb2.setColor(Color.green);
					eb2.addField("Election Topic: " + args[0],"", false);
					//eb.addBlankField(false);
					//System.out.println("debug");
					
				
					while(i < argsLength)
					{
					
						String rs = i + "⃣";
						System.out.println(rs);
					
						//MessageReaction debug = emessage.getReactionById(rs);
						for(MessageReaction reaction : tmessage.getReactions())
						{
							//System.out.println("debugs");
							if(reaction.getReactionEmote().getEmoji().equals(i + "⃣"))
							{
								System.out.println("debugss");
								res.addAll(reaction.retrieveUsers().complete());
								res.remove(emessage.getJDA().getSelfUser());
							
								int p = res.size();
							
								eb2.addField(p + " people voted for " + args[i], "", false);
								
								res.clear();
								
							}
						}
						
						i++;
						
						
					}
					
					Message dmessage = event.getChannel().sendMessage(eb2.build()).complete();
					
					emessage.delete().queue();
					
					//dmessage.delete().queueAfter(15, TimeUnit.SECONDS);
				}
				else
				{
					if(!event.getUser().isBot())
					{
					event.getMember().getUser().openPrivateChannel().complete().sendMessage("You didn't start the elction so you can't end it!").complete();
					}
				}
			
		}
		else
		{
			
		}
	}
}
}
	

