package etu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.basic.MultiLineLabel;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.util.value.ValueMap;




public final class GuestBook extends WebPage{
	private static final List<Comment> commentList = Collections.synchronizedList(new ArrayList<Comment>());
	
			public GuestBook(){
		add(new CommentForm("commentForm"));
		add(new PropertyListView<Comment>("comments", commentList) {
		
		public void populateItem(final ListItem<Comment> listItem){
			listItem.add(new Label("date"));
			listItem.add(new MultiLineLabel("text"));
		}
		}).setVersioned(false);
	}
	public final class CommentForm extends Form<ValueMap>{
		public CommentForm(final String id){
			super(id,new CompoundPropertyModel<ValueMap>(new ValueMap()));
	//		setMarkupId("commentForm");
			add(new TextArea<String>("text").setType(String.class));
			add(new TextField<String>("comment").setType(String.class));
		}
		@Override
		public final void onSubmit(){
			ValueMap values = getModelObject(); 
			String commentValue = (String)values.get("comment");
			if (commentValue == null || commentValue.isEmpty()) {
				error("Caught a spammer!!!");
				return;
			}
			Comment comment = new Comment();
			comment.setDate(new Date());
			comment.setText((String)values.get("text"));
			commentList.add(0, comment);
			values.put("text", "");
		}
	}
	public static void clear(){
		commentList.clear();
	}
}
