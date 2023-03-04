package com.itx.encrypt;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import org.json.JSONObject;
import tools.EncryptTool;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import javax.swing.JCheckBox;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {

	private JPanel contentPane;
	private JTextField tfKey;
	private JTextField tfSalt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
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
	public MainFrame() {
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setTitle("EncryptDecryptApp");
		ArrayList<Image> icons = new ArrayList<Image>();
		icons.add(Toolkit.getDefaultToolkit().getImage("img\\ic_logo.png"));
		icons.add(Toolkit.getDefaultToolkit().getImage("img\\ic_logo.png"));
		icons.add(Toolkit.getDefaultToolkit().getImage("img\\ic_logo.png"));
		icons.add(Toolkit.getDefaultToolkit().getImage("img\\ic_logo.png"));
		this.setIconImages(icons);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 502, 320);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblString = new JLabel("String:");
		lblString.setBounds(6, 77, 89, 14);
		contentPane.add(lblString);
		
		JButton btnEncrypt = new JButton("Encrypt");
		btnEncrypt.setBounds(266, 247, 89, 23);
		contentPane.add(btnEncrypt);
		
		JButton btnDecrypt = new JButton("Decrypt");
		btnDecrypt.setBounds(365, 247, 89, 23);
		contentPane.add(btnDecrypt);
		
		JTextArea tfString = new JTextArea();
		tfString.setBounds(116, 72, 338, 164);
		contentPane.add(tfString);
		
		JCheckBox chkIsJson = new JCheckBox("IsJson");
		chkIsJson.setBounds(6, 109, 93, 23);
		contentPane.add(chkIsJson);
		
		JLabel lblKey = new JLabel("Key");
		lblKey.setBounds(6, 11, 46, 14);
		contentPane.add(lblKey);
		
		JLabel lblSalt = new JLabel("Salt");
		lblSalt.setBounds(6, 36, 46, 14);
		contentPane.add(lblSalt);
		
		tfKey = new JTextField();
		tfKey.setText("LoUCtInA+HOJI85SJMI2qA==");
		tfKey.setBounds(116, 8, 338, 20);
		contentPane.add(tfKey);
		tfKey.setColumns(10);
		
		tfSalt = new JTextField();
		tfSalt.setBounds(116, 33, 338, 20);
		contentPane.add(tfSalt);
		tfSalt.setColumns(10);
		
		btnEncrypt.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if(chkIsJson.isSelected()){
						JSONObject obj = new JSONObject();
						tfString.setText(generateParams(obj, new JSONObject(tfString.getText()), true).toString());
					}else{
						tfString.setText(EncryptTool.encriptar(tfString.getText()));
					}					
				} catch (Exception ex) { 
					ex.printStackTrace();
				}
			}
			
		});
		
		btnDecrypt.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if(chkIsJson.isSelected()){
						JSONObject obj = new JSONObject();
						tfString.setText(generateParams(obj, new JSONObject(tfString.getText()), false).toString());
					}else{
						tfString.setText(EncryptTool.desencriptar(tfString.getText()));
					}
				} catch (Exception ex) { 
					ex.printStackTrace();
				}
			}
			
		});
			
	}
	
	public static JSONObject generateParams(JSONObject parameters, JSONObject obj, boolean crypt) throws Exception{
		Iterator<String> keys = obj.keys();
		
		while(keys.hasNext()){
			String key = keys.next();
			if(obj.get(key) instanceof JSONObject){
				JSONObject parametersChild = generateParams(parameters, obj.getJSONObject(key), crypt);
				parameters.put(key, parametersChild);
			} else {
				if(crypt == true){
					parameters.put(key, EncryptTool.encriptar(obj.get(key).toString()));
				}else{
					parameters.put(key, EncryptTool.desencriptar(obj.get(key).toString()));
				}					
			}
		}
		
		return parameters;
	}
}
