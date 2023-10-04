package com.example.application.views.cadastro;

import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import com.example.application.data.Controller.AtividadeController;
import com.example.application.data.entity.Atividades;

@PageTitle("Cadastro")
@Route(value = "cadastro/:id", layout = MainLayout.class)
@Uses(Icon.class)
public class CadastroView extends Composite<VerticalLayout>  implements BeforeEnterObserver{

    private Atividades atividadeToEdit;
    private String atividadeId;
    private final AtividadeController atividadeController;

    private  TextField textField = new TextField();
    private DatePicker datePicker = new DatePicker();
    private TextArea textArea = new TextArea();

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        atividadeId = event.getRouteParameters().get("id").orElse(null);
        
        if (atividadeId != null ) {
            Long id = Long.parseLong(atividadeId);
            atividadeToEdit = atividadeController.obterAtividade(id).orElse(null);
    
            if(atividadeToEdit != null){

                java.util.Date datautl = atividadeToEdit.getDataAtividade();
                java.sql.Date dataSql = new java.sql.Date(datautl.getTime());
                LocalDate dataSaida = dataSql.toLocalDate();
                textField.setValue(atividadeToEdit.getTituloAtividade());
                datePicker.setValue(dataSaida);
                textArea.setValue(atividadeToEdit.getDescAtividade());
            }
        }
    }
    public CadastroView(AtividadeController atividadeController) {
        this.atividadeController = atividadeController;
        HorizontalLayout layoutRow = new HorizontalLayout();
        VerticalLayout layoutColumn3 = new VerticalLayout();
        VerticalLayout layoutColumn2 = new VerticalLayout();
        HorizontalLayout layoutRow2 = new HorizontalLayout();
        Button buttonPrimary = new Button();
        Button buttonPrimary2 = new Button();
        VerticalLayout layoutColumn4 = new VerticalLayout();
        getContent().setHeightFull();
        getContent().setWidthFull();
        getContent().setFlexGrow(1.0, layoutRow);
        layoutRow.setWidthFull();
        layoutRow.addClassName(Gap.MEDIUM);
        layoutColumn3.setHeightFull();
        layoutColumn3.setWidth(null);
        layoutRow.setFlexGrow(1.0, layoutColumn2);
        layoutColumn2.setHeightFull();
        layoutColumn2.setWidth(null);
        textField.setLabel("Título");
        textField.setWidthFull();
        layoutColumn2.setAlignSelf(FlexComponent.Alignment.CENTER, textField);
        datePicker.setLabel("Data");
        datePicker.setWidthFull();
        layoutColumn2.setAlignSelf(FlexComponent.Alignment.CENTER, datePicker);
        textArea.setLabel("Descrição");
        textArea.setWidthFull();
        layoutRow2.setWidthFull();
        layoutColumn2.setFlexGrow(1.0, layoutRow2);
        layoutRow2.addClassName(Gap.MEDIUM);
        layoutRow2.setAlignItems(Alignment.CENTER);
        layoutRow2.setJustifyContentMode(JustifyContentMode.CENTER);

        buttonPrimary.setText("Salvar");
        layoutRow2.setAlignSelf(FlexComponent.Alignment.START, buttonPrimary);
        buttonPrimary.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        buttonPrimary.addClickListener(event -> {
            Atividades atividade = new Atividades();
            atividade.setTituloAtividade(textField.getValue());
            LocalDate data = datePicker.getValue();
            Date dataDate = Date.from(data.atStartOfDay(ZoneId.systemDefault()).toInstant());
            atividade.setDataAtividade(dataDate);
            atividade.setDescAtividade(textArea.getValue());

            boolean sucesso = false;
            if (atividadeToEdit == null) {
              sucesso =  atividadeController.inserirAtividade(atividade);
            } else {
                atividade.setId(atividadeToEdit.getId());
                sucesso = atividadeController.atualizarAtividade(atividade);
            }

            if (sucesso) {
                Notification.show("Atividade salva com sucesso.");
                UI.getCurrent().navigate("lembretes");
            } else {
                Notification.show("Erro ao salvar a atividade.");
            }
        });

        buttonPrimary2.setText("Cancelar");
        buttonPrimary2.addClickListener(e -> getUI().ifPresent(ui -> ui.navigate("lembretes")));

        layoutRow2.setAlignSelf(FlexComponent.Alignment.START, buttonPrimary2);
        buttonPrimary2.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        layoutColumn4.setHeightFull();
        layoutColumn4.setWidth(null);
        getContent().add(layoutRow);
        layoutRow.add(layoutColumn3);
        layoutRow.add(layoutColumn2);
        layoutColumn2.add(textField);
        layoutColumn2.add(datePicker);
        layoutColumn2.add(textArea);
        layoutColumn2.add(layoutRow2);
        layoutRow2.add(buttonPrimary);
        layoutRow2.add(buttonPrimary2);
        layoutRow.add(layoutColumn4);
    }
}
