package org.pedidos.dao;

import org.pedidos.model.Pedido;
import util.ConexionDB;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {

    public void crear(Pedido pedido) {

        String sql = "INSERT INTO pedidos (tipo, estado, repartidor_id) VALUES (?, ?, ?)";

        try (Connection con = ConexionDB.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, pedido.getTipo());
            ps.setString(2, pedido.getEstado());
            ps.setInt(3, pedido.getRepartidorId());
            ps.executeUpdate();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error al crear pedido:\n" + e.getMessage());
        }
    }

    public List<Pedido> listarTodos() {

        List<Pedido> lista = new ArrayList<>();
        String sql = "SELECT * FROM pedidos";

        try (Connection con = ConexionDB.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new Pedido(
                        rs.getInt("id"),
                        rs.getString("tipo"),
                        rs.getString("estado"),
                        rs.getInt("repartidor_id")
                ));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error al listar pedidos:\n" + e.getMessage());
        }

        return lista;
    }

    public void actualizar(Pedido pedido) {

        String sql = "UPDATE pedidos SET tipo=?, estado=?, repartidor_id=? WHERE id=?";

        try (Connection con = ConexionDB.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, pedido.getTipo());
            ps.setString(2, pedido.getEstado());
            ps.setInt(3, pedido.getRepartidorId());
            ps.setInt(4, pedido.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error al actualizar pedido:\n" + e.getMessage());
        }
    }

    public void eliminar(int id) {

        String sql = "DELETE FROM pedidos WHERE id = ?";

        try (Connection con = ConexionDB.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error al eliminar pedido:\n" + e.getMessage());
        }
    }
}