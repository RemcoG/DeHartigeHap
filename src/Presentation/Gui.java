package Presentation;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.event.*;
import javax.swing.JOptionPane;
import BusinessLogic.Manager;
import java.awt.EventQueue;

public class Gui extends JFrame {

    private static final long serialVersionUID = 1L;
    public JPanel contentPane;
    public JTextField textField_1;
    public JSeparator separator;
    public JTextField textField;
    String menucode;
    
    /*
     * Aanmaken van de manager vanuit de Presentationlaag
     * Dit manager object wordt gebruikt om te communiceren 
     * naar de onderliggende entity laag.
     */
    Manager bestellingManager = new Manager();
    
    //GUI ontwerpen
    public Gui() {

       
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 446, 248);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JLabel lblVoerUwMenucodes = new JLabel("Voer uw menucode(s) in gescheiden door een komma:");
        JLabel lblVoerUwTafelnummer = new JLabel("Uw tafelnummer is:");

        textField_1 = new JTextField();
        textField_1.setColumns(10);
        textField_1.setEditable(false);
        separator = new JSeparator();
        textField = new JTextField();
        textField.setColumns(10);
        textField.setEditable(true);

        JButton btnBestel = new JButton("Bestel");
        btnBestel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menucode = textField.getText();
                JFrame Bestelling = new JFrame();
                textField_1.setText(String.valueOf(bestellingManager.HaalTafelnummerOp(menucode)));
                JOptionPane.showMessageDialog(Bestelling, bestellingManager.Bestel(menucode,bestellingManager.HaalTafelnummerOp(menucode)));
                
            }
        });

        /*
         * Layoutmanager voor de opbouw van het scherm
         */
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
                gl_contentPane.createParallelGroup(Alignment.TRAILING)
                .addGroup(gl_contentPane.createSequentialGroup()
                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
                .addComponent(textField, Alignment.LEADING)
                .addComponent(lblVoerUwMenucodes, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE)
                .addComponent(btnBestel, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE))
                .addGap(420))
                .addGroup(gl_contentPane.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblVoerUwTafelnummer)
                .addPreferredGap(ComponentPlacement.RELATED)))
                .addGroup(gl_contentPane.createSequentialGroup()
                .addContainerGap()
                .addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(ComponentPlacement.RELATED)))
                .addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap()));
        gl_contentPane.setVerticalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup()
                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblVoerUwMenucodes, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(ComponentPlacement.UNRELATED)
                .addComponent(lblVoerUwTafelnummer)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(29)
                .addComponent(btnBestel))
                .addGroup(gl_contentPane.createSequentialGroup()
                .addGap(41)
                .addComponent(separator, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(242, Short.MAX_VALUE)));
        contentPane.setLayout(gl_contentPane);

        
        //textField.setText(String.valueOf(menucode));
    }
    
        public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    Gui frame = new Gui();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
