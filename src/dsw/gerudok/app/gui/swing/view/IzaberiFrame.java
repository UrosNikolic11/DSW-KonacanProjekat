package dsw.gerudok.app.gui.swing.view;

import dsw.gerudok.app.gui.swing.controller.TextAction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IzaberiFrame extends JDialog {

    private JButton textBtn;
    private JButton slikaBtn;
    private JLabel textLbl;
    private JPanel panel;
    private JLabel spacing;
    private JLabel spacing1;


    public IzaberiFrame()  {

        super();
        initElements();
        addElements();

    }

    private void initElements() {
        Toolkit kit= Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize(screenWidth / 8, screenHight / 5);
        setLocationRelativeTo(null);
        setTitle("Izaberi ");
        setVisible(true);
        textBtn = new JButton("Text");
        slikaBtn = new JButton("Slika");
        textLbl = new JLabel("Izaberite vrstu sadrzaja:");
        spacing = new JLabel(" ");
        spacing1 = new JLabel(" ");
        textBtn.addActionListener(new DugmeText());
        slikaBtn.addActionListener(new DugmeSlika());
    }

    private class DugmeText implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            TextFrame tf = new TextFrame();

            gasi();
        }
    }

    private class DugmeSlika implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            SlikaFrame sf = new SlikaFrame();
            gasi();
        }
    }
    private void gasi(){
        this.dispose();
    }
    private void addElements(){

        panel= new JPanel();
        panel.add(textLbl);
        panel.add(spacing);
        panel.add(textBtn);
        panel.add(spacing1);
        panel.add(slikaBtn);

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        this.add(panel);

    }

}
