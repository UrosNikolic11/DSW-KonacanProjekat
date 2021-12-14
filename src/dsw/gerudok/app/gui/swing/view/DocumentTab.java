package dsw.gerudok.app.gui.swing.view;

import dsw.gerudok.app.repository.Document;
import dsw.gerudok.app.repository.node.RuNode;

import java.awt.*;

import javax.swing.*;

public class DocumentTab extends JPanel {


    private JPanel topPanel;
    private Document d;
    private String docName;

    public DocumentTab(Document d) {
        this.d=d;
        this.setLayout(new GridLayout(2, 3));
        addChild();
    }

    public void addChild(){

        for(RuNode child: d.getChildren()){
            JPanel panel= new JPanel();
            panel.setBackground(Color.WHITE);
            panel.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
            panel.setSize(20,20);
            JLabel naziv= new JLabel(child.getName(), SwingConstants.CENTER);
            panel.add(naziv);
            this.add(panel);

        }

    }


    public void saveDocumentState() {
        System.out.println("Cuvam sadrzaj dokumenta: " + this.docName);
    }

    public JPanel getTopPanel() {
        return topPanel;
    }

    public void setTopPanel(JPanel topPanel) {
        this.topPanel = topPanel;
    }

    public Document getD() {
        return d;
    }

    public void setD(Document d) {
        this.d = d;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }
}
