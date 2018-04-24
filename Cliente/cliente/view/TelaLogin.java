package cliente.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.JPasswordField;

public class TelaLogin extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldEmail;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaLogin frame = new TelaLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("InternalFrame.inactiveTitleGradient"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				
				String email = textFieldEmail.getText();
				String senha = passwordField.getText();
				
				if (email.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Campos obrigat�rios n�o preenchidos.");
				} else if (senha.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Campos obrigat�rios n�o preenchidos.");
				}
				
				TelaAplicacao login = new TelaAplicacao();
				login.show();
				dispose();
			}
		});
		btnEntrar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnEntrar.setBounds(307, 168, 105, 23);
		contentPane.add(btnEntrar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnCancelar.setBounds(178, 168, 105, 23);
		contentPane.add(btnCancelar);

		JButton btnCadastrar = new JButton("Cadastrar-se");
		btnCadastrar.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				TelaCadastro cadastro = new TelaCadastro();
				cadastro.show();
				dispose();
			}
		});
		btnCadastrar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnCadastrar.setBounds(36, 168, 117, 23);
		contentPane.add(btnCadastrar);

		textFieldEmail = new JTextField();
		textFieldEmail.setBounds(127, 53, 285, 20);
		contentPane.add(textFieldEmail);
		textFieldEmail.setColumns(10);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblEmail.setBounds(36, 56, 46, 14);
		contentPane.add(lblEmail);

		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSenha.setBounds(36, 115, 61, 14);
		contentPane.add(lblSenha);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(127, 114, 146, 20);
		contentPane.add(passwordField);
	}
}
