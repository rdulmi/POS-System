package devCashier.com;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class Payment {

	private JFrame frame;
	private JTextField billnum;
	private JTextField billamount;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Payment window = new Payment();
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
	public Payment() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(153, 204, 255));
		frame.setBounds(400, 200, 600, 400);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Bill Number");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 23));
		lblNewLabel_1.setBounds(120, 95, 215, 48);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Bill Amount");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 23));
		lblNewLabel_1_1.setBounds(120, 153, 215, 48);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		billnum = new JTextField();
		billnum.setHorizontalAlignment(SwingConstants.RIGHT);
		billnum.setBackground(new Color(204, 204, 255));
		billnum.setBounds(332, 109, 215, 31);
		frame.getContentPane().add(billnum);
		billnum.setColumns(10);
		
		billamount = new JTextField();
		billamount.setHorizontalAlignment(SwingConstants.RIGHT);
		billamount.setBackground(new Color(204, 204, 255));
		billamount.setColumns(10);
		billamount.setBounds(332, 167, 215, 31);
		frame.getContentPane().add(billamount);
		
		JButton btnNewButton = new JButton("Clear");
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(51, 153, 0));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 23));
		btnNewButton.setBounds(21, 271, 131, 48);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBackground(new Color(204, 0, 0));
		btnCancel.setForeground(new Color(255, 255, 255));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
			}
		});
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 23));
		btnCancel.setBounds(211, 271, 155, 48);
		frame.getContentPane().add(btnCancel);
		
		JButton btnPay = new JButton("Pay");
		btnPay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				if(billnum.getText().length()==0 || billamount.getText().length()==0 ) {
					JOptionPane.showMessageDialog(null, "Both Bill Number and Bill Amount Field Should be Filled");
				}else {
					UserDtails wind = new UserDtails();
					wind.main(null);
					frame.setVisible(false);
				}
				
				
				
			}
		});
		btnPay.setForeground(new Color(255, 255, 255));
		btnPay.setBackground(new Color(0, 0, 204));
		btnPay.setFont(new Font("Tahoma", Font.BOLD, 23));
		btnPay.setBounds(408, 271, 139, 48);
		frame.getContentPane().add(btnPay);
		
		JLabel lblNewLabel = new JLabel("Card Payment");
		lblNewLabel.setBounds(194, 25, 176, 31);
		frame.getContentPane().add(lblNewLabel);
		lblNewLabel.setBackground(Color.CYAN);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
	}

}
