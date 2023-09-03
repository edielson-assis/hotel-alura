package com.edielson.dao.ipml;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.edielson.connection.DatabaseConnection;
import com.edielson.connection.DbException;
import com.edielson.connection.DbIntegrityException;
import com.edielson.dao.ReservationDao;
import com.edielson.model.Reservation;

public class ReservationDaoJDBC implements ReservationDao {

    private Connection con;

    public ReservationDaoJDBC(Connection con) {
        this.con = con;
    }

    @Override
    public void save(Reservation reservation) {
        try (PreparedStatement st = con.prepareStatement(
                "INSERT INTO Reservas (DataEntrada, DataSaida, Valor, FormaPagamento) VALUES (?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS)) {

            st.setDate(1, new java.sql.Date(reservation.getDataEntrada().getTime()));
            st.setDate(2, new java.sql.Date(reservation.getDataSaida().getTime()));
            st.setBigDecimal(3, reservation.getValor());
            st.setString(4, reservation.getFormaPagamento());

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    reservation.setId(rs.getInt(1));
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
    public void update(Reservation reservation) {
        try (PreparedStatement st = con.prepareStatement(
                "UPDATE Reservas SET DataEntrada = ?, DataSaida = ?, Valor = ?, FormaPagamento = ? WHERE Id = ?")) {

            st.setDate(1, new java.sql.Date(reservation.getDataEntrada().getTime()));
            st.setDate(2, new java.sql.Date(reservation.getDataSaida().getTime()));
            st.setBigDecimal(3, reservation.getValor());
            st.setString(4, reservation.getFormaPagamento());
            st.setInt(5, reservation.getId());
            
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public void deleteById(Integer id) {
        try (PreparedStatement st = con.prepareStatement("DELETE FROM Reservas WHERE Id = ?")) {
            st.setInt(1, id);

            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbIntegrityException(e.getMessage());
        }
    }

    @Override
    public List<Reservation> findById(String id) {
        boolean isNumeric = isNumeric(id);
        String sql;

        if (isNumeric) {
            sql = "SELECT * FROM Reservas WHERE Id = ?";
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

            List<Reservation> reservations = new ArrayList<>();

            while (rs.next()) {
                Reservation reservation = instantiateReservation(rs);
                reservations.add(reservation);
            }
            DatabaseConnection.closeResultSet(rs);
            return reservations;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public List<Reservation> findAll() {
        try (PreparedStatement st = con.prepareStatement("SELECT * FROM Reservas")) {
            ResultSet rs = st.executeQuery();

            List<Reservation> reservations = new ArrayList<>();

            while (rs.next()) {
                Reservation reservation = instantiateReservation(rs);
                reservations.add(reservation);
            }
            DatabaseConnection.closeResultSet(rs);
            return reservations;
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

    private Reservation instantiateReservation(ResultSet rs) throws SQLException {
        Reservation reservation = new Reservation();
        reservation.setId(rs.getInt("Id"));
        reservation.setDataEntrada(rs.getDate("DataEntrada"));
        reservation.setDataSaida(rs.getDate("DataSaida"));
        reservation.setValor(rs.getBigDecimal("Valor"));
        reservation.setFormaPagamento(rs.getString("FormaPagamento"));
        return reservation;
    }
}