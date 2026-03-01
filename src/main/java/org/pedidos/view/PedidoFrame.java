package org.pedidos.view;

import org.pedidos.Main;
import org.pedidos.model.Pedido;
import org.pedidos.model.Repartidor;
import org.pedidos.dao.PedidoDAO;
import org.pedidos.dao.RepartidorDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PedidoFrame extends JFrame {

    private JTextField txtTipo;
    private JTextField txtEstado;
    private JComboBox<Repartidor> comboRepartidor;
    private JTable tabla;
    private DefaultTableModel modelo;

    private PedidoDAO pedidoDAO;
    private RepartidorDAO repartidorDAO;
    private Main menu;

    public PedidoFrame(Main menu) {

        this.menu = menu;

        pedidoDAO = new PedidoDAO();
        repartidorDAO = new RepartidorDAO();

        setTitle("Gestión de Pedidos");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        panel.add(new JLabel("Tipo:"));
        txtTipo = new JTextField(10);
        panel.add(txtTipo);

        panel.add(new JLabel("Estado:"));
        txtEstado = new JTextField(10);
        panel.add(txtEstado);

        panel.add(new JLabel("Repartidor:"));
        comboRepartidor = new JComboBox<>();
        panel.add(comboRepartidor);

        JButton btnAgregar = new JButton("Agregar");
        JButton btnActualizar = new JButton("Actualizar");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnVolver = new JButton("Volver al menú");

        panel.add(btnAgregar);
        panel.add(btnActualizar);
        panel.add(btnEliminar);
        panel.add(btnVolver);

        add(panel, BorderLayout.NORTH);

        // Table
        modelo = new DefaultTableModel(
                new Object[]{"ID", "Tipo", "Estado", "Repartidor ID"}, 0
        );

        tabla = new JTable(modelo);
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        btnAgregar.addActionListener(e -> agregar());
        btnActualizar.addActionListener(e -> actualizar());
        btnEliminar.addActionListener(e -> eliminar());

        btnVolver.addActionListener(e -> {
            this.dispose();
            menu.volverAlMenu();
        });

        cargarRepartidores();
        cargarTabla();
    }

    // Carga de repartidores
    private void cargarRepartidores() {

        comboRepartidor.removeAllItems();
        List<Repartidor> lista = repartidorDAO.listarTodos();

        for (Repartidor r : lista) {
            comboRepartidor.addItem(r);
        }
    }

    //Para agregar
    private void agregar() {

        String tipo = txtTipo.getText().trim();
        String estado = txtEstado.getText().trim();
        Repartidor repartidor = (Repartidor) comboRepartidor.getSelectedItem();

        if (tipo.isEmpty() || estado.isEmpty() || repartidor == null) {
            JOptionPane.showMessageDialog(this, "Complete todos los campos");
            return;
        }

        Pedido pedido = new Pedido(tipo, estado, repartidor.getId());
        pedidoDAO.crear(pedido);

        txtTipo.setText("");
        txtEstado.setText("");

        cargarTabla();
    }

    // Para actualizar
    private void actualizar() {

        int fila = tabla.getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un pedido");
            return;
        }

        String tipo = txtTipo.getText().trim();
        String estado = txtEstado.getText().trim();
        Repartidor repartidor = (Repartidor) comboRepartidor.getSelectedItem();

        if (tipo.isEmpty() || estado.isEmpty() || repartidor == null) {
            JOptionPane.showMessageDialog(this, "Complete todos los campos");
            return;
        }

        int id = (int) modelo.getValueAt(fila, 0);

        Pedido pedido = new Pedido(id, tipo, estado, repartidor.getId());
        pedidoDAO.actualizar(pedido);

        txtTipo.setText("");
        txtEstado.setText("");

        cargarTabla();
    }

    // Para eliminar
    private void eliminar() {

        int fila = tabla.getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un pedido");
            return;
        }

        int id = (int) modelo.getValueAt(fila, 0);

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "¿Eliminar pedido?",
                "Confirmar",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            pedidoDAO.eliminar(id);
            cargarTabla();
        }
    }

    private void cargarTabla() {

        modelo.setRowCount(0);

        List<Pedido> lista = pedidoDAO.listarTodos();

        for (Pedido p : lista) {
            modelo.addRow(new Object[]{
                    p.getId(),
                    p.getTipo(),
                    p.getEstado(),
                    p.getRepartidorId()
            });
        }
    }
}