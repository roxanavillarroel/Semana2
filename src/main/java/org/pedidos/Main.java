package org.pedidos;

import org.pedidos.view.RepartidorFrame;
import org.pedidos.view.PedidoFrame;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

    public Main() {

        setTitle("Bienvenidos al portal Speed Fast");
        setSize(450, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout(FlowLayout.CENTER, 20, 40));

        JButton btnRepartidores = new JButton("Gestionar Repartidores");
        JButton btnPedidos = new JButton("Gestionar Pedidos");

        add(btnRepartidores);
        add(btnPedidos);

        btnRepartidores.addActionListener(e -> {
            this.setVisible(false); // Oculta menú
            new RepartidorFrame(this).setVisible(true);
        });

        btnPedidos.addActionListener(e -> {
            this.setVisible(false);
            new PedidoFrame(this).setVisible(true);
        });
    }

    public void volverAlMenu() {
        this.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->
                new Main().setVisible(true));
    }
}