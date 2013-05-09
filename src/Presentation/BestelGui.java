package presentation;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTextArea;
import java.awt.Color;
import logic.Manager;


import java.text.DecimalFormat;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;

public class BestelGui extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextArea textArea;
	private JScrollPane sp;
	// Create the frame
	
	public BestelGui(final Manager manager, final DecimalFormat f) {

		setBackground(Color.LIGHT_GRAY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 333);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent args0) {
				if (textField.getText().toString().length() == 3) {
					textArea.setText("");
					manager.addMenuItem(textField.getText());
					textArea.setText(manager.getArray() + "\nTotaal:      €" + f.format(manager.getTotaalPrijs()));
					textField.setText("");
				}
			}
		});

		textField.setColumns(10);

		textArea = new JTextArea();
		textArea.setEditable(false);
		sp = new JScrollPane(textArea);
		
		JLabel lblScanUwBarcode = new JLabel("Scan uw barcode:");
		
		JLabel lblUwBestelling = new JLabel("Uw bestelling:");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblScanUwBarcode)
						.addComponent(lblUwBestelling)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
						.addComponent(sp, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(151, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblScanUwBarcode)
					.addGap(9)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(69)
					.addComponent(lblUwBestelling)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(sp, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(16, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
