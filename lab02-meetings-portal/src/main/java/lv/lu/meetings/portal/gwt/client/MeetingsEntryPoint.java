package lv.lu.meetings.portal.gwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;

public class MeetingsEntryPoint implements EntryPoint {
	
	@Override
	public void onModuleLoad() {
		GWT.log("Hi from GWT!");
		
		Button b = new Button("GWT button", new ClickHandler() {
		    public void onClick(ClickEvent event) {
		        Window.alert("Hello, GWT");
		    }
		});
		RootPanel.get().add(b);

	}
}