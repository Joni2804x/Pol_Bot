package de.Pol_Bot.Commands;

import java.awt.Color;
import java.util.concurrent.TimeUnit;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class SuggestionCommand extends ListenerAdapter implements ServerCommand 
{
	static Message sMessage;
	@Override
	public void performCommand(Member m, TextChannel channel, Message message)
	{
		//Regelt ob zurzeit Anfragen angenommen werden
		String suggestion = message.getContentDisplay().substring(11);
		
		//Keine Anfragen
		if(SuggestionSwitch.suggestions == false)
		{
			Message offMessage = channel.sendMessage("I am currently not accepting Suggestions.").complete();
			message.delete().complete();
			offMessage.delete().queueAfter(15, TimeUnit.SECONDS);
		}
		
		//Anfragen
		else if(SuggestionSwitch.suggestions == true)
		{
			String myId = "371652395861671948";
			User me = message.getGuild().getJDA().retrieveUserById(myId).complete();
			
			EmbedBuilder eb = new EmbedBuilder();
			
			eb.setTitle("A new Suggestion has arrived!");
			eb.setColor(Color.MAGENTA);
			
			eb.addField("Suggestion send by: ", m.getEffectiveName(), false);
			eb.addField("The suggestion: ", suggestion, false);
			
			System.out.println(me);
			
			sMessage = me.openPrivateChannel().complete().sendMessage(eb.build()).complete();
			
			sMessage.addReaction("🗑️").complete();
			
			message.delete().complete();
		}
	}
	
	//Löscht die Anfrage in den DMs wenn ich mit dem Mülleimer reagiere
	public void onMessageReactionAdd(MessageReactionAddEvent event)
	{
		//String stringMessage  = event.getMessageId();
		Message message = event.retrieveMessage().complete();
		if(event.isFromType(ChannelType.PRIVATE))
		{
			String emoji = event.getReactionEmote().getEmoji();
		
			if(emoji.equals("🗑️"))
			{
				if(!event.getUser().isBot())
				{
					message.delete().queue();	
				}
			}
		}
	}

}
