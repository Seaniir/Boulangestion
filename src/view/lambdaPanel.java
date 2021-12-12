package view;

import controller.PanelsManager;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class lambdaPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public lambdaPanel() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("PanelsManager CHECK");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel.setBounds(62, 39, 313, 148);
		add(lblNewLabel);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				PanelsManager.contentPane.removeAll();
				PanelsManager.contentPane.repaint();
				PanelsManager.contentPane.revalidate();
			}
		});
		btnNewButton.setBounds(328, 22, 89, 23);
		add(btnNewButton);
		setVisible(true);

	}
}
