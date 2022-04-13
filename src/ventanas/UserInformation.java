package ventanas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import modelo.DatabaseConnection;

public class UserInformation extends JFrame implements ActionListener {

	String user = "", user_update = "";
	int ID;
	private JLabel labelTittle, labelName, labelEmail, labelPhone, labelPermissionOf, labelUsername, labelStatus,
			labelRegisterBy;
	private JTextField txtName, txtEmail, txtPhone, txtUsername, txtRegisterBy;
	private JComboBox<String> cmbLevels, cmbStatus;
	private JPanel container;
	private JButton btnUpdate, btnRestorePassword;

	public UserInformation() {
		initComponents();
		// user = Login.user;
		user_update = ManagementUsers.user_update; // Guardamos el usuario seleccionado en la tabla usuarios

		this.setResizable(false);
		this.setTitle("Informaci�n del usuario " + user_update + " - Sesi�n de " + user);
		this.setLocationRelativeTo(null);

		this.setSize(630, 460);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		labelTittle.setText("Informaci�n del usuario " + user_update);

		// Nos conectamos a la base de datos
		try {
			Connection cn = (Connection) DatabaseConnection.conectar();
			PreparedStatement pst = (PreparedStatement) cn
					.prepareStatement("SELECT * FROM usuarios WHERE username = '" + user_update + "'");
			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				ID = rs.getInt("id_usuario");

				txtName.setText(rs.getString("nombre_usuario"));
				txtEmail.setText(rs.getString("email"));
				txtPhone.setText(rs.getString("telefono"));
				txtUsername.setText(rs.getString("username"));
				txtRegisterBy.setText(rs.getString("registrado_por"));

				cmbLevels.setSelectedItem(rs.getString("tipo_nivel"));
				cmbStatus.setSelectedItem(rs.getString("estatus"));
			}

			cn.close();

		} catch (SQLException ex) {
			System.err.println("Error en cargar usuario " + ex);
			JOptionPane.showMessageDialog(null, "��Error al cargar!! Contacte al Administrador");
		}
	}

	public void initComponents() {

		this.container = new JPanel();
		this.container.setBackground(new Color(46, 59, 104));
		this.container.setLayout(null);
		this.container.setBounds(630, 460, 630, 460);
		this.setContentPane(container);

		labelTittle = new JLabel();
		labelTittle.setFont(new java.awt.Font("Segoe UI", 0, 24));
		labelTittle.setForeground(new java.awt.Color(192, 192, 192));
		labelTittle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		labelTittle.setText("Informaci�n del Usuario");
		labelTittle.setBounds(140, 10, 400, 30);
		this.container.add(labelTittle);

		labelName = new JLabel();
		labelName.setFont(new java.awt.Font("Segoe UI", 1, 12)); 
		labelName.setForeground(new java.awt.Color(192, 192, 192));
		labelName.setText("Nombre :");
		labelName.setBounds(20, 50, 100, 20);
		this.container.add(labelName);

		labelEmail = new JLabel();
		labelEmail.setFont(new java.awt.Font("Segoe UI", 1, 12)); 
		labelEmail.setForeground(new java.awt.Color(192, 192, 192));
		labelEmail.setText("Email :");
		labelEmail.setBounds(20, 110, 100, 20);
		this.container.add(labelEmail);

		labelPhone = new JLabel();
		labelPhone.setFont(new java.awt.Font("Segoe UI", 1, 12)); 
		labelPhone.setForeground(new java.awt.Color(192, 192, 192));
		labelPhone.setText("Tel�fono :");
		labelPhone.setBounds(20, 170, 100, 20);
		this.container.add(labelPhone);

		labelPermissionOf = new JLabel();
		labelPermissionOf.setFont(new java.awt.Font("Segoe UI", 1, 12)); 
		labelPermissionOf.setForeground(new java.awt.Color(192, 192, 192));
		labelPermissionOf.setText("Permisos de :");
		labelPermissionOf.setBounds(20, 230, 100, 20);
		this.container.add(labelPermissionOf);

		labelUsername = new JLabel();
		labelUsername.setFont(new java.awt.Font("Segoe UI", 1, 12)); 
		labelUsername.setForeground(new java.awt.Color(192, 192, 192));
		labelUsername.setText("Username :");
		labelUsername.setBounds(380, 50, 100, 20);
		this.container.add(labelUsername);

		labelStatus = new JLabel();
		labelStatus.setFont(new java.awt.Font("Segoe UI", 1, 12)); 
		labelStatus.setForeground(new java.awt.Color(192, 192, 192));
		labelStatus.setText("Estatus :");
		labelStatus.setBounds(380, 110, 100, 20);
		this.container.add(labelStatus);

		labelRegisterBy = new JLabel();
		labelRegisterBy.setFont(new java.awt.Font("Segoe UI", 1, 12)); 
		labelRegisterBy.setForeground(new java.awt.Color(192, 192, 192));
		labelRegisterBy.setText("Registrado por :");
		labelRegisterBy.setBounds(380, 160, 100, 20);

		txtName = new JTextField();
		txtName.setFont(new java.awt.Font("Segoe UI", 1, 16)); 
		txtName.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
		txtName.setBounds(20, 70, 210, 30);
		this.container.add(txtName);

		txtEmail = new JTextField();
		txtEmail.setFont(new java.awt.Font("Segoe UI", 1, 16)); 
		txtEmail.setHorizontalAlignment(javax.swing.JTextField.CENTER);
		txtEmail.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
		txtEmail.setBounds(20, 130, 210, 30);
		this.container.add(txtEmail);

		txtPhone = new JTextField();
		txtPhone.setFont(new java.awt.Font("Segoe UI", 1, 16)); 
		txtPhone.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
		txtPhone.setBounds(20, 190, 210, 30);
		this.container.add(txtPhone);

		txtUsername = new JTextField();
		txtUsername.setFont(new java.awt.Font("Segoe UI", 1, 16)); 
		txtUsername.setHorizontalAlignment(javax.swing.JTextField.CENTER);
		txtUsername.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
		txtUsername.setBounds(380, 70, 210, 30);
		this.container.add(txtUsername);

		txtRegisterBy = new JTextField();
		txtRegisterBy.setFont(new java.awt.Font("Segoe UI", 1, 16)); 
		txtRegisterBy.setHorizontalAlignment(javax.swing.JTextField.CENTER);
		txtRegisterBy.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
		txtRegisterBy.setEnabled(false);
		txtRegisterBy.setBounds(380, 190, 210, 30);
		this.container.add(txtRegisterBy);

		cmbStatus = new JComboBox<String>();
		cmbStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Activo", "Inactivo" }));
		cmbStatus.setBounds(380, 130, 150, 30);
		this.container.add(cmbStatus);

		cmbLevels = new JComboBox<String>();
		cmbLevels.setModel(
				new javax.swing.DefaultComboBoxModel<>(new String[] { "Administrador", "Capturista", "Tecnico" }));
		cmbLevels.setBounds(20, 250, 150, 30);
		this.container.add(cmbLevels);

		btnUpdate = new JButton();
		btnUpdate.setFont(new java.awt.Font("Tahoma", 1, 18));
		btnUpdate.setText("Actualizar Usuario");
		btnUpdate.setBorder(null);
		btnUpdate.addActionListener(this);
		btnUpdate.setBounds(380, 250, 210, 35);
		this.container.add(btnUpdate);

		btnRestorePassword = new JButton();
		btnRestorePassword.setFont(new java.awt.Font("Tahoma", 1, 18)); 
		btnRestorePassword.setText("Restaurar Password");
		btnRestorePassword.setBorder(null);
		btnRestorePassword.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				// jButton_RestaurarPasswordActionPerformed(evt);
			}
		});
		btnRestorePassword.setBounds(380, 300, 210, 35);
		this.container.add(btnRestorePassword);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == this.btnUpdate) {
			int cmbPermissions, cmbStatus, validation = 0;
	        String name, email, phone, username, password, permissionsString = "", statusString = "";

	        //Guardamos lo que esta en loc comboBox y en los campos de texto en las variables
	        email = txtEmail.getText().trim();
	        name = txtName.getText().trim();
	        username = txtUsername.getText().trim();
	        phone = txtPhone.getText().trim();
	        
	        cmbPermissions = cmbLevels.getSelectedIndex() + 1;
	        cmbStatus = this.cmbStatus.getSelectedIndex() + 1;

	        //Validamos que ningun campo este vacio
	        if (email.equals("")) {
	            txtEmail.setBackground(Color.red);
	            validation++;
	        }
	        if (username.equals("")) {
	            txtUsername.setBackground(Color.red);
	            validation++;
	        }
	        if (name.equals("")) {
	            txtName.setBackground(Color.red);
	            validation++;
	        }
	        if (phone.equals("")) {
	            txtPhone.setBackground(Color.red);
	            validation++;
	        }

	        //Cuando verifique que todos los campos esten llenos
	        if (validation == 0) {
	            
	            switch (cmbPermissions) {
	                case 1 ->
	                    permissionsString = "Administrador";
	                case 2 ->
	                    permissionsString = "Capturista";
	                case 3 ->
	                    permissionsString = "Tecnico";
	                default -> {
	                }
	            }
	            
	            if (cmbStatus == 1) {
	                statusString = "Activo";
	            } else if (cmbStatus == 2) {
	                statusString = "Inactivo";
	            }

	            //Realizamos la actualizacion a la base de datos
	            try {
	                Connection cn2 = (Connection) DatabaseConnection.conectar();
	                PreparedStatement pst2 = (PreparedStatement) cn2.prepareStatement("SELECT username FROM usuarios WHERE username = '" + username + "' AND NOT id_usuario = '" + ID + "'");
	                
	                ResultSet rs2 = pst2.executeQuery();
	                
	                if (rs2.next()) {
	                    txtUsername.setBackground(Color.red);
	                    JOptionPane.showMessageDialog(null, "Nombre de usuario no disponible");
	                    cn2.close();
	                } else {
	                    Connection cn3 = (Connection) DatabaseConnection.conectar();
	                    PreparedStatement pst3 = (PreparedStatement) cn3.prepareStatement(
	                            "UPDATE usuarios SET nombre_usuario = ?, email = ?, telefono = ?, username = ?, tipo_nivel = ?,"
	                            + " estatus = ? WHERE id_usuario = '" + ID + "'");
	                    
	                    pst3.setString(1, name);
	                    pst3.setString(2, email);
	                    pst3.setString(3, phone);
	                    pst3.setString(4, username);
	                    pst3.setString(5, permissionsString);
	                    pst3.setString(6, statusString);
	                    
	                    pst3.executeUpdate();
	                    
	                    cn3.close();
	                    
	                    JOptionPane.showMessageDialog(null, "Modificaci�n correcta");
	                }
	                
	            } catch (SQLException ex) {
	                System.err.println("Error al actualizar " + ex);
	            }
	            
	        } else {
	            JOptionPane.showMessageDialog(null, "Debes llenar todos los campos");
	        }
		}
	}
}
