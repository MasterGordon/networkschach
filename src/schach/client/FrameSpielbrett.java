package schach.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;

public class FrameSpielbrett extends JFrame {

	private JPanel contentPane;
	private JButton[][] buttons;
	private boolean firstClick = false;
	private JButton firstButton = null;
	private String firstCoord = "";
	private boolean currentPlayer = false;
	private JLabel lblBlack;
	private JLabel lblWhite;
	private JLabel timerLeft;
	private JLabel timerTime;
	private int timeLeft;
	private int timeTime;
	Thread threadTimeLeftTimer = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameSpielbrett frame = new FrameSpielbrett();
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
	public FrameSpielbrett() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setTitle("Schach - Ingame");
		setIconImage(Toolkit.getDefaultToolkit().getImage(FrameLogin.class.getResource("/resources/chess-icon.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 710, 532);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblBlack = new JLabel("Black");
		lblBlack.setHorizontalAlignment(SwingConstants.CENTER);
		lblBlack.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		lblBlack.setBounds(532, 10, 152, 55);
		contentPane.add(lblBlack);

		lblWhite = new JLabel("White");
		lblWhite.setHorizontalAlignment(SwingConstants.CENTER);
		lblWhite.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		lblWhite.setBounds(532, 427, 152, 55);
		contentPane.add(lblWhite);

		timerLeft = new JLabel("time left: "+"0:00");
		timerLeft.setHorizontalAlignment(SwingConstants.CENTER);
		timerLeft.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		timerLeft.setBounds(532, 152, 152, 55);
		contentPane.add(timerLeft);

		timerTime = new JLabel("time: 0:00");
		timerTime.setHorizontalAlignment(SwingConstants.CENTER);
		timerTime.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		timerTime.setBounds(532, 234, 152, 55);
		contentPane.add(timerTime);

		buttons = new JButton[8][];
		for (int i = 0; i < 8; i++) {
			buttons[i] = new JButton[8];
			for (int j = 0; j < 8; j++) {
				JButton button = new JButton("");
				button.setBounds(10 + 64 * i, 10 + 64 * j, 64, 64);
				contentPane.add(button);
				button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (!firstClick) {
							((JButton) e.getSource()).setBackground(Color.GREEN);
							firstCoord = getPosOfButton((JButton) e.getSource());
							firstButton = ((JButton) e.getSource());
						} else {
							send(firstCoord, getPosOfButton((JButton) e.getSource()));
						}
						firstClick = !firstClick;
					}
				});
				buttons[i][j] = button;
			}
		}
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					timeTime++;
					if (timeTime % 60 < 10)
						timerTime.setText("time: "+timeTime / 60 + ":0" + timeTime % 60);
					else
						timerTime.setText("time: "+timeTime / 60 + ":" + timeTime % 60);
				}
			}
		}).start();
	}

	public void send(String from, String to) {
		String packet = "m#" + from + "#" + to;
		firstButton.setBackground(null);
		System.out.println(packet);
	}

	public String getPosOfButton(JButton button) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (buttons[i][j].equals(button)) {
					return i + "," + j;
				}
			}
		}
		return "-1,-1";
	}

	// TBHKQHBT
	// PPPPPPPP
	//
	//
	//
	//
	// pppppppp
	// tbhkqhbt

	//
	public void update(String s) {
		String[] split = s.split("#");
		if (split[1].equals("0")) {
			lblBlack.setBackground(Color.GREEN);
			lblWhite.setBackground(null);
		} else {
			lblWhite.setBackground(Color.GREEN);
			lblBlack.setBackground(null);
		}

		if (split[2].length() != 64)
			return;
		int index = 0;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (Character.isLowerCase(s.charAt(index))) {
					buttons[i][j].setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(
							FrameSpielbrett.class.getResource("/resources/white_" + split[2].charAt(index) + ".png"))));
				} else {
					buttons[i][j].setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(
							FrameSpielbrett.class.getResource("/resources/black_" + split[2].charAt(index) + ".png"))));
				}
				index++;
			}
		}
		if (threadTimeLeftTimer != null)
			if (threadTimeLeftTimer.isAlive())
				threadTimeLeftTimer.stop();
		threadTimeLeftTimer = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					timeLeft--;
					if (timeLeft % 60 < 10)
						timerTime.setText("time left: "+timeLeft / 60 + ":0" + timeLeft % 60);
					else
						timerTime.setText("time left: "+timeLeft / 60 + ":" + timeLeft % 60);
				}
			}
		});
		timeLeft = 180;
		threadTimeLeftTimer.start();
	}
}
