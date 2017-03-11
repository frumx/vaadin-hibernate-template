package com.ksmk.front;

import com.ksmk.back.service.Bookmark;
import com.ksmk.back.service.BookmarkDaoImpl;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;

import com.vaadin.navigator.Navigator;
import javax.servlet.annotation.WebServlet;
import java.util.List;

/**
 * Created by mdcow on 02.03.17.
 */
@SuppressWarnings("serial")
@Theme("evidence")
public class MainUI extends UI {
    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = MainUI.class)
    public static class Servlet extends VaadinServlet {

    }

    private final BookmarkDaoImpl service;
    private Bookmark bookmark;
    private Grid grid;
    private TextField webPage;
    private TextField urlString;
    private Button save;
    private Button add;
    private Button delete;

    public MainUI() {
        this.service = new BookmarkDaoImpl();
        this.grid   = new Grid();
        this.webPage = new TextField();
        this.urlString = new TextField();
        this.add    = new Button("add new", e -> addBookmark());
        this.save   = new Button("save", e -> saveBookmark());
        this.delete = new Button("delete", e -> deleteBookmark());
    }

    private void addBookmark() {
        service.create(new Bookmark(webPage.getValue(), urlString.getValue()));
        updateGrid();
    }

    private void saveBookmark() {
        service.update(bookmark);
        updateGrid();
    }

    private void deleteBookmark() {
        service.delete(bookmark);
        updateGrid();
    }

    @Override
    protected void init(VaadinRequest request) {
        updateGrid();
        HorizontalLayout actions = new HorizontalLayout(add, save, delete);
        actions.setWidth("300");
        actions.getComponent(0).setWidth("100");
        actions.getComponent(1).setWidth("100");
        actions.getComponent(2).setWidth("100");

        HorizontalLayout input_fields = new HorizontalLayout(webPage, urlString);
        input_fields.setWidth("600");
        input_fields.getComponent(0).setWidth("300");
        input_fields.getComponent(1).setWidth("300");

        VerticalLayout mainLayout = new VerticalLayout(grid, input_fields, actions );
        mainLayout.setMargin(true);
        setContent(mainLayout);

        grid.setHeight(450, Unit.PIXELS);
        grid.setWidth(600, Unit.PIXELS);
        grid.setColumns("webPage", "urlString");
        grid.getColumn("webPage").setWidth(300);
        grid.getColumn("urlString").setWidth(300);
        grid.addSelectionListener(e -> updateForm());

    }

    private void updateGrid() {
        List<Bookmark> bookmarks = service.getAll();
        grid.setContainerDataSource(new BeanItemContainer<>(Bookmark.class, bookmarks));
        setFormVisible(false);
    }

    private void updateForm() {
        if (grid.getSelectedRows().isEmpty()) {
            setFormVisible(true);
        } else {
            bookmark = (Bookmark) grid.getSelectedRow();
            BeanFieldGroup.bindFieldsUnbuffered(bookmark, this);
            setFormVisible(false);
        }
    }

    private void setFormVisible(boolean visible) {
        webPage.setVisible(visible);
        urlString.setVisible(visible);
    }

}
