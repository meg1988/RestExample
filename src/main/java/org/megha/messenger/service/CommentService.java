package org.megha.messenger.service;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;

import org.megha.messenger.database.DatabaseClass;
import org.megha.messenger.model.Message;
import org.megha.messenger.model.Comment;

public class CommentService {

	private Map<Long, Message> messages = DatabaseClass.getMessages();

	public List<Comment> getAllComments(long messageId)
	{
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		return new ArrayList<Comment>(comments.values());
	}
	
	public Comment getComment(long messageId, long commentId)
	{
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		return comments.get(commentId);
	}
	
	public Comment addComment(long messageId, Comment comment)
	{
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		comment.setId(comments.size()+1);
		comments.put(comment.getId(), comment);
		System.out.println("Comments for the message Id" + messageId + comments);
;		return comment;

	}
	
	public Comment updateComment(long messageId, Comment comment)
	{
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		if (comment.getId() <=0 || comment.getId() > comments.size())
		{
			return null;
		}
		comments.put(comment.getId(), comment);
		return comment;

	}
	
	public Comment deleteComment(long messageId, long commentId)
	{
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		if (commentId <=0 || commentId > comments.size())
		{
			return null;
		}
		return comments.remove(commentId);
		
	}
	
}
