package dsw.gerudok.app.gui.swing.error.errorObs;

import dsw.gerudok.app.gui.swing.error.Error;

public interface ErrorSubscriber {
    void updateError(Error error);
}
