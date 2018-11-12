package com.example.common.ui;

import com.example.common.service.AuthenticationService;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.function.SerializableSupplier;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.server.VaadinService;
import com.vaadin.flow.spring.annotation.UIScope;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

import java.util.Locale;

@UIScope
@Theme(value = Lumo.class, variant = Lumo.DARK)
@HtmlImport("/frontend/styles/shared-styles.html")
@HtmlImport("/frontend/styles/main-layout-styles.html")
@Push
public class MainLayout extends Composite<VerticalLayout> implements RouterLayout {

    private VerticalLayout contentLayout = new VerticalLayout();

    public MainLayout(AuthenticationService authenticationService, UIConfiguration uiConfiguration) {
        Locale locale = new Locale(VaadinService.getCurrentRequest().getLocale().getLanguage());
        UI.getCurrent().setLocale(locale);

        Anchor signOut = new Anchor("/logout", Messages.get("com.example.webapp.signOut"));
        signOut.addClassName("header-signout");

        Header header = new Header(Messages.get("com.example.appName"));

        if (!uiConfiguration.getHeaderComponentSuppliers().isEmpty()) {
            uiConfiguration.getHeaderComponentSuppliers().stream().map(SerializableSupplier::get).forEach(header::add);
        }

        if (authenticationService.isAuthenticated()) {
            header.add(signOut);
        }

        MainMenu mainMenu = new MainMenu();
        uiConfiguration.getMenuOptions().stream().forEach(mainMenu::addOption);

        contentLayout.setPadding(false);
        contentLayout.setSpacing(false);

        HorizontalLayout horizontalLayout = new HorizontalLayout(mainMenu, contentLayout);
        horizontalLayout.setSizeFull();
        horizontalLayout.setPadding(false);
        horizontalLayout.setSpacing(false);

        getContent().add(header, horizontalLayout);
        getContent().setAlignSelf(FlexComponent.Alignment.CENTER, horizontalLayout);
        getContent().setSizeFull();
        getContent().setPadding(false);
        getContent().setSpacing(false);
    }

    @Override
    public void showRouterLayoutContent(HasElement content) {
        contentLayout.removeAll();
        contentLayout.getElement().appendChild(content.getElement());
    }

}
