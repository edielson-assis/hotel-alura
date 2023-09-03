package com.edielson.swing.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.text.Format;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.edielson.model.Guest;
import com.edielson.model.Reservation;
import com.edielson.service.GuestService;
import com.edielson.swing.Button;
import com.toedter.calendar.JDateChooser;

@SuppressWarnings("serial")
public class GuestRegistration extends JFrame {

	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtSobrenome;
	private JTextField txtTelefone;
	private JTextField txtNreserva;
	private JDateChooser txtDataN;
	private JComboBox<Format> txtNacionalidade;
	private static Reservation reservation;
	int xMouse, yMouse;

	/**
	 * Create the frame.
	 */
	public GuestRegistration(Reservation reservation) {
		this.reservation = reservation;
		setIconImage(
				Toolkit.getDefaultToolkit()
						.getImage(GuestRegistration.class.getResource("/com/edielson/images/lOGO-50PX.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 634);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.text);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		setUndecorated(true);
		contentPane.setLayout(null);

		JPanel header = new JPanel();
		header.setBounds(-54, 0, 910, 36);
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);
			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});

		txtNome = new JTextField();
		txtNome.setFont(new Font("sansserif", Font.PLAIN, 15));
		txtNome.setBounds(560, 135, 285, 33);
		txtNome.setBackground(Color.WHITE);
		txtNome.setColumns(10);
		txtNome.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtNome);

		txtSobrenome = new JTextField();
		txtSobrenome.setFont(new Font("sansserif", Font.PLAIN, 15));
		txtSobrenome.setBounds(560, 204, 285, 33);
		txtSobrenome.setColumns(10);
		txtSobrenome.setBackground(Color.WHITE);
		txtSobrenome.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtSobrenome);

		txtDataN = new JDateChooser();
		txtDataN.setBounds(560, 278, 285, 36);
		txtDataN.getCalendarButton();
		txtDataN.getCalendarButton().setBackground(SystemColor.textHighlight);
		txtDataN.setDateFormatString("dd/MM/yyyy");
		contentPane.add(txtDataN);

		txtNacionalidade = new JComboBox();
		txtNacionalidade.setBounds(560, 350, 289, 36);
		txtNacionalidade.setBackground(SystemColor.text);
		txtNacionalidade.setFont(new Font("sansserif", Font.PLAIN, 15));
		txtNacionalidade.setModel(new DefaultComboBoxModel(new String[] { "alemão", "andorrano", "angolano",
				"antiguano", "saudita", "argelino", "argentino", "armênio", "australiano", "austríaco", "azerbaijano",
				"bahamense", "bangladês, bangladense", "barbadiano", "bahreinita", "belga", "belizenho", "beninês",
				"belarusso", "boliviano", "bósnio", "botsuanês", "brasileiro", "bruneíno", "búlgaro",
				"burkineonse, burkinabé", "burundês", "butanês", "cabo-verdiano", "camerounês", "cambojano",
				"catariano", "canadense", "cazaque", "chadiano", "chileno", "chinês", "cipriota", "colombiano",
				"comoriano", "congolês", "congolês", "sul-coreano", "norte-coreano", "costa-marfinense, marfinense",
				"costa-ricense", "croata", "cubano", "dinamarquês", "djiboutiano", "dominiquense", "egípcio",
				"salvadorenho", "emiradense, emirático", "equatoriano", "eritreu", "eslovaco", "esloveno", "espanhol",
				"estadunidense, (norte-)americano", "estoniano", "etíope", "fijiano", "filipino", "finlandês",
				"francês", "gabonês", "gambiano", "ganês ou ganense", "georgiano", "granadino", "grego", "guatemalteco",
				"guianês", "guineense", "guineense, bissau-guineense", "equato-guineense", "haitiano", "hondurenho",
				"húngaro", "iemenita", "cookiano", "marshallês", "salomonense", "indiano", "indonésio", "iraniano",
				"iraquiano", "irlandês", "islandês", "34", "jamaicano", "japonês", "jordaniano", "kiribatiano",
				"kuwaitiano", "laosiano", "lesotiano", "letão", "libanês", "liberiano", "líbio", "liechtensteiniano",
				"lituano", "luxemburguês", "macedônio", "madagascarense", "malásio37", "malawiano", "maldivo",
				"maliano", "maltês", "marroquino", "mauriciano", "mauritano", "mexicano", "myanmarense", "micronésio",
				"moçambicano", "moldovo", "monegasco", "mongol", "montenegrino", "namibiano", "nauruano", "nepalês",
				"nicaraguense", "nigerino", "nigeriano", "niuiano", "norueguês", "neozelandês", "omani", "neerlandês",
				"palauano", "palestino", "panamenho", "papua, papuásio", "paquistanês", "paraguaio", "peruano",
				"polonês, polaco", "português", "queniano", "quirguiz", "britânico", "centro-africano", "tcheco",
				"dominicano", "romeno", "ruandês", "russo", "samoano", "santa-lucense", "são-cristovense", "samarinês",
				"santomense", "são-vicentino", "seichelense", "senegalês", "sérvio", "singapurense", "sírio",
				"somaliano, somali", "sri-lankês", "suázi", "sudanês", "sul-sudanês", "sueco", "suíço", "surinamês",
				"tajique", "tailandês", "tanzaniano", "timorense", "togolês", "tonganês", "trinitário", "tunisiano",
				"turcomeno", "turco", "tuvaluano", "ucraniano", "ugandês", "uruguaio", "uzbeque", "vanuatuense",
				"vaticano", "venezuelano", "vietnamita", "zambiano", "zimbabueano" }));
		contentPane.add(txtNacionalidade);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(562, 119, 253, 14);
		lblNome.setForeground(SystemColor.textInactiveText);
		lblNome.setFont(new Font("Roboto Black", Font.PLAIN, 16));
		contentPane.add(lblNome);

		JLabel lblSobrenome = new JLabel("Sobrenome");
		lblSobrenome.setBounds(560, 189, 255, 14);
		lblSobrenome.setForeground(SystemColor.textInactiveText);
		lblSobrenome.setFont(new Font("Roboto Black", Font.PLAIN, 16));
		contentPane.add(lblSobrenome);

		JLabel lblDataN = new JLabel("Data de Nascimento");
		lblDataN.setBounds(560, 256, 255, 14);
		lblDataN.setForeground(SystemColor.textInactiveText);
		lblDataN.setFont(new Font("Roboto Black", Font.PLAIN, 16));
		contentPane.add(lblDataN);

		JLabel lblNacionalidade = new JLabel("Nacionalidade");
		lblNacionalidade.setBounds(560, 326, 255, 14);
		lblNacionalidade.setForeground(SystemColor.textInactiveText);
		lblNacionalidade.setFont(new Font("Roboto Black", Font.PLAIN, 16));
		contentPane.add(lblNacionalidade);

		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setBounds(562, 406, 253, 14);
		lblTelefone.setForeground(SystemColor.textInactiveText);
		lblTelefone.setFont(new Font("Roboto Black", Font.PLAIN, 16));
		contentPane.add(lblTelefone);

		txtTelefone = new JTextField();
		txtTelefone.setFont(new Font("sansserif", Font.PLAIN, 15));
		txtTelefone.setBounds(560, 424, 285, 33);
		txtTelefone.setColumns(10);
		txtTelefone.setBackground(Color.WHITE);
		txtTelefone.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtTelefone);

		JLabel lblTitulo = new JLabel("REGISTRO DE HÓSPEDE");
		lblTitulo.setBounds(606, 55, 234, 42);
		lblTitulo.setForeground(new Color(12, 138, 199));
		lblTitulo.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		contentPane.add(lblTitulo);

		JLabel lblNumeroReserva = new JLabel("Número da Reserva");
		lblNumeroReserva.setBounds(560, 474, 253, 14);
		lblNumeroReserva.setForeground(SystemColor.textInactiveText);
		lblNumeroReserva.setFont(new Font("Roboto Black", Font.PLAIN, 16));
		contentPane.add(lblNumeroReserva);

		txtNreserva = new JTextField();
		txtNreserva.setFont(new Font("sansserif", Font.BOLD, 15));
		txtNreserva.setBounds(560, 495, 285, 33);
		txtNreserva.setColumns(10);
		txtNreserva.setBackground(Color.WHITE);
		txtNreserva.setEditable(false);
		txtNreserva.setText(String.valueOf(reservation.getId()));
		txtNreserva.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtNreserva);

		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setBounds(560, 170, 289, 2);
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		contentPane.add(separator_1_2);

		JSeparator separator_1_2_1 = new JSeparator();
		separator_1_2_1.setBounds(560, 240, 289, 2);
		separator_1_2_1.setForeground(new Color(12, 138, 199));
		separator_1_2_1.setBackground(new Color(12, 138, 199));
		contentPane.add(separator_1_2_1);

		JSeparator separator_1_2_2 = new JSeparator();
		separator_1_2_2.setBounds(560, 314, 289, 2);
		separator_1_2_2.setForeground(new Color(12, 138, 199));
		separator_1_2_2.setBackground(new Color(12, 138, 199));
		contentPane.add(separator_1_2_2);

		JSeparator separator_1_2_3 = new JSeparator();
		separator_1_2_3.setBounds(560, 386, 289, 2);
		separator_1_2_3.setForeground(new Color(12, 138, 199));
		separator_1_2_3.setBackground(new Color(12, 138, 199));
		contentPane.add(separator_1_2_3);

		JSeparator separator_1_2_4 = new JSeparator();
		separator_1_2_4.setBounds(560, 457, 289, 2);
		separator_1_2_4.setForeground(new Color(12, 138, 199));
		separator_1_2_4.setBackground(new Color(12, 138, 199));
		contentPane.add(separator_1_2_4);

		JSeparator separator_1_2_5 = new JSeparator();
		separator_1_2_5.setBounds(560, 529, 289, 2);
		separator_1_2_5.setForeground(new Color(12, 138, 199));
		separator_1_2_5.setBackground(new Color(12, 138, 199));
		contentPane.add(separator_1_2_5);

		Button btnsalvar = new Button();
		btnsalvar.setBounds(723, 560, 122, 35);

		btnsalvar.setLayout(null);
		contentPane.add(btnsalvar);
		btnsalvar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

		JButton labelSalvar = new JButton();
		labelSalvar.setHorizontalAlignment(SwingConstants.CENTER);
		labelSalvar.setBackground(new Color(12, 138, 199));
		labelSalvar.setForeground(new Color(250, 250, 250));
		labelSalvar.setText("Salvar");
		labelSalvar.setFont(new Font("sansserif", Font.PLAIN, 18));
		labelSalvar.setBounds(0, 0, 122, 35);
		btnsalvar.add(labelSalvar, "w 40%, h 40");
		labelSalvar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				save();
			}
		});

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 489, 634);
		panel.setBackground(new Color(12, 138, 199));
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel imageFundo = new JLabel("");
		imageFundo.setBounds(0, 121, 479, 502);
		panel.add(imageFundo);
		imageFundo.setIcon(new ImageIcon(GuestRegistration.class.getResource("/com/edielson/images/registro.png")));

		JLabel logo = new JLabel("");
		logo.setBounds(194, 39, 104, 107);
		panel.add(logo);
		logo.setIcon(new ImageIcon(GuestRegistration.class.getResource("/com/edielson/images/Ha-100px.png")));
	}

	private void save() {
		if (!isFieldEmpty(txtNome) && !isFieldEmpty(txtSobrenome) && !isFieldEmpty(txtTelefone)) {
			Integer age = ageCalculator(txtDataN.getDate());
			if (age < 18) {
				showMessage("Hóspede deve ter pelo menos 18 anos.");
				return;
			}

			if (!isAlphabetic(txtNome.getText())) {
				showMessage("Deve haver apenas letras no campo Nome");
				return;
			}

			if (!isAlphabetic(txtSobrenome.getText())) {
				showMessage("Deve haver apenas letras no campo Sobrenome");
				return;
			}

			if (!isNumeric(txtTelefone.getText())) {
				showMessage("Deve haver apenas números no campo Telefone");
				return;
			}

			Guest guest = new Guest(txtNome.getText(), txtSobrenome.getText(),
					txtDataN.getDate(), txtNacionalidade.getSelectedItem().toString(), txtTelefone.getText(), reservation.getId());

			showMessage("O número da sua reserva é: " + reservation.getId());
			saveGuest(guest);
		} else {
			showMessage("Preencha todos os campos");
		}
	}

	private boolean isFieldEmpty(JTextField field) {
		return field.getText().trim().isEmpty();
	}

	private void showMessage(String message) {
		JOptionPane.showMessageDialog(this, message);
	}

	private void saveGuest(Guest guest) {
		GuestService service = new GuestService();
		service.save(guest);
		Success success = new Success();
		success.setVisible(true);
		dispose();
	}

	private boolean isNumeric(String str) {
		return str.replaceAll("\\s+", "").matches("\\d+");
	}

	private boolean isAlphabetic(String str) {
		return str.replaceAll("\\s+", "").matches("[a-zA-Z]+");
	}

	private Integer ageCalculator(Date dataNascimento) {
		LocalDate dataN = dataNascimento.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate dataAtual = LocalDate.now();
		return Period.between(dataN, dataAtual).getYears();
	}

	// Código que permite movimentar a janela pela tela seguindo a posição de "x" e "y"
	private void headerMousePressed(java.awt.event.MouseEvent evt) {
		xMouse = evt.getX();
		yMouse = evt.getY();
	}

	private void headerMouseDragged(java.awt.event.MouseEvent evt) {
		int x = evt.getXOnScreen();
		int y = evt.getYOnScreen();
		this.setLocation(x - xMouse, y - yMouse);
	}
}