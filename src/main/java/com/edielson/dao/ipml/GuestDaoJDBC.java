package com.edielson.dao.ipml;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.edielson.connection.DatabaseConnection;
import com.edielson.connection.DbException;
import com.edielson.dao.GuestDao;
import com.edielson.model.Guest;
import com.edielson.model.Reservation;

public class GuestDaoJDBC implements GuestDao {

    private Connection con;

    public GuestDaoJDBC(Connection con) {
        this.con = con;
    }

    @Override
    public void save(Guest guest) {
        try (PreparedStatement st = con.prepareStatement(
                "INSERT INTO Hospedes (Nome, Sobrenome, DataNascimento, Nacionalidade, Telefone, IdReserva) VALUES (?, ?, ?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS)) {

            st.setString(1, guest.getNome());
            st.setString(2, guest.getSobrenome());
            st.setDate(3, new java.sql.Date(guest.getDataDeNascimento().getTime()));
            st.setString(4, guest.getNacionalidade());
            st.setString(5, guest.getTelefone());
            st.setInt(6, guest.getIdReserva());

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    guest.setId(rs.getInt(1));
                }
                DatabaseConnection.closeResultSet(rs);
            } else {
                throw new DbException("Erro inesperado! Nenhuma linha afetada!");
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public void update(Guest guest) {
        try (PreparedStatement st = con.prepareStatement(
                "UPDATE Hospedes SET Nome = ?, Sobrenome = ?, DataNascimento = ?, Nacionalidade = ?, Telefone = ? WHERE Id = ?")) {

            st.setString(1, guest.getNome());
            st.setString(2, guest.getSobrenome());
            st.setDate(3, new java.sql.Date(guest.getDataDeNascimento().getTime()));
            st.setString(4, guest.getNacionalidade());
            st.setString(5, guest.getTelefone());
            st.setInt(6, guest.getId());
           
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public List<Guest> findById(String id) {
        boolean isNumeric = isNumeric(id);
        String sql;

        if (isNumeric) {
            sql = "SELECT H.*, R.DataEntrada, R.DataSaida, R.Valor, R.FormaPagamento FROM Reservas R INNER JOIN Hospedes H "
                    + "ON R.Id = H.IdReserva "
                    + "WHERE IdReserva = ?";
        } else {
            sql = "SELECT H.*, R.DataEntrada, R.DataSaida, R.Valor, R.FormaPagamento FROM Reservas R INNER JOIN Hospedes H "
                    + "ON R.Id = H.IdReserva "
                    + "WHERE Sobrenome = ?";
        }

        try (PreparedStatement st = con.prepareStatement(sql)) {
            if (isNumeric) {
                st.setInt(1, Integer.parseInt(id));
            } else {
                st.setString(1, id);
            }
            ResultSet rs = st.executeQuery();

            List<Guest> list = new ArrayList<>();
            Map<Integer, Reservation> map = new HashMap<>();

            while (rs.next()) {

                Reservation reservation = map.get(rs.getInt("IdReserva"));

                if (reservation == null) {
                    reservation = instantiateReservation(rs);
                    map.put(rs.getInt("IdReserva"), reservation);
                }

                Guest guest = instantiateGuest(rs, reservation);
                list.add(guest);
            }
            DatabaseConnection.closeResultSet(rs);
            return list;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public List<Guest> findAll() {
        try (PreparedStatement st = con.prepareStatement("SELECT * FROM Hospedes")) {
            ResultSet rs = st.executeQuery();

            List<Guest> list = new ArrayList<>();
            Map<Integer, Reservation> map = new HashMap<>();

            while (rs.next()) {

                Reservation reservation = map.get(rs.getInt("IdReserva"));

                if (reservation == null) {
                    reservation = instantiateReservation(rs);
                    map.put(rs.getInt("IdReserva"), reservation);
                }

                Guest guest = instantiateGuest(rs, reservation);
                list.add(guest);
            }
            DatabaseConnection.closeResultSet(rs);
            return list;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    private static boolean isNumeric(String str) {
        if (str.replaceAll("\\s+", "").matches("\\d+")) {
            return true;
        }
        return false;
    }

    private Guest instantiateGuest(ResultSet rs, Reservation reservation) throws SQLException {
        Guest guest = new Guest();
        guest.setId(rs.getInt("Id"));
        guest.setNome(rs.getString("Nome"));
        guest.setSobrenome(rs.getString("Sobrenome"));
        guest.setDataDeNascimento(rs.getDate("DataNascimento"));
        guest.setNacionalidade(rs.getString("Nacionalidade"));
        guest.setTelefone(rs.getString("Telefone"));
        guest.setIdReserva(reservation.getId());
        return guest;
    }

    private Reservation instantiateReservation(ResultSet rs) throws SQLException {
        Reservation reservation = new Reservation();
        reservation.setId(rs.getInt("Id"));
        return reservation;
    }
}