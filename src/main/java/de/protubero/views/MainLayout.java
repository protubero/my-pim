package de.protubero.views;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.RoutePrefix;
import com.vaadin.flow.theme.lumo.LumoUtility;

/**
 * The main view is a top-level placeholder for other views.
 */
@RoutePrefix("page")
public class MainLayout extends AppLayout {

    /**
	 * 
	 */
	private static final long serialVersionUID = -4146960873756980400L;
	
	private H2 viewTitle;
	
//	private PimApplicationModel model;
	
    public MainLayout() {
        setPrimarySection(Section.DRAWER);
        addDrawerContent();
        addHeaderContent();
    }

    
    @Override
    protected void onAttach(AttachEvent attachEvent) {
    }
    
    private void addHeaderContent() {
        DrawerToggle toggle = new DrawerToggle();
        toggle.setAriaLabel("Menu toggle");

        viewTitle = new H2();
        viewTitle.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);
        
        addToNavbar(true, toggle, viewTitle);
    }

    private void addDrawerContent() {
        H1 appName = new H1("MyPim");
        appName.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);
        Header header = new Header(appName);
        
        SideNav nav = new SideNav();
        nav.addItem(new SideNavItem("Home", HomePageView.class));
        nav.addItem(new SideNavItem("Tasks", TaskListPageView.class));
		        
        Scroller scroller = new Scroller(nav);

        addToDrawer(header, scroller, createFooter());
    }


    private Footer createFooter() {
        Footer layout = new Footer();

        return layout;
    }

    @Override
    protected void afterNavigation() {
    	System.out.println("after navigation");
        super.afterNavigation();
        viewTitle.setText(getCurrentPageTitle());
    }

    
    
    private String getCurrentPageTitle() {
    	if (getContent() instanceof PimPageView) {
    		return ((PimPageView) getContent()).pageTitle();
    	} else {
	        PageTitle title = getContent().getClass().getAnnotation(PageTitle.class);
	        return title == null ? "" : title.value();
    	}    
    }
}
