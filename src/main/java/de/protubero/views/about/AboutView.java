package de.protubero.views.about;

import java.util.ArrayList;
import java.util.List;

import org.commonmark.Extension;
import org.commonmark.ext.autolink.AutolinkExtension;
import org.commonmark.ext.gfm.strikethrough.StrikethroughExtension;
import org.commonmark.ext.gfm.tables.TablesExtension;
import org.commonmark.ext.ins.InsExtension;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;

import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;

import de.protubero.mdrender.VaadinRenderer;
import de.protubero.mdrender.VaadinRenderer.Builder;
import de.protubero.views.MainLayout;

@PageTitle("About")
@Route(value = "about", layout = MainLayout.class)
public class AboutView extends VerticalLayout {

    public AboutView() {
        setSpacing(false);

        
        Parser parser = Parser.builder().build();
        
        
        Image img = new Image("images/empty-plant.png", "placeholder plant");
        img.setWidth("200px");
        add(img);

        H2 header = new H2("This place intentionally left empty");
        header.addClassNames(Margin.Top.XLARGE, Margin.Bottom.MEDIUM);
        add(header);

        VerticalLayout renderedArea = new VerticalLayout(); 
        
        TextArea textArea = new TextArea();
        textArea.setLabel("Comment");
        textArea.setMaxLength(500);
        textArea.setValueChangeMode(ValueChangeMode.EAGER);
        textArea.addValueChangeListener(e -> {
            e.getSource()
                    .setHelperText(e.getValue().length() + "/500");
            Node document = parser.parse(e.getValue());
            Builder builder = VaadinRenderer.builder();
            List<Extension> extensions = new ArrayList<>();
            extensions.add(TablesExtension.create());
            extensions.add(InsExtension.create());
            extensions.add(AutolinkExtension.create());
            extensions.add(StrikethroughExtension.builder().build());
            builder.extensions(extensions);
			VaadinRenderer renderer = builder.build();
            renderedArea.removeAll();
            renderedArea.add(renderer.render(document));
        });
        textArea.setValue("Great job. This is excellent!");
        add(textArea);     
        
        add(renderedArea);
        
        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("text-align", "center");
    }

    
}
