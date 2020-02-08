package ui.components.listview.actions;

import persistence.model.Wine;

public interface WineModifyAction {
    boolean modifyWine(Wine wine, String newName);
}
