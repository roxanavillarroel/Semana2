package org.pedidos.view;

import org.pedidos.Main;
import org.pedidos.model.Repartidor;
import org.pedidos.dao.RepartidorDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class RepartidorFrame extends JFrame {

    private JTextField txtNombre;
    private JTable tabla;
    private DefaultTableModel modelo;
    private RepartidorDAO dao;
    private Main menu;

    public RepartidorFrame(Main menu) {

        this.menu = menu;
        dao = new RepartidorDAO();

        setTitle("Gestión de Repartidores");
        setSize(750, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());


        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        panel.add(new JLabel("Nombre:"));

        txtNombre = new JTextField(15);
        panel.add(txtNombre);

        JButton btnAgregar = new JButton("Agregar");
        JButton btnActualizar = new JButton("Actualizar");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnVolver = new JButton("Volver al menú");

        panel.add(btnAgregar);
        panel.add(btnActualizar);
        panel.add(btnEliminar);
        panel.add(btnVolver);

        add(panel, BorderLayout.NORTH);

        modelo = new DefaultTableModel(new Object[]{"ID", "Nombre"}, 0);
        tabla = new JTable(modelo);
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        btnAgregar.addActionListener(e -> agregar());
        btnActualizar.addActionListener(e -> actualizar());
        btnEliminar.addActionListener(e -> eliminar());

        btnVolver.addActionListener(e -> {
            this.dispose();
            menu.volverAlMenu();
        });

        cargarTabla();
    }


    private void agregar() {

        String nombre = txtNombre.getText().trim();

        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe ingresar un nombre");
            return;
        }

        dao.crear(new Repartidor(nombre));
        txtNombre.setText("");
        cargarTabla();
    }

    private void actualizar() {

        int fila = tabla.getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un repartidor");
            return;
        }

        String nombre = txtNombre.getText().trim();

        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el nuevo nombre");
            return;
        }

        int id = (int) modelo.getValueAt(fila, 0);

        dao.actualizar(new Repartidor(id, nombre));
        txtNombre.setText("");
        cargarTabla();
    }

    private void eliminar() {

        int fila = tabla.getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un repartidor");
            return;
        }

        int id = (int) modelo.getValueAt(fila, 0);

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "¿Eliminar repartidor?",
                "Confirmar",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            dao.eliminar(id);
            cargarTabla();
        }
    }

    private void cargarTabla() {

        modelo.setRowCount(0);

        List<Repartidor> lista = dao.listarTodos();

        for (Repartidor r : lista) {
            modelo.addRow(new Object[]{r.getId(), r.getNombre()});
        }
    }
}