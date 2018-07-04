package schach.client;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrameLogin extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6458368637515229905L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private MainClient main;
	
	/**
	 * Create the frame.
	 */
	
	public FrameLogin(MainClient mainc) {
		this.main = mainc;
		setResizable(false);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setIconImage(Toolkit.getDefaultToolkit().getImage(FrameLogin.class.getResource("/resources/chess-icon.png")));
		setTitle("Schach - Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 241, 238);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblServerIp = new JLabel("Server IP");
		lblServerIp.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		lblServerIp.setBounds(10, 11, 102, 39);
		contentPane.add(lblServerIp);

		JLabel lblPort = new JLabel("Port");
		lblPort.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		lblPort.setBounds(10, 61, 102, 39);
		contentPane.add(lblPort);

		JLabel lblUsername = new JLabel("Session ID");
		lblUsername.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		lblUsername.setBounds(10, 111, 102, 39);
		contentPane.add(lblUsername);

		JTextField label = new JTextField("0.0.0.0");
		label.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		label.setBounds(122, 11, 102, 39);
		contentPane.add(label);

		textField = new JTextField("1005");
		textField.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		textField.setBounds(122, 61, 102, 39);
		contentPane.add(textField);

		textField_1 = new JTextField("1");
		textField_1.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		textField_1.setBounds(122, 111, 102, 39);
		contentPane.add(textField_1);

		JButton lblStart = new JButton("Start");
		lblStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				main.connect(label.getText(), textField.getText(), textField_1.getText());
			}
		});
		lblStart.setHorizontalAlignment(SwingConstants.CENTER);
		lblStart.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		lblStart.setBounds(10, 161, 214, 39);
		contentPane.add(lblStart);
	}
}
