package de.protubero.views;

import org.springframework.stereotype.Component;

import com.vaadin.flow.spring.annotation.UIScope;

@Component
@UIScope
public class MainViewBus {
    private MainLayout mainView;

    public MainViewBus() {
    }

    public MainLayout getMainView() {
        return this.mainView;
    }
    public void setMainView(MainLayout mainView) {
        this.mainView = mainView;
    }
}