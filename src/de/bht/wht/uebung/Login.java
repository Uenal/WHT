package de.bht.wht.uebung;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
 
public class Login extends JDialog {
 
	HashMap<String, String> personen = new HashMap<String, String>();
	
	JLabel warningLabel = new JLabel("Bitte geben Sie Benutzername und Kennwort ein");
	
	JLabel nameLabel = new JLabel("Name: ");
	JTextField nameField = new JTextField();
	JLabel passwordLabel = new JLabel("Password: ");
	JPasswordField passwordField = new JPasswordField();
	
	JButton okButton = new JButton("OK");
	JButton cancelButton = new JButton("Cancel");
 
	public Login() {
		setupUser();
		setupUI();
		setUpListeners();
	}
 
	private void setupUser() {
		
		personen.put("User1", "x401");
		personen.put("User2", "x4aq");
		personen.put("User3", "x4dc");
		personen.put("User4", "xqas");
		personen.put("User5", "xcxy");
		personen.put("User6", "xxya");
		personen.put("User7", "x9ki");
		personen.put("User8", "x467");
		
		personen.put("User9", "x4qq");
		personen.put("User10", "x4hq");
		personen.put("User11", "x93c");
		personen.put("User12", "x0as");
		personen.put("User13", "x1xy");
		personen.put("User14", "qaya");
		personen.put("User15", "pi9ki");
		personen.put("User16", "qo67");
		
		personen.put("User17", "zzqa");
		personen.put("User18", "jska");
		personen.put("User19", "ofue");
		personen.put("User20", "qxkd");
		personen.put("User21", "iwzu");
		personen.put("User22", "iref");
		personen.put("User23", "ylkh");
		personen.put("User24", "231o");
		
	}

	public void setupUI() {
 
		this.setTitle("Login");
		warningLabel.setForeground(Color.green);
 
		JPanel topPanel = new JPanel(new GridBagLayout());
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
 
		buttonPanel.add(okButton);
		buttonPanel.add(cancelButton);
 
		GridBagConstraints gbc = new GridBagConstraints();
 
		gbc.insets = new Insets(4, 4, 4, 4);
 
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 0;
		topPanel.add(nameLabel, gbc);
 
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1;
		topPanel.add(nameField, gbc);
 
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.fill = GridBagConstraints.NONE;
		gbc.weightx = 0;
		topPanel.add(passwordLabel, gbc);
 
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.weightx = 1;
		topPanel.add(passwordField, gbc);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.weightx = 1;
		topPanel.add(warningLabel, gbc);
 
		this.add(topPanel);
 
		this.add(buttonPanel, BorderLayout.SOUTH);
		
	}
 
	private void setUpListeners() {
 
		passwordField.addKeyListener(new KeyAdapter() {
 
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					login();
				}
			}
		});
 
		okButton.addActionListener(new ActionListener() {
 
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(!nameField.getText().isEmpty()) {
					
					if(personen.get(nameField.getText()) != null) {
					
						if(personen.get(nameField.getText()).equals(passwordField.getText())) {
							
							login();
							
						} else {
							
							warningLabel.setForeground(Color.red);
							warningLabel.setText("Das Kennwort ist falsch.");
							
						}
					
					} else {
						
						warningLabel.setForeground(Color.red);
						warningLabel.setText("Der Benutzernamen wurde nicht gefunden.");
						
					} 
					
				} else {
					
					warningLabel.setForeground(Color.red);
					warningLabel.setText("Bitte geben Sie den Benutzernamen ein.");
					
				}
				
			}
			
		});
 
		cancelButton.addActionListener(new ActionListener() {
 
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(1);
			}
		});
	}
	
	public void login() {
		
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				try {
					
					ExperimentGUI window = new ExperimentGUI(nameField.getText());
					window.frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		});
		
		Login.this.setVisible(false);
		
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Login ld = new Login();
		ld.setSize(400, 150);
		ld.setVisible(true);
	}

}