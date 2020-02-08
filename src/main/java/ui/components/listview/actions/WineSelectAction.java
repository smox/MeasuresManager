package ui.components.listview.actions;

import persistence.model.Entry;
import persistence.model.Wine;

public interface WineSelectAction {
    void selectWine(Wine wine, Entry entry);
}
