package cliente.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Label;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTextField;

public class TelaIP {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaIP window = new TelaIP();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaIP() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		Label label = new Label("Digite o IP Do Servidor");
		label.setBackground(Color.BLACK);
		label.setAlignment(Label.CENTER);
		label.setForeground(Color.BLACK);
		label.setFont(new Font("Al Nile", Font.PLAIN, 12));
		label.setBounds(115, 10, 163, 34);
		frame.getContentPane().add(label);

		JButton btnNewButton = new JButton("Conectar");
		btnNewButton.setBounds(225, 218, 117, 29);
		frame.getContentPane().add(btnNewButton);

		textField = new JTextField();
		textField.setBounds(148, 111, 240, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
	}
}
