package ui.components.dialogs.actions;

import persistence.model.Entry;

public interface IAddOrEditEntryDialogSuccessAction {
    void onSuccess(Entry entry);
}
