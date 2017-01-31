package org.megha.messenger.resources;

import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.megha.messenger.model.Message;
import org.megha.messenger.resources.beans.MessageFilterBean;
import org.megha.messenger.service.MessageService;

@Path("/messages") 
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource {
	
	MessageService messageService = new MessageService();
	
	@GET
	public List<Message> getMessages(@BeanParam MessageFilterBean filterBean){
		
		if (filterBean.getYear() > 0){
			return messageService.getAllMessagesForYear(filterBean.getYear());
		}
		if (filterBean.getStart() >= 0 && filterBean.getSize() >= 0)
		{
			return messageService.getAllMessagesPagenated(filterBean.getStart(), filterBean.getSize());
		}
		return messageService.getAllMessages();
	}

	@Path("/{messageId}") 
	@GET
	public Message getMessage(@PathParam("messageId") long messageId)
	{
		return messageService.getMessage(messageId);
	}
	
	@POST
	
	public Response addMessage(Message message)
	{
		Message newMessage=messageService.addMessage(message);
		return Response.status(Status.CREATED).entity(newMessage).build();
		//return messageService.addMessage(message);
	}
	
	@PUT
	@Path("/{messageId}") 
	public Message updateMessage(@PathParam("messageId") long messageId,Message message)
	{
		message.setId(messageId);
		return messageService.updateMessage(message);
	}
	
	@DELETE
	@Path("/{messageId}") 
	public void removeMessage(@PathParam("messageId") long messageId)
	{
		messageService.removeMessage(messageId);
	}
	
	@Path("/{messageId}/comments") 
	public CommentResource getCommentResource()
	{
		return new CommentResource();
	}
}
