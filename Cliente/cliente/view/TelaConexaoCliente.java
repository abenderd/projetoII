package cliente.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cliente.conection.ClientConexao;

import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;

public class TelaConexaoCliente extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldIpServidor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaConexaoCliente frame = new TelaConexaoCliente();
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
	public TelaConexaoCliente() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 372, 147);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textFieldIpServidor = new JTextField();
		textFieldIpServidor.setBounds(166, 29, 147, 20);
		contentPane.add(textFieldIpServidor);
		textFieldIpServidor.setColumns(10);

		JLabel lblIpServidor = new JLabel("Informe o IP do Servidor:");
		lblIpServidor.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblIpServidor.setBounds(10, 32, 164, 14);
		contentPane.add(lblIpServidor);

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				try {
					conexao();

					TelaLogin login = new TelaLogin();
					login.show();
					dispose();
				} catch (UnknownHostException erro) {
					// TODO Auto-generated catch block
					erro.printStackTrace();
				} catch (IOException erro) {
					// TODO Auto-generated catch block
					erro.printStackTrace();
				}

			}
		});
		btnSalvar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSalvar.setBounds(178, 74, 89, 23);
		contentPane.add(btnSalvar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCancelar.setBounds(79, 74, 89, 23);
		contentPane.add(btnCancelar);
	}

	public String conexao() throws UnknownHostException, IOException {
		String ipServidor = textFieldIpServidor.getText();

		ClientConexao c = new ClientConexao(ipServidor);
		c.Recebe();

		return ipServidor;
	}
}
