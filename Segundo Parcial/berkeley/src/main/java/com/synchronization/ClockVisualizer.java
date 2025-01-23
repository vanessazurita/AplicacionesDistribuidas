package com.synchronization;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ClockVisualizer {
    private JFrame frame;
    private JPanel initialPanel;
    private JPanel synchronizedPanel;
    private JLabel headerLabel;

    public ClockVisualizer() {
        // Configuración de la ventana principal
        frame = new JFrame("Clock Synchronization - Berkeley Algorithm");
        frame.setLayout(new BorderLayout());
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Configurar el encabezado
        headerLabel = new JLabel("Clock Synchronization");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 18));
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerLabel.setOpaque(true);
        headerLabel.setBackground(new Color(70, 130, 180));
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Paneles para tiempos iniciales y sincronizados
        initialPanel = new JPanel(new GridLayout(1, 0, 10, 10));
        initialPanel.setBorder(BorderFactory.createTitledBorder("Initial Times"));

        synchronizedPanel = new JPanel(new GridLayout(1, 0, 10, 10));
        synchronizedPanel.setBorder(BorderFactory.createTitledBorder("Synchronized Times"));

        // Añadir componentes a la ventana
        frame.add(headerLabel, BorderLayout.NORTH);

        JPanel contentPanel = new JPanel(new GridLayout(2, 1));
        contentPanel.add(initialPanel);
        contentPanel.add(synchronizedPanel);

        frame.add(contentPanel, BorderLayout.CENTER);
    }

    /**
     * Muestra la ventana gráfica.
     */
    public void show() {
        frame.setVisible(true);
    }

    /**
     * Actualiza los relojes iniciales en la interfaz.
     *
     * @param nodes Lista de nodos con tiempos iniciales.
     */
    public void updateInitialClocks(List<ClockNode> nodes) {
        initialPanel.removeAll();
        for (ClockNode node : nodes) {
            initialPanel.add(createClockPanel(node));
        }
        initialPanel.revalidate();
        initialPanel.repaint();
    }

    /**
     * Actualiza los relojes sincronizados en la interfaz.
     *
     * @param nodes Lista de nodos con tiempos sincronizados.
     */
    public void updateSynchronizedClocks(List<ClockNode> nodes) {
        synchronizedPanel.removeAll();
        for (ClockNode node : nodes) {
            synchronizedPanel.add(createClockPanel(node));
        }
        synchronizedPanel.revalidate();
        synchronizedPanel.repaint();
    }

    /**
     * Crea un panel con un gráfico de reloj.
     *
     * @param node Nodo con el tiempo a mostrar.
     * @return Panel con el gráfico del reloj.
     */
    private JPanel createClockPanel(ClockNode node) {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel("Node " + node.getId(), SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 14));

        ClockGraphic clockGraphic = new ClockGraphic(node.getAdjustedTime());
        panel.add(label, BorderLayout.NORTH);
        panel.add(clockGraphic, BorderLayout.CENTER);

        return panel;
    }
}
