package dsw.gerudok.app.gui.swing.view;

import javax.swing.*;
import java.awt.*;

public class AboutFrame extends JDialog {

    private JLabel ime1Label;
    private JLabel ime2Label;
    private JPanel panel;
    private JPanel panel1;
    private JPanel panel2;
    private ImageIcon slika1;
    private ImageIcon slika2;
    private JLabel slikaLabel1;
    private JLabel slikaLabel2;

    public AboutFrame()  {

        super();
        initElements();
        addElements();

    }

    private void initElements(){

        Toolkit kit= Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize(screenWidth / 2, screenHight / 2);
        setLocationRelativeTo(null);
        setTitle("About");
        setVisible(true);
        ime1Label= new JLabel("Nikola Trajkovic 45/19-RN",SwingConstants.CENTER);
        ime2Label= new JLabel("Uros Nikolic 26/19-RN",SwingConstants.CENTER);
        slika1= new ImageIcon(this.getClass().getResource("slike/nikola.png"));
        slika2= new ImageIcon(this.getClass().getResource("slike/uros.png"));
        slikaLabel1 = new JLabel("",SwingConstants.CENTER);
        slikaLabel2 = new JLabel("",SwingConstants.CENTER);
        slikaLabel1.setIcon(slika1);
        slikaLabel2.setIcon(slika2);
        ime1Label.setFont(new Font(ime1Label.getName(), Font.PLAIN, 30));
        ime2Label.setFont(new Font(ime2Label.getName(), Font.PLAIN, 30));

    }

    private void addElements(){

        panel= new JPanel();
        panel1= new JPanel();
        panel2= new JPanel();

        panel1.add(ime1Label);
        panel1.add(slikaLabel1);
        panel2.add(ime2Label);
        panel2.add(slikaLabel2);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(panel1);
        panel.add(panel2);

        this.add(panel);


    }

}
