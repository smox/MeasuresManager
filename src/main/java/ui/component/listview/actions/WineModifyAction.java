package ui.component.listview.actions;

import persistence.model.Wine;

public interface WineModifyAction {
    void modifyWine(Wine wine, String newName);
}
