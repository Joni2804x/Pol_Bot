package de.Pol_Bot.Listeners;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.MessageHistory;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.RestAction;

public class ShitPostListener extends ListenerAdapter
{
	//Baut sich aus zufälligen Wörtern der letzten 100 Nachrichten einen Satz und schickt
	//das ganze in den channel in dem der Bot gepingt wurde
	
	static int m;
	static int j = 0;
	
	//static String smessage;
	
	static MessageChannel channel1;
	
	public void onMessageReceived(MessageReceivedEvent event)
	{
		Role role = event.getMessage().getGuild().getRoleById("859103990226878464");
		
		m = new Random().nextInt(20) + 1;
		//smessage = "";
		
		User PolBot = event.getJDA().getUserById("848855668538867712");
		User Otto = event.getJDA().getUserById("609401263540535308");
		
		//horcht ob der Bot gepingt wurde, der ping startet das ganze
		if(event.getAuthor().isBot() == false)
			if(event.getMessage().getMentionedUsers().contains(PolBot) || event.getMessage().getMentionedUsers().contains(Otto))
				{
			
					MessageChannel channel = event.getChannel();
					channel1 = event.getChannel();
				
					MessageHistory mHistory = channel.getHistory();
					RestAction<List<Message>> rawMessages = mHistory.retrievePast(100);
				
					List<Message> UhrMessages = rawMessages.complete();
					
					//int j = 0;
					
					//Das hier ist dafür da alle Videos, Bilder, und gifs rauszuschmeißen weil
					//das den channel sehr schnell zuspammt
					ArrayList<Message> messages = new ArrayList<>(UhrMessages);
					
			          for(Message tmessage : UhrMessages)
	                    {
	                        String f = tmessage.getContentRaw();
	                        
	                        if(f.contains("https")) 
	                        {
	                             messages.remove(tmessage);
	                        }
	                        else if(f.contains("@everyone") || f.contains("@here")) 
	                        {
	                             messages.remove(tmessage);
	                        }                   
	                    }
	                
	                    String smessage = " ";
	                
	                    ShitPost(messages, channel, smessage);
			
				}
	}

	public void ShitPost(List<Message> messages, MessageChannel channel, String smessage)
	{	
		
		//Der loop ist dafür da, dass nur eine bestimmte anzahl an wörter in die 
		//Nachrichten kommt, wenn alle Nachrichten 3 args oder länger sind,
		//dann sind es maximal 40 Wörter
		while(m > j)
		{
			int a = new Random().nextInt(10) + 1;
			
			int i = new Random().nextInt(messages.size());
			
			Message message = messages.get(i);
			
			//System.out.println(message);
		
			String[] args = message.getContentRaw().split(" ");
			
			int t = new Random().nextInt(args.length);
		
			//Wenn die Nachricht länger als 3 Wörter lang ist, dann nimmt der Bot 2 Wörter anstatt
			//nur einem
			if(args.length >= 3)
			{
				//das hier verhindert out of Bounds exceptions
				
				//das hier verhindert größere Zahlen
				if(args.length - 1 == t)
				{
					int m = t - 1;
					smessage = (smessage + " " + args[m] + " " + args[t]);
				}
				//das hier verhindert kleiner Zahlen als 
				else
				{
					int m = t + 1;
					smessage = (smessage + " " + args[t] + " " + args[m]);	
				}
			}
			//ist die Nachricht kleiner kommt das hier
			else if(args.length < 3)
			{
			smessage = (smessage + " " + args[t]);
			}
			
			j++;
			
			
		}
		
		
		//soll leere nachrichten abfangen, auch wenn das eigentlich nicht möglich sein sollte
			if(!smessage.isEmpty())
			{
				channel.sendMessage(smessage).complete();
				j = 0;
			}
			else
			{
				System.out.println("smessage is empty");
			}
		
	}
	
}

