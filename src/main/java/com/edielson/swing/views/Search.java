package com.edielson.swing.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.edielson.model.Guest;
import com.edielson.model.Reservation;
import com.edielson.service.GuestService;
import com.edielson.service.ReservationService;

@SuppressWarnings("serial")
public class Search extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHospedes;
	private JTable tbReservas;
	private DefaultTableModel modelo;
	private DefaultTableModel modeloHospedes;
	private JLabel labelAtras;
	private JLabel labelExit;
	int xMouse, yMouse;

	/**
	 * Create the frame.
	 */
	public Search() {
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(Search.class.getResource("/com/edielson/images/lOGO-50PX.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);

		txtBuscar = new JTextField();
		txtBuscar.setBounds(536, 127, 193, 31);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);

		JLabel lblTitulo = new JLabel("SISTEMA DE BUSCA");
		lblTitulo.setForeground(new Color(12, 138, 199));
		lblTitulo.setFont(new Font("Roboto Black", Font.BOLD, 24));
		lblTitulo.setBounds(331, 62, 280, 42);
		contentPane.add(lblTitulo);

		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBackground(new Color(12, 138, 199));
		panel.setFont(new Font("sansserif", Font.PLAIN, 16));
		panel.setBounds(20, 169, 865, 328);
		contentPane.add(panel);

		tbReservas = new JTable();
		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservas.setFont(new Font("sansserif", Font.PLAIN, 16));
		modelo = (DefaultTableModel) tbReservas.getModel();
		modelo.addColumn("Numero de Reserva");
		modelo.addColumn("Data Check In");
		modelo.addColumn("Data Check Out");
		modelo.addColumn("Valor");
		modelo.addColumn("Forma de Pago");
		JScrollPane scroll_table = new JScrollPane(tbReservas);
		panel.addTab("Reservas", new ImageIcon(Search.class.getResource("/com/edielson/images/reservado.png")),
				scroll_table, null);
		scroll_table.setVisible(true);

		tbHospedes = new JTable();
		tbHospedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHospedes.setFont(new Font("sansserif", Font.PLAIN, 16));
		modeloHospedes = (DefaultTableModel) tbHospedes.getModel();
		modeloHospedes.addColumn("Numero de Hóspede");
		modeloHospedes.addColumn("Nome");
		modeloHospedes.addColumn("Sobrenome");
		modeloHospedes.addColumn("Data de Nascimento");
		modeloHospedes.addColumn("Nacionalidade");
		modeloHospedes.addColumn("Telefone");
		modeloHospedes.addColumn("Numero de Reserva");
		JScrollPane scroll_tableHuespedes = new JScrollPane(tbHospedes);
		panel.addTab("Hóspedes", new ImageIcon(Search.class.getResource("/com/edielson/images/pessoas.png")),
				scroll_tableHuespedes,
				null);
		scroll_tableHuespedes.setVisible(true);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Search.class.getResource("/com/edielson/images/Ha-100px.png")));
		lblNewLabel_2.setBounds(56, 51, 104, 107);
		contentPane.add(lblNewLabel_2);

		JPanel header = new JPanel();
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
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);

		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				UserMenu user = new UserMenu();
				user.setVisible(true);
				dispose();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				labelAtras.setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnAtras.setBackground(Color.white);
				labelAtras.setForeground(Color.black);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);

		labelAtras = new JLabel("<");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("sansserif", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);

		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				UserMenu user = new UserMenu();
				user.setVisible(true);
				dispose();
			}

			@Override
			public void mouseEntered(MouseEvent e) { // Quando o usuário passa o mouse sobre o botão, ele muda de cor
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) { // Quando o usuário remove o mouse do botão, ele retornará ao estado
													// original
				btnexit.setBackground(Color.white);
				labelExit.setForeground(Color.black);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(Color.WHITE);
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);

		labelExit = new JLabel("X");
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.BLACK);
		labelExit.setFont(new Font("sansserif", Font.PLAIN, 18));
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);

		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		separator_1_2.setBounds(539, 159, 193, 2);
		contentPane.add(separator_1_2);

		JButton btnbuscar = new JButton();
		btnbuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clear();
				searchReservation(txtBuscar.getText());
			}
		});
		btnbuscar.setLayout(null);
		btnbuscar.setBackground(new Color(12, 138, 199));
		btnbuscar.setBounds(748, 125, 122, 35);
		btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnbuscar);

		JLabel lblBuscar = new JLabel("BUSCAR");
		lblBuscar.setBounds(0, 0, 122, 35);
		btnbuscar.add(lblBuscar, "w 40%, h 40");
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(Color.WHITE);
		lblBuscar.setFont(new Font("sansserif", Font.PLAIN, 18));

		JButton btnEditar = new JButton();
		btnEditar.setLayout(null);
		btnEditar.setBackground(new Color(12, 138, 199));
		btnEditar.setBounds(635, 508, 122, 35);
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEditar);
		btnEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (tbReservas.getSelectedRow() != -1 || tbHospedes.getSelectedRow() != -1) {
					if (tbReservas.getSelectedRow() != -1) {
						updateReservation(modelo);
					}
					if (tbHospedes.getSelectedRow() != -1) {
						updateGuest(modeloHospedes);
					}
				} else {
					showMessage("Por favor, escolha um registro");
				}
			}
		});

		JLabel lblEditar = new JLabel("EDITAR");
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setForeground(Color.WHITE);
		lblEditar.setFont(new Font("sansserif", Font.PLAIN, 18));
		lblEditar.setBounds(0, 0, 122, 35);
		btnEditar.add(lblEditar, "w 40%, h 40");

		JButton btnDeletar = new JButton();
		btnDeletar.setLayout(null);
		btnDeletar.setBackground(new Color(12, 138, 199));
		btnDeletar.setBounds(767, 508, 122, 35);
		btnDeletar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnDeletar);
		btnDeletar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (isNumeric(txtBuscar.getText())) {
					Integer id = Integer.parseInt(txtBuscar.getText());
					remove(id);
					clear();
					showMessage("Dados removidos");
				} else {
					showMessage("Id inválido!");
				}
			}
		});

		JLabel lblExcluir = new JLabel("DELETAR");
		lblExcluir.setHorizontalAlignment(SwingConstants.CENTER);
		lblExcluir.setForeground(Color.WHITE);
		lblExcluir.setFont(new Font("sansserif", Font.PLAIN, 18));
		lblExcluir.setBounds(0, 0, 122, 35);
		btnDeletar.add(lblExcluir, "w 40%, h 40");
		setResizable(false);
	}

	private GuestService instantiateGuestService() {
		return new GuestService();
	}

	private ReservationService instantiateReservationService() {
		return new ReservationService();
	}

	private static final SimpleDateFormat SDF = new SimpleDateFormat("dd-MM-yyyy");

	private void searchReservation(String id) {
		List<Reservation> reservations = null;
		List<Guest> guests = null;

		if (!txtBuscar.getText().isEmpty()) {
			reservations = instantiateReservationService().findById(id);
			guests = instantiateGuestService().findById(id);
		} else {
			reservations = instantiateReservationService().findAll();
			guests = instantiateGuestService().findAll();
		}

		if (!guests.isEmpty() && !reservations.isEmpty()) {
			reservations.forEach(reserve -> {
				modelo.addRow(new Object[] { reserve.getId(), SDF.format(reserve.getDataEntrada()),
						SDF.format(reserve.getDataSaida()), reserve.getValor(), reserve.getFormaPagamento() });
			});

			guests.forEach(guest -> {
				modeloHospedes.addRow(new Object[] { guest.getId(), guest.getNome(), guest.getSobrenome(),
						SDF.format(guest.getDataDeNascimento()), guest.getNacionalidade(), guest.getTelefone(),
						guest.getIdReserva() });
			});
		} else {
			showMessage("Id inválido!");
		}
	}

	private void showMessage(String message) {
		JOptionPane.showMessageDialog(this, message);
	}

	private void remove(Integer id) {
		if (id != null) {
			instantiateReservationService().remove(id);
		} else {
			throw new IllegalStateException("Serviço estava nulo");
		}
	}

	private static boolean isNumeric(String str) {
		if (str.replaceAll("\\s+", "").matches("\\d+")) {
			return true;
		}
		return false;
	}

	private void clear() {
		modelo.setRowCount(0);
		modeloHospedes.setRowCount(0);
	}

	private void updateReservation(DefaultTableModel modelo) {
		Reservation reservation = new Reservation();

		Optional.ofNullable(modelo.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn()))
			.ifPresentOrElse(fila -> {
			String idStr = modelo.getValueAt(tbReservas.getSelectedRow(), 0).toString();
			if (idStr.isEmpty()) {
				showMessage("ID de reserva não pode estar vazio.");
				return;
			}

			int id = Integer.parseInt(idStr);
			if (id <= 0) {
				showMessage("ID de reserva inválido.");
				return;
			}
			try {
				Date dataEntrada = SDF.parse(modelo.getValueAt(tbReservas.getSelectedRow(), 1).toString());
				Date dataSaida = SDF.parse(modelo.getValueAt(tbReservas.getSelectedRow(), 2).toString());

				if (dataEntrada.before(new Date())) {
					showMessage("A reserva deve ser marcada para datas futuras.");
					return;
				}

				if (!dataSaida.after(dataEntrada)) {
					showMessage("Data de saída deve ser depois da data de entrada.");
					return;
				}

				BigDecimal valor = reservationCalculator(dataEntrada, dataSaida);
				if (valor == null) {
					showMessage("Erro ao calcular o valor da reserva.");
					return;
				}

				reservation.setId(id);
				reservation.setDataEntrada(dataEntrada);
				reservation.setDataSaida(dataSaida);
				reservation.setValor(valor);
				reservation.setFormaPagamento(modelo.getValueAt(tbReservas.getSelectedRow(), 4).toString());

				instantiateReservationService().update(reservation);
				showMessage("Registro modificado com êxito");
			} catch (ParseException e) {
				showMessage("Erro ao analisar as datas.");
			}
		}, () -> {});
	}

	private void updateGuest(DefaultTableModel modeloHospedes) {
		Guest guest = new Guest();

		Optional.ofNullable(modeloHospedes.getValueAt(tbHospedes.getSelectedRow(), tbHospedes.getSelectedColumn()))
			.ifPresentOrElse(filaHuesped -> {
			String idStr = modeloHospedes.getValueAt(tbHospedes.getSelectedRow(), 0).toString();
			if (idStr.isEmpty()) {
				showMessage("ID de hóspede não pode estar vazio.");
				return;
			}

			int id = Integer.parseInt(idStr);
			if (id <= 0) {
				showMessage("ID de hóspede inválido.");
				return;
			}
			try {
				guest.setId(id);
				String nome = modeloHospedes.getValueAt(tbHospedes.getSelectedRow(), 1).toString();
				guest.setNome(nome);

				String sobrenome = modeloHospedes.getValueAt(tbHospedes.getSelectedRow(), 2).toString();
				guest.setSobrenome(sobrenome);

				Date dataDeNascimento = SDF.parse(modeloHospedes.getValueAt(tbHospedes.getSelectedRow(), 3).toString());
				guest.setDataDeNascimento(dataDeNascimento);

				String nacionalidade = modeloHospedes.getValueAt(tbHospedes.getSelectedRow(), 4).toString();
				guest.setNacionalidade(nacionalidade);

				if (!isAlphabetic(nome)) {
					showMessage("Deve haver apenas letras no campo Nome");
					return;
				}
	
				if (!isAlphabetic(sobrenome)) {
					showMessage("Deve haver apenas letras no campo Sobrenome");
					return;
				}

				String telefone = modeloHospedes.getValueAt(tbHospedes.getSelectedRow(), 5).toString();
				if (!isNumeric(telefone)) {
					showMessage("Telefone deve conter apenas números.");
					return;
				}
				guest.setTelefone(telefone);

				Integer age = ageCalculator(dataDeNascimento);
				if (age < 18) {
					showMessage("Hóspede deve ter pelo menos 18 anos.");
					return;
				}

				String idStr2 = modeloHospedes.getValueAt(tbHospedes.getSelectedRow(), 6).toString();
				if (idStr2.isEmpty()) {
					showMessage("IdReserva não pode estar vazio.");
					return;
				}

				int idReserva = Integer.parseInt(idStr2);
				if (idReserva < 0) {
					showMessage("ID de reserva inválido para o hóspede.");
					return;
				}
				guest.setIdReserva(idReserva);

				instantiateGuestService().update(guest);
				showMessage("Registro modificado com êxito");
			} catch (ParseException e) {
				showMessage("Erro ao analisar as datas.");
			}
		}, () -> {});
	}

	private boolean isAlphabetic(String str) {
		return str.replaceAll("\\s+", "").matches("[a-zA-Z]+");
	}

	private Integer ageCalculator(Date dataNascimento) {
		LocalDate dataN = dataNascimento.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate dataAtual = LocalDate.now();
		return Period.between(dataN, dataAtual).getYears();
	}

	private BigDecimal reservationCalculator(Date checkin, Date checkout) {
		if (checkin.before(new Date()) || checkout.before(new Date())) {
			showMessage("A reserva deve ser marcada para datas futuras.");
			return null;
		} else if (!checkout.after(checkin)) {
			showMessage("Data de saída deve ser depois da data de entrada.");
			return null;
		} else {
			Duration duration = Duration.between(checkin.toInstant(), checkout.toInstant());
			long totalDays = duration.toDays();
			BigDecimal totalValor = BigDecimal.valueOf(100.0).multiply(BigDecimal.valueOf(totalDays));
			String formattedValue = totalValor.setScale(2, RoundingMode.HALF_UP).toString();
			modelo.setValueAt(formattedValue, tbReservas.getSelectedRow(), 3);
			return totalValor.setScale(2, RoundingMode.HALF_UP);
		}
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