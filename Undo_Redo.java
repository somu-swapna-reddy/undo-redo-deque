package Pac1;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayDeque;
import java.util.Deque;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;

public class Undo_Redo {

	private JFrame frame;
	private JTextField key;
	private JFrame startframe;

	/**
	 * Launch the application.
	 */
	Deque<Character> deque = new ArrayDeque<Character>(); // Dequeue declaration
	private JTextField Undo;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Undo_Redo window = new Undo_Redo();
					window.startframe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Undo_Redo() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		startframe = new JFrame();
		startframe.setBounds(100, 100, 674, 558);
		startframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		startframe.getContentPane().setLayout(null);
		
		frame = new JFrame();
		frame.setBounds(100, 100, 557, 355);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(false);
		
		JLabel title = new JLabel("UNDO-REDO");
		title.setForeground(Color.DARK_GRAY);
		title.setFont(new Font("Tahoma", Font.BOLD, 18));
		title.setBounds(274, 26, 127, 44);
		startframe.getContentPane().add(title);
		
		JLabel lblNewLabel = new JLabel("Undo-Redo");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(219, 11, 118, 34);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("String :");
		lblNewLabel_1.setForeground(Color.DARK_GRAY);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblNewLabel_1.setBounds(58, 77, 89, 34);
		frame.getContentPane().add(lblNewLabel_1);
		
		JButton start = new JButton("START");
		start.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e)
	    	{
	    		frame.setVisible(true);
	    		startframe.setVisible(false);
	    	}
		});
		start.setFont(new Font("Tahoma", Font.BOLD, 18));
		start.setBounds(283, 437, 99, 67);
		startframe.getContentPane().add(start);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel.setBounds(132, 79, 390, 316);
		startframe.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel name = new JLabel("Name");
		name.setFont(new Font("Tahoma", Font.BOLD, 18));
		name.setBounds(53, 44, 101, 42);
		panel.add(name);
		
		JLabel roll = new JLabel("Roll No");
		roll.setFont(new Font("Tahoma", Font.BOLD, 18));
		roll.setBounds(255, 44, 101, 42);
		panel.add(roll);
		
		JLabel roll2 = new JLabel("2110030015");
		roll2.setFont(new Font("Tahoma", Font.BOLD, 18));
		roll2.setBounds(236, 172, 131, 42);
		panel.add(roll2);
		
		JLabel name2 = new JLabel("Swapna");
		name2.setFont(new Font("Tahoma", Font.BOLD, 18));
		name2.setBounds(37, 172, 117, 42);
		panel.add(name2);
		
		key = new JTextField();
		key.setForeground(Color.BLUE);
		key.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e)
			{
				deque.clear();
				Undo.setText(null);
			}
		});
		key.setFont(new Font("Tahoma", Font.PLAIN, 16));
		key.setBounds(171, 77, 212, 44);
		frame.getContentPane().add(key);
		key.setColumns(10);
		
		JButton btnUndo = new JButton("Undo");
		btnUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if(key.getText().equals("")) {
					JOptionPane.showMessageDialog(key,"Field is Empty");
				}
				else{
					String PreBackspace = key.getText();
					int len = PreBackspace.length();
					Character ch = PreBackspace.charAt(len-1); // Extracting the last character in the String
					//Undo.setText(Undo.getText()+Character.toString(ch)); // push the deleted character
					deque.push(ch);
					char PostBackspace[] = new char[PreBackspace.length()-1];
					char Temp[] = PreBackspace.toCharArray();
					for(int i = 0; i<Temp.length-1; i++) {
						PostBackspace[i] = Temp[i];
					}
					String Buffer = String.valueOf(PostBackspace);
					key.setText(Buffer);
					Undo.setText(deque.toString());
				}
			}
		});
		btnUndo.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 21));
		btnUndo.setBounds(169, 240, 102, 34);
		frame.getContentPane().add(btnUndo);
		
		JButton btnRedo = new JButton("Redo");
		btnRedo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if(!deque.isEmpty()) {
					StringBuilder buffer = new StringBuilder();
					buffer.append(key.getText());
					buffer.append(deque.pop());
					String newString = buffer.toString();
					key.setText(newString);
					Undo.setText(deque.toString());
				}
			}
		});
		btnRedo.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 21));
		btnRedo.setBounds(283, 240, 100, 34);
		frame.getContentPane().add(btnRedo);
		
		Undo = new JTextField();
		Undo.setEditable(false);
		Undo.setForeground(Color.BLUE);
		Undo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		Undo.setColumns(10);
		Undo.setBounds(171, 160, 212, 44);
		frame.getContentPane().add(Undo);
		
		JLabel lblNewLabel_1_1 = new JLabel("Undo :");
		lblNewLabel_1_1.setForeground(Color.DARK_GRAY);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblNewLabel_1_1.setBounds(58, 163, 78, 34);
		frame.getContentPane().add(lblNewLabel_1_1);
	}
}