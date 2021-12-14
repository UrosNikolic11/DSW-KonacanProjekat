package dsw.gerudok.app.gui.swing.error;

import dsw.gerudok.app.core.IError;
import dsw.gerudok.app.gui.swing.error.errorObs.ErrorPublisher;
import dsw.gerudok.app.gui.swing.error.errorObs.ErrorSubscriber;

import java.util.ArrayList;
import java.util.List;

public class ErrorImplementation implements IError, ErrorPublisher {

    private List<ErrorSubscriber> subscribers= new ArrayList<>();

    @Override
    public void napraviGresku(ErrorType type) {
        if(type.equals(ErrorType.WORKSPACE_DELETE)){
            notifyError(new Error("Workspace se ne moze brisati",1));
        }else if(type.equals(ErrorType.DELETE_PRAZNO)){
            notifyError(new Error("Neselektovano brisanje",2));
        }else if(type.equals(ErrorType.DODAVANJE_SLOTA)){
            notifyError(new Error("Ne moze se vise dodavati",3));
        }else if(type.equals(ErrorType.COPY_PROJEKAT)){
            notifyError(new Error("Projekat se ne moze kopirati",4));
        }else if(type.equals(ErrorType.COPY_PAGE)){
            notifyError(new Error("Page se ne moze kopirati",5));
        }else if(type.equals(ErrorType.COPY_SLOT)){
            notifyError(new Error("Slot se ne moze kopirati",6));
        }else if(type.equals(ErrorType.COPY_WORKSPACE)){
            notifyError(new Error("Workspace se ne moze kopirati",7));
        }else if(type.equals(ErrorType.NESELEKTOVAN_PAGE)){
            notifyError(new Error("Niste selektovali page",8));
        }else if(type.equals(ErrorType.PRAZAN_RENAME)){
            notifyError(new Error("Ne mozete ostaviti prazan rename!",9));
        }else if(type.equals(ErrorType.PRAZAN_PASTE)){
            notifyError(new Error("Nemate kopiran dokument",10));
        }else if(type.equals(ErrorType.NESELEKTOVAN_SLOT)){
            notifyError(new Error("Nisteselektovali slot",11));
        }else if(type.equals(ErrorType.VISE_SELEKTOVANI_SLOTOVI)){
            notifyError(new Error("Ne smete selektovati vise od jednog slota",12));
        }else if(type.equals(ErrorType.PRAZNI_TEXT_U_SLOTU)){
            notifyError(new Error("Morate popuniti text",13));
        }

    }


    @Override
    public void addErrorSub(ErrorSubscriber error) {
        subscribers.add(error);
    }

    @Override
    public void notifyError(Error error) {
        for(ErrorSubscriber e : subscribers){
            e.updateError(error);
        }

    }
}
