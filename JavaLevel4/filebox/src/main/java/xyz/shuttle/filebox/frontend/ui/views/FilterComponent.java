package xyz.shuttle.filebox.frontend.ui.views;

import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.shuttle.filebox.frontend.services.SaveFileService;

import java.util.ArrayList;
import java.util.List;

@Component
public class FilterComponent extends CustomComponent {

    @Autowired
    private SaveFileService saveFileService;

    private List<TextField> textFields = new ArrayList<>();
    private List<Label> labels = new ArrayList<>();

    private Button addBtn = new Button("Add filter");

    private Button applyBtn = new Button("Apply");

    private Panel panel = new Panel("Filter by name");
    private VerticalLayout panelContent = new VerticalLayout();
    private HorizontalLayout btnLayout = new HorizontalLayout();

    public FilterComponent() {
        addBtn.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                HorizontalLayout layout = new HorizontalLayout();
                Button button = new Button("Del");
                TextField textField = new TextField();
                Label label = new Label();

                textFields.add(textField);
                labels.add(label);
                refreshLabels();

                layout.addComponents(label, textField, button);

                button.addClickListener(new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent clickEvent) {
                        panelContent.removeComponent(layout);
                        textFields.remove(textField);
                        labels.remove(label);
                        refreshLabels();
                        addBtn.focus();
                    }
                });
                panelContent.addComponents(layout);
                textField.focus();
            }
        });

        btnLayout.addComponents(addBtn, applyBtn);
        panelContent.addComponents(btnLayout);
        panel.setContent(panelContent);
        apply();
        setCompositionRoot(panel);
    }

    private void apply() {
        applyBtn.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                System.out.println(saveFileService);
                textFields.forEach(textField -> System.out.println(textField.getValue()));
            }
        });
    }

    private void refreshLabels() {
        if (labels.size() > 0) {
            labels.forEach(label1 -> label1.setValue("OR"));
            labels.get(0).setValue("");
        }
    }
}
