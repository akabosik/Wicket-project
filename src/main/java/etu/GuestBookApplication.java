package etu;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;

public class GuestBookApplication extends WebApplication{
	
	@Override
	protected void init() {
		super.init();
		System.out.println(":)");
	}

	@Override
	public Class <? extends Page> getHomePage(){
		return GuestBook.class;
	}
}
