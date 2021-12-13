package view;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class NouveauClient extends JPanel {

	/**
	 * Create the panel.
	 */
	public NouveauClient() {
		setBackground(Color.PINK);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 1440, 131);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblBack = new JLabel("");
		lblBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		lblBack.setVerticalAlignment(SwingConstants.BOTTOM);
		lblBack.setHorizontalAlignment(SwingConstants.CENTER);
		lblBack.setIcon(new ImageIcon("C:\\Users\\Julien\\Desktop\\projetBoulang\\arrow_left.png"));
		lblBack.setBounds(10, 10, 160, 72);
		panel.add(lblBack);
		
		JLabel lblNewLabel = new JLabel("Retour");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 80, 160, 19);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\Julien\\Desktop\\projetBoulang\\exit.png"));
		lblNewLabel_1.setBounds(1331, 10, 99, 89);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Retour");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(1270, 102, 160, 19);
		panel.add(lblNewLabel_2);
		
	}
}
