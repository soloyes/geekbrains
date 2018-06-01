package xyz.shuttle.filebox.basis.ui.views;

import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import xyz.shuttle.filebox.basis.model.files.FSServiceImpl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Component
public class FilterComponent extends CustomComponent {

    @Autowired
    private FSServiceImpl fileService;

    private List<TextField> textFields = new ArrayList<>();
    private List<Label> labels = new ArrayList<>();

    private Button addBtn = new Button("Add filter");
    private Button applyBtn = new Button("Apply");

    private Panel panel = new Panel("Filter by name");
    private VerticalLayout panelContent = new VerticalLayout();
    private HorizontalLayout btnLayout = new HorizontalLayout();

    public FilterComponent() {
        addBtn.addClickListener((Button.ClickListener) clickEvent -> {
            HorizontalLayout layout = new HorizontalLayout();
            Button button = new Button("Del");
            TextField textField = new TextField();
            Label label = new Label();

            textFields.add(textField);
            labels.add(label);
            refreshLabels();

            layout.addComponents(label, textField, button);

            button.addClickListener((Button.ClickListener) clickEvent1 -> {
                panelContent.removeComponent(layout);
                textFields.remove(textField);
                labels.remove(label);
                refreshLabels();
                addBtn.focus();
            });
            panelContent.addComponents(layout);
            textField.focus();
        });

        btnLayout.addComponents(addBtn, applyBtn);
        panelContent.addComponents(btnLayout);
        panel.setContent(panelContent);
        setCompositionRoot(panel);
    }

    public void apply(Grid<File> fileGrid) {
        applyBtn.addClickListener((Button.ClickListener) clickEvent -> {
            if (textFields.size() == 0)
                fileGrid.setItems(fileService.getFileList(
                        SecurityContextHolder.getContext().getAuthentication().getName())
                );
            else
                fileGrid.setItems(fileService.filterList(textFields));
        });
    }

    private void refreshLabels() {
        if (labels.size() > 0) {
            labels.forEach(label1 -> label1.setValue("OR"));
            labels.get(0).setValue("");
        }
    }
}
