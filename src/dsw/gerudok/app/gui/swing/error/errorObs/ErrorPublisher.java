package dsw.gerudok.app.gui.swing.error.errorObs;

import dsw.gerudok.app.gui.swing.error.Error;

public interface ErrorPublisher {
    void addErrorSub(ErrorSubscriber error);
    void notifyError(Error error);
}
