import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.plaf.ButtonUI;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import java.awt.SystemColor;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JEditorPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;

public class ATM extends JFrame {

	private JPanel contentPane;
	private JPanel slot;
	
	private String pinEntry = "", amount = ""; 
	private int availableBalance;  
	private Banknote banknote;
	private JTextPane maintext;
	private Card card;
	private JLabel lblAB;
	
	private JButton btn20;
	private JButton btn50;
	private JButton btn70;
	private JButton btn100;
	private JButton btn150;
	private JButton btn200;
	private JButton btnOk;
	private JButton btnCancel;
	private BankDB bankDB; 
	private Account acc; 
	private Boolean userAuthenticated;
	private String name;
	String cbVal;
	Object item;
	
	
	Timer timer = new Timer(5000, (e)->reset());
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ATM frame = new ATM();
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
	public ATM() {
		
		bankDB = new BankDB();
		userAuthenticated = false;
		
		List<String> items = bankDB.accList();
		JComboBox comboBox = new JComboBox(items.toArray());
		
		item = comboBox.getSelectedItem();
		cbVal = item.toString();
		availableBalance = bankDB.getAvailableBalance(Integer.valueOf(cbVal));
		
		name = bankDB.getName(Integer.valueOf(cbVal));
		
		
		setTitle("ATM");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 645, 447);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		banknote = new Banknote();
		banknote.setVisible(false);
		banknote.setBackground(Color.GREEN);
		banknote.setBounds(423, 271, 70, 112);
		contentPane.add(banknote);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		panel.setBackground(Color.CYAN);
		panel.setBounds(185, 11, 300, 175);
		contentPane.add(panel);
		panel.setLayout(null);
		
		maintext = new JTextPane();
		maintext.setFont(new Font("Arial", Font.BOLD, 14));
		maintext.setBackground(Color.CYAN);
		maintext.setEditable(false);
		maintext.setText("Welcome!\r\nPlease enter your card.");
		maintext.setBounds(63, 24, 171, 40);
		panel.add(maintext);
		
		lblAB = new JLabel("Available balance:");
		lblAB.setHorizontalAlignment(SwingConstants.LEFT);
		lblAB.setBounds(66, 99, 94, 14);
		lblAB.setVisible(false);
		panel.add(lblAB);
		
		textField = new JTextField(availableBalance);
		textField.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField.setText(String.valueOf(availableBalance));
		textField.setVisible(false);
		textField.setEnabled(false);
		
		textField.setBounds(153, 96, 59, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		card = new Card();
		card.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				card.moveToTarget(slot.getX()+10, slot.getY()+10);
				maintext.setText("Enter your PIN, and press OK\n");
				pinEntry = "";
				btnCancel.setVisible(true);
				btnOk.setVisible(true);
			}
		});
		card.setBackground(Color.PINK);
		card.setBounds(10, 245, 60, 90);
		contentPane.add(card);
		
		slot = new JPanel();
		slot.setBorder(new LineBorder(new Color(0, 0, 0), 4, true));
		slot.setBounds(423, 224, 81, 15);
		contentPane.add(slot);
		
		JPanel btnPanel = new JPanel();
		btnPanel.setBounds(203, 212, 198, 123);
		contentPane.add(btnPanel);
		btnPanel.setLayout(new GridLayout(4, 3, 0, 0));
		
		JButton btn1 = new JButton("1");
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pinEntry += "1";
				maintext.setText(maintext.getText()+"*");
			}
		});
		btnPanel.add(btn1);
		
		JButton btn2 = new JButton("2");
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pinEntry += "2";
				maintext.setText(maintext.getText()+"*");
			}
		});
		btnPanel.add(btn2);
		
		JButton btn3 = new JButton("3");
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pinEntry += "3";
				maintext.setText(maintext.getText()+"*");
			}
		});
		btnPanel.add(btn3);
		
		JButton btn4 = new JButton("4");
		btn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pinEntry += "4";
				maintext.setText(maintext.getText()+"*");
			}
		});
		btnPanel.add(btn4);
		
		JButton btn5 = new JButton("5");
		btn5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pinEntry += "5";
				maintext.setText(maintext.getText()+"*");
			}
		});
		btnPanel.add(btn5);
		
		JButton btn6 = new JButton("6");
		btn6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pinEntry += "6";
				maintext.setText(maintext.getText()+"*");
			}
		});
		btnPanel.add(btn6);
		
		JButton btn7 = new JButton("7");
		btn7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pinEntry += "7";
				maintext.setText(maintext.getText()+"*");
			}
		});
		btnPanel.add(btn7);
		
		JButton btn8 = new JButton("8");
		btn8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pinEntry += "8";
				maintext.setText(maintext.getText()+"*");
			}
		});
		btnPanel.add(btn8);
		
		JButton btn9 = new JButton("9");
		btn9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pinEntry += "9";
				maintext.setText(maintext.getText()+"*");
			}
		});
		btnPanel.add(btn9);
		
		JButton btnBlanc = new JButton("");
		btnPanel.add(btnBlanc);
		
		JButton btn0 = new JButton("0");
		btn0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pinEntry += "0";
				maintext.setText(maintext.getText()+"*");
			}
		});
		btnPanel.add(btn0);
		
		JButton btnC = new JButton("C");
		btnC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pinEntry = "";
			}
		});
		btnC.setBackground(Color.RED);
		btnC.setUI((ButtonUI) BasicButtonUI.createUI(btnC));
		btnPanel.add(btnC);
		
		btn20 = new JButton("20");
		btn20.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btn20.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!btnOk.isVisible()) return;
				amount = "20";
				
				maintext.setText("Chosen amount: "+amount);
			}
		});
		btn20.setBounds(128, 24, 47, 28);
		btn20.setVisible(false);
		contentPane.add(btn20);
		
		btn50 = new JButton("50");
		btn50.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btn50.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!btnOk.isVisible()) return;
				amount = "50";
				maintext.setText("Chosen amount: "+amount);
			}
		});
		btn50.setBounds(128, 63, 47, 28);
		btn50.setVisible(false);
		contentPane.add(btn50);
		
		btn70 = new JButton("70");
		btn70.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btn70.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!btnOk.isVisible()) return;
				amount = "70";
				maintext.setText("Chosen amount: "+amount);
			}
		});
		btn70.setBounds(128, 103, 47, 28);
		btn70.setVisible(false);
		contentPane.add(btn70);
		
		btnCancel = new JButton("C");
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reset();
			}
		});
		btnCancel.setBounds(128, 142, 47, 28);
		contentPane.add(btnCancel);
		
		btn100 = new JButton("100");
		btn100.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btn100.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!btnOk.isVisible()) return;
				amount = "100";
				maintext.setText("Chosen amount: "+amount);
			}
		});
		btn100.setBounds(495, 24, 47, 28);
		btn100.setVisible(false);
		contentPane.add(btn100);
		
		btn150 = new JButton("150");
		btn150.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btn150.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!btnOk.isVisible()) return;
				amount = "150";
				maintext.setText("Chosen amount: "+amount);
			}
		});
		btn150.setBounds(495, 63, 47, 28);
		btn150.setVisible(false);
		contentPane.add(btn150);
		
		btn200 = new JButton("200");
		btn200.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btn200.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!btnOk.isVisible()) return;
				amount = "200";
				maintext.setText("Chosen amount: "+amount);
			}
		});
		
		btn200.setBounds(495, 103, 47, 28);
		btn200.setVisible(false);
		contentPane.add(btn200);
		
		btnOk = new JButton("Ok");
		btnOk.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!btnOk.isVisible()) return;
				item = comboBox.getSelectedItem();
				cbVal = item.toString();
				availableBalance = bankDB.getAvailableBalance(Integer.valueOf(cbVal));
				name = bankDB.getName(Integer.valueOf(cbVal));
				userAuthenticated = bankDB.authenticateUser(Integer.valueOf(cbVal),Integer.valueOf(pinEntry));
			
					if (userAuthenticated) {		
					if (amount.length()>0) {
						if (availableBalance >= Integer.valueOf(amount)) {
						// an amount was chosen, eject note
						System.out.println("Ejecting amount: "+amount);
						banknote.animate(amount);
						timer.start();
						availableBalance = availableBalance - Integer.valueOf(amount);
						
						bankDB.setAvailableBalance(Integer.valueOf(cbVal),availableBalance);
						
						textField.setText(String.valueOf(availableBalance));
						try {
							AccountFactory.writeAccountsToFile("date.csv", bankDB.getAccounts());
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						}
						else {
							maintext.setText("Insufficient funds.");
						}
					}
					else {
						System.out.println("PIN Ok");
						lblAB.setVisible(true);
						textField.setVisible(true);
						textField.setText(String.valueOf(availableBalance));
						maintext.setText("Welcome "+ name + "!" + " Choose amount and press Ok.");
						
						
						btn20.setVisible(true);
						btn50.setVisible(true);
						btn70.setVisible(true);
						btn100.setVisible(true);
						btn150.setVisible(true);
						btn200.setVisible(true);
						amount = "";
					}
				}
				else {
					System.err.println("Wrong PIN");
					maintext.setText("PIN was incorrect. Try again.");
					pinEntry="";
				}
				
			}
		});
		btnOk.setBounds(495, 142, 47, 28);
		contentPane.add(btnOk);
		
		JPanel outputSlot = new JPanel();
		outputSlot.setBorder(new LineBorder(new Color(0, 0, 0), 4, true));
		outputSlot.setBounds(411, 265, 93, 15);
		contentPane.add(outputSlot);
		
		JPanel background = new JPanel();
		background.setBackground(SystemColor.activeCaption);
		background.setBounds(108, 0, 458, 408);
		contentPane.add(background);
		
		JLabel lblIdBank = new JLabel("MyBank");
		lblIdBank.setIcon(new ImageIcon(getClass().getResource("logo.png")));
		lblIdBank.setHorizontalAlignment(SwingConstants.RIGHT);
		
		//lblIdBank.setIcon(new ImageIcon(getClass().getResource("/logo.png")));
		GroupLayout gl_background = new GroupLayout(background);
		gl_background.setHorizontalGroup(
			gl_background.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_background.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_background.createParallelGroup(Alignment.LEADING)
						.addComponent(lblIdBank, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(349, Short.MAX_VALUE))
		);
		gl_background.setVerticalGroup(
			gl_background.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_background.createSequentialGroup()
					.addGap(220)
					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 101, Short.MAX_VALUE)
					.addComponent(lblIdBank, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		background.setLayout(gl_background);	
		
	}
	
	

	public void reset() {
		// Two lines of code that clear the variables pinEntry and amount.
		pinEntry=""; amount="";
		// A line that makes the banknote invisible again, using setVisible(false) method.
		banknote.setVisible(false);
		// A line that sets the card to the original location and gives original dimensions.
		// For this, look for the definition of the card (in the constructor) and copy the line using the setBounds(...) method.
		card.setBounds(10, 245, 60, 90);
		// A line that sets the original welcome text to maintext.
		maintext.setText("Welcome! Please enter your card.");
		textField.setVisible(false);
		lblAB.setVisible(false);
		
		btn20.setVisible(false);
		btn50.setVisible(false);
		btn70.setVisible(false);
		btn100.setVisible(false);
		btn150.setVisible(false);
		btn200.setVisible(false);
		timer.stop();
	}
}
