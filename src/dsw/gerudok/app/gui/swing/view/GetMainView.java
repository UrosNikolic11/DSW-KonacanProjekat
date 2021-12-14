package dsw.gerudok.app.gui.swing.view;

import com.sun.tools.javac.Main;

public class GetMainView implements MainView{

    private MainFrame mainFrame;

    public GetMainView() {
        mainFrame = MainFrame.getInstance();
    }

    @Override
    public MainFrame getMainFrame() {
        return mainFrame;
    }
}
