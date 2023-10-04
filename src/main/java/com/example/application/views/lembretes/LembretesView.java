package com.example.application.views.lembretes;

import com.example.application.data.Controller.AtividadeController;
import com.example.application.data.entity.Atividades;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import org.springframework.beans.factory.annotation.Autowired;

@PageTitle("Lembretes")
@Route(value = "lembretes", layout = MainLayout.class)
@Uses(Icon.class)
public class LembretesView extends Composite<VerticalLayout> {

    private final AtividadeController atividadeController;
    private Grid<Atividades> atividadesGrid;

    public LembretesView(@Autowired AtividadeController atividadeController) {
        this.atividadeController = atividadeController;

        HorizontalLayout layoutRow = new HorizontalLayout();
        VerticalLayout layoutColumn3 = new VerticalLayout();
        VerticalLayout layoutColumn2 = new VerticalLayout();
        atividadesGrid = new Grid<>(Atividades.class); // Declare atividadesGrid como um campo de classe

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

        Button buttonPrimary = new Button("Novo");
        buttonPrimary.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonPrimary.addClickListener(e -> getUI().ifPresent(ui -> ui.navigate("cadastro/0")));

        atividadesGrid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);
        atividadesGrid.setColumns("id", "tituloAtividade", "descAtividade", "dataAtividade");

        atividadesGrid.addComponentColumn(atividade -> {
            Button editarButton = new Button("Editar");
            long id = atividade.getId();
            editarButton.addClickListener(e -> getUI().ifPresent(ui -> ui.navigate("cadastro/"+id)));
            return editarButton;
        });

        atividadesGrid.addComponentColumn(atividade -> {
            Button excluirButton = new Button("Excluir");
            excluirButton.addClickListener(e -> excluirAtividade(atividade));
            return excluirButton;
        });

        setGridSampleData(atividadesGrid);

        layoutColumn4.setHeightFull();
        layoutColumn4.setWidth(null);

        getContent().add(layoutRow);
        layoutRow.add(layoutColumn3);
        layoutRow.add(layoutColumn2);
        layoutColumn2.add(buttonPrimary);
        layoutColumn2.add(atividadesGrid);
        layoutRow.add(layoutColumn4);
    }

    private void setGridSampleData(Grid<Atividades> grid) {
        grid.setItems(atividadeController.listarAtividades());
    }

    private void editarAtividade(Atividades atividade) {
        // Implemente a lógica para abrir a página de edição com os detalhes da atividade
    }

    private void excluirAtividade(Atividades atividade) {
        if (atividadeController.deletarAtividade(atividade)) {
            setGridSampleData(atividadesGrid); 
        }
    }
}
