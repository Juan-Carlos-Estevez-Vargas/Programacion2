package ventanas.administrator;

import java.awt.Color;
import java.awt.Font;
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
import javax.swing.JTextField;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import modelo.DatabaseConnection;

/**
 * 
 * @author Juan Carlos Estevez Vargas.
 *
 */
public class UserInformation extends JFrame implements ActionListener {

	/**
	 * Declaraci�n de Variables.
	 */
	private static final long serialVersionUID = 1L;
	private String user = "", user_update = "";
	private int ID;
	private JLabel labelTittle;
	private JLabel labelName;
	private JLabel labelEmail;
	private JLabel labelPhone;
	private JLabel labelPermissionOf;
	private JLabel labelUsername;
	private JLabel labelStatus;
	private JLabel labelRegisterBy;
	private JTextField txtName;
	private JTextField txtEmail;
	private JTextField txtPhone;
	private JTextField txtUsername;
	private JTextField txtRegisterBy;
	private JComboBox<String> cmbLevels;
	private JComboBox<String> cmbStatus;
	private JPanel container;
	private JButton btnUpdate;
	private JButton btnRestorePassword;

	/**
	 * Constructor de clase.
	 */
	public UserInformation() {
		initComponents();
		// user = Login.user;
		user_update = ManagementUsers.user_update; // Guardamos el usuario seleccionado en la tabla usuarios
		this.setResizable(false);
		this.setTitle("Informaci�n del usuario " + user_update + " - Sesi�n de " + user);
		this.setLocationRelativeTo(null);
		this.setSize(630, 460);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.labelTittle.setText("Informaci�n del usuario " + user_update);

		/**
		 * Conectando con la base de datos para recuperar la informaci�n del usuario
		 * previamente seleccionado el la tabla de la vista ManagementUsers.
		 */
		try {
			Connection cn = (Connection) DatabaseConnection.conectar();
			PreparedStatement pst = (PreparedStatement) cn
					.prepareStatement("SELECT * FROM usuarios WHERE username = '" + user_update + "'");
			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				this.ID = rs.getInt("id_usuario");
				this.txtName.setText(rs.getString("nombre_usuario"));
				this.txtEmail.setText(rs.getString("email"));
				this.txtPhone.setText(rs.getString("telefono"));
				this.txtUsername.setText(rs.getString("username"));
				this.txtRegisterBy.setText(rs.getString("registrado_por"));
				this.cmbLevels.setSelectedItem(rs.getString("tipo_nivel"));
				this.cmbStatus.setSelectedItem(rs.getString("estatus"));
			}
			cn.close();
		} catch (SQLException ex) {
			System.err.println("Error en cargar usuario " + ex);
			JOptionPane.showMessageDialog(null, "��Error al cargar!! Contacte al Administrador");
		}
	}

	/**
	 * Construye los componentes Swing en el Frame.
	 */
	public void initComponents() {

		/**
		 * Panel Principal.
		 */
		this.container = new JPanel();
		this.container.setBackground(new Color(46, 59, 104));
		this.container.setLayout(null);
		this.container.setBounds(630, 460, 630, 460);
		this.setContentPane(container);

		/**
		 * Label Principal.
		 */
		labelTittle = new JLabel("Informaci�n del Usuario");
		labelTittle.setFont(new java.awt.Font("Segoe UI", 0, 24));
		labelTittle.setForeground(new java.awt.Color(192, 192, 192));
		labelTittle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		labelTittle.setBounds(110, 10, 400, 30);
		this.container.add(labelTittle);

		/**
		 * Label Nombre.
		 */
		labelName = new JLabel();
		labelName.setFont(new java.awt.Font("Segoe UI", 1, 12));
		labelName.setForeground(new java.awt.Color(192, 192, 192));
		labelName.setText("Nombre :");
		labelName.setBounds(20, 50, 100, 20);
		this.container.add(labelName);

		/**
		 * Label Email.
		 */
		labelEmail = new JLabel();
		labelEmail.setFont(new java.awt.Font("Segoe UI", 1, 12));
		labelEmail.setForeground(new java.awt.Color(192, 192, 192));
		labelEmail.setText("Email :");
		labelEmail.setBounds(20, 110, 100, 20);
		this.container.add(labelEmail);

		/**
		 * Label Phone.
		 */
		labelPhone = new JLabel();
		labelPhone.setFont(new java.awt.Font("Segoe UI", 1, 12));
		labelPhone.setForeground(new java.awt.Color(192, 192, 192));
		labelPhone.setText("Tel�fono :");
		labelPhone.setBounds(20, 170, 100, 20);
		this.container.add(labelPhone);

		/**
		 * Label Permisos de.
		 */
		labelPermissionOf = new JLabel();
		labelPermissionOf.setFont(new java.awt.Font("Segoe UI", 1, 12));
		labelPermissionOf.setForeground(new java.awt.Color(192, 192, 192));
		labelPermissionOf.setText("Permisos de :");
		labelPermissionOf.setBounds(20, 230, 100, 20);
		this.container.add(labelPermissionOf);

		/**
		 * Label Username.
		 */
		labelUsername = new JLabel();
		labelUsername.setFont(new java.awt.Font("Segoe UI", 1, 12));
		labelUsername.setForeground(new java.awt.Color(192, 192, 192));
		labelUsername.setText("Username :");
		labelUsername.setBounds(360, 50, 100, 20);
		this.container.add(labelUsername);

		/**
		 * Label Status.
		 */
		labelStatus = new JLabel();
		labelStatus.setFont(new java.awt.Font("Segoe UI", 1, 12));
		labelStatus.setForeground(new java.awt.Color(192, 192, 192));
		labelStatus.setText("Estatus :");
		labelStatus.setBounds(360, 110, 100, 20);
		this.container.add(labelStatus);

		/**
		 * Label Registrado Por.
		 */
		labelRegisterBy = new JLabel();
		labelRegisterBy.setFont(new java.awt.Font("Segoe UI", 1, 12));
		labelRegisterBy.setForeground(new java.awt.Color(192, 192, 192));
		labelRegisterBy.setText("Registrado por :");
		labelRegisterBy.setBounds(360, 170, 100, 20);
		this.container.add(this.labelRegisterBy);

		/**
		 * Campo de texto con la informaci�n del nombre del usuario a actualizar.
		 */
		txtName = new JTextField();
		txtName.setBounds(20, 70, 230, 30);
		this.txtName.setBackground(new Color(127, 140, 141));
		this.txtName.setFont(new Font("serif", Font.BOLD, 20));
		this.txtName.setHorizontalAlignment(JLabel.LEFT);
		this.txtName.setForeground(Color.WHITE);
		this.container.add(txtName);

		/**
		 * Campo de texto con la informaci�n del email del usuario a actualizar.
		 */
		txtEmail = new JTextField();
		txtEmail.setBounds(20, 130, 230, 30);
		this.txtEmail.setBackground(new Color(127, 140, 141));
		this.txtEmail.setFont(new Font("serif", Font.BOLD, 20));
		this.txtEmail.setHorizontalAlignment(JLabel.LEFT);
		this.txtEmail.setForeground(Color.WHITE);
		this.container.add(txtEmail);

		/**
		 * Campo de texto con la informaci�n del tel�fono del usuario a actualizar.
		 */
		txtPhone = new JTextField();
		txtPhone.setFont(new java.awt.Font("Segoe UI", 1, 16));
		txtPhone.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
		txtPhone.setBounds(20, 190, 230, 30);
		this.txtPhone.setBackground(new Color(127, 140, 141));
		this.txtPhone.setFont(new Font("serif", Font.BOLD, 20));
		this.txtPhone.setHorizontalAlignment(JLabel.LEFT);
		this.txtPhone.setForeground(Color.WHITE);
		this.container.add(txtPhone);

		/**
		 * Campo de texto con la informaci�n del username del usuario a actualizar.
		 */
		txtUsername = new JTextField();
		txtUsername.setHorizontalAlignment(javax.swing.JTextField.CENTER);
		txtUsername.setBounds(360, 70, 230, 30);
		this.txtUsername.setBackground(new Color(127, 140, 141));
		this.txtUsername.setFont(new Font("serif", Font.BOLD, 20));
		this.txtUsername.setForeground(Color.WHITE);
		this.container.add(txtUsername);

		/**
		 * Campo de texto con la informaci�n de qui�n registr� el usuario a actualizar.
		 */
		txtRegisterBy = new JTextField();
		txtRegisterBy.setHorizontalAlignment(javax.swing.JTextField.CENTER);
		txtRegisterBy.setEnabled(false);
		txtRegisterBy.setBounds(360, 190, 230, 30);
		this.txtRegisterBy.setBackground(new Color(127, 140, 141));
		this.txtRegisterBy.setFont(new Font("serif", Font.BOLD, 20));
		this.txtRegisterBy.setForeground(Color.WHITE);
		this.container.add(txtRegisterBy);

		/**
		 * ComboBox con la informaci�n del Status del usuario a actualizar.
		 */
		cmbStatus = new JComboBox<String>();
		cmbStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Activo", "Inactivo" }));
		cmbStatus.setBounds(360, 130, 170, 30);
		this.cmbStatus.setBackground(new Color(127, 140, 141));
		this.cmbStatus.setFont(new Font("serif", Font.BOLD, 20));
		this.cmbStatus.setForeground(Color.WHITE);
		this.container.add(cmbStatus);

		/**
		 * ComboBox con la informaci�n del nivel del usuario a actualizar.
		 */
		cmbLevels = new JComboBox<String>();
		cmbLevels.setModel(
				new javax.swing.DefaultComboBoxModel<>(new String[] { "Administrador", "Capturista", "Tecnico" }));
		cmbLevels.setBounds(20, 250, 170, 30);
		this.cmbLevels.setBackground(new Color(127, 140, 141));
		this.cmbLevels.setFont(new Font("serif", Font.BOLD, 20));
		this.cmbLevels.setForeground(Color.WHITE);
		this.container.add(cmbLevels);

		/**
		 * Bot�n encargado de actualizar el usuario en cuesti�n.
		 */
		btnUpdate = new JButton("Actualizar Usuario");
		btnUpdate.setBounds(380, 250, 210, 35);
		this.btnUpdate.setFont(new Font("serif", Font.BOLD, 20));
		this.btnUpdate.setBackground(new Color(8, 85, 224));
		this.btnUpdate.setForeground(Color.WHITE);
		this.btnUpdate.setHorizontalAlignment(JButton.CENTER);
		this.btnUpdate.addActionListener(this);
		this.container.add(btnUpdate);

		/**
		 * Bot�n para restaurar la contrase�a del usuario en cuesti�n.
		 */
		btnRestorePassword = new JButton("Restaurar Password");
		btnRestorePassword.setBounds(380, 300, 210, 35);
		this.btnRestorePassword.setFont(new Font("serif", Font.BOLD, 20));
		this.btnRestorePassword.setBackground(new Color(8, 85, 224));
		this.btnRestorePassword.setForeground(Color.WHITE);
		this.btnRestorePassword.setHorizontalAlignment(JButton.CENTER);
		this.btnRestorePassword.addActionListener(this);
		this.container.add(btnRestorePassword);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		/**
		 * Actualizaci�n del usuario en el sistema y la base de datos.
		 */
		if (e.getSource() == this.btnUpdate) {
			int cmbPermissions, cmbStatus, validation = 0;
			String name, email, phone, username, permissionsString = "", statusString = "";

			/**
			 * Guardamos lo que esta en loc comboBox y en los campos de texto en las
			 * variables.
			 */
			email = txtEmail.getText().trim();
			name = txtName.getText().trim();
			username = txtUsername.getText().trim();
			phone = txtPhone.getText().trim();
			cmbPermissions = cmbLevels.getSelectedIndex() + 1;
			cmbStatus = this.cmbStatus.getSelectedIndex() + 1;

			/**
			 * Validamos que ningun campo este vacio.
			 */
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

			/**
			 * Cuando verifiquemos que todos los campos esten llenos.
			 */
			if (validation == 0) {

				switch (cmbPermissions) {
				case 1 -> permissionsString = "Administrador";
				case 2 -> permissionsString = "Capturista";
				case 3 -> permissionsString = "Tecnico";
				default -> {
				}
				}

				if (cmbStatus == 1) {
					statusString = "Activo";
				} else if (cmbStatus == 2) {
					statusString = "Inactivo";
				}

				/**
				 * Realizamos la actualizacion en la base de datos
				 */
				try {
					Connection cn2 = (Connection) DatabaseConnection.conectar();
					PreparedStatement pst2 = (PreparedStatement) cn2
							.prepareStatement("SELECT username FROM usuarios WHERE username = '" + username
									+ "' AND NOT id_usuario = '" + ID + "'");
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
						JOptionPane.showMessageDialog(null, "Modificaci�n exitosa!!");
					}
				} catch (SQLException ex) {
					System.err.println("Error al actualizar " + ex);
				}
			} else {
				JOptionPane.showMessageDialog(null, "Debes llenar todos los campos");
			}
		}
		/**
		 * Muestra el panel para modificar la contrase�a del usuario en cuesti�n.
		 */
		if (e.getSource() == this.btnRestorePassword) {
			RestorePassword restorePassword = new RestorePassword();
			restorePassword.setVisible(true);
		}
	}
}