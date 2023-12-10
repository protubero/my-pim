package de.protubero.mdrender;

import java.io.StringWriter;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Map;

import org.commonmark.internal.util.Escaping;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Emphasis;
import com.vaadin.flow.component.html.H6;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.html.ListItem;
import com.vaadin.flow.component.html.OrderedList;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Pre;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.html.UnorderedList;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class VaadinWriter {

    private static final Map<String, String> NO_ATTRIBUTES = Collections.emptyMap();

    private VerticalLayout layout;
    private Deque<HasComponents> componentStack = new LinkedList<>();
    private boolean lastEltWasNewLine = false;

    public VaadinWriter(VerticalLayout layout) {
        if (layout == null) {
            throw new NullPointerException("layout must not be null");
        }
        this.layout = layout;
    }

    public void raw(String s) {
        append(s);
    }

    public void text(String text) {
        append(Escaping.escapeHtml(text));
    }

    public void tag(String name) {
        tag(name, NO_ATTRIBUTES);
    }

    public void tag(String name, Map<String, String> attrs) {
        tag(name, attrs, false);
    }

    public void tag(String name, Map<String, String> attrs, boolean voidElement) {
    	System.out.println("######### " + name + " /" + voidElement);
    	boolean isEndTag = name.startsWith("/");
    	if (isEndTag) {
    		name = name.substring(1);
    		closeTag();
    	} else {
	    	if (name.equals(Tag.P) && attrs.size() == 0 && !voidElement) {
	    		append(new Paragraph());
	    	} else if (name.equals(Tag.EM) && attrs.size() == 0 && !voidElement) {
	    		append(new Emphasis());
	    	} else if (name.equals(Tag.PRE) && attrs.size() == 0 && !voidElement) {
	    		append(new Pre());
	    	} else if ((name.equals(Tag.H1) || 
	    			name.equals(Tag.H2) || 
	    			name.equals(Tag.H3) ||
	    			name.equals(Tag.H4) ||
	    			name.equals(Tag.H5) ||
	    			name.equals(Tag.H6)
	    			) && attrs.size() == 0 && !voidElement) {
	    		append(new H6());
	    	} else if (name.equals(Tag.OL) && attrs.size() == 0 && !voidElement) {
	    		append(new OrderedList());
	    	} else if (name.equals(Tag.UL) && attrs.size() == 0 && !voidElement) {
	    		append(new UnorderedList());
	    	} else if (name.equals(Tag.LI) && attrs.size() == 0 && !voidElement) {
	    		append(new ListItem());
	    	} else if (name.equals(Tag.HR) && voidElement) {
	    		append(new Hr());
	    	} else if (name.equals("code") && attrs.size() == 0 && !voidElement) {
	    		Span span = new Span();
	    		span.setClassName("md-code");
	    		append(span);
	    	} else if (name.equals("blockquote") && attrs.size() == 0 && !voidElement) {
	    		Span span = new Span();
	    		span.setClassName("md-blockquote");
	    		append(span);
	    	} else if (name.equals(Tag.STRONG) && attrs.size() == 0 && !voidElement) {
	    		Span span = new Span();
	    		span.setClassName("md-strong");
	    		append(span);
	    	} else {
		    	StringWriter sb = new StringWriter();
		        sb.append("<");
		        sb.append(name);
		        if (attrs != null && !attrs.isEmpty()) {
		            for (Map.Entry<String, String> attrib : attrs.entrySet()) {
		            	sb.append(" ");
		            	sb.append(Escaping.escapeHtml(attrib.getKey()));
		            	sb.append("=\"");
		            	sb.append(Escaping.escapeHtml(attrib.getValue()));
		            	sb.append("\"");
		            }
		        }
		        if (voidElement) {
		        	sb.append(" /");
		        }
		        sb.append(">");
		        
		        text(sb.toString());
	    	}    
    	}
    }

    public void line() {    	
        if (!lastEltWasNewLine) {
            append("\n");
        }
    }

    protected void closeTag() {
    	if (componentStack.isEmpty()) {
    		throw new AssertionError();
    	}
    	HasComponents topComponent = componentStack.pop();
    	if (componentStack.isEmpty()) {
    		layout.add((Component) topComponent);
    	} else {
    		componentStack.peek().add((Component) topComponent);
    	}
    }
    
    protected void append(Component c) {
    	if (c instanceof HasComponents) {
    		componentStack.push((HasComponents) c);
    	} else if (!componentStack.isEmpty()) {
    		componentStack.peek().add(c);
    	} else {
          	layout.add(c);
    	}
    }
    protected void append(String text) {
    	lastEltWasNewLine = text.charAt(text.length() - 1) == '\n';
      	append(new Text(text));
    }
    
}
