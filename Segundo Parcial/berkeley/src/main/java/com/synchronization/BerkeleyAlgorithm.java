package com.synchronization;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BerkeleyAlgorithm {
    private List<ClockNode> nodes;

    public BerkeleyAlgorithm() {
        nodes = new ArrayList<>();
    }

    /**
     * Inicia la simulación con un número de nodos.
     *
     * @param nodeCount Número de nodos a crear.
     */
    public void startSimulation(int nodeCount) {
        Random random = new Random();
        long currentTimeMillis = System.currentTimeMillis();

        for (int i = 1; i <= nodeCount; i++) {
            // Generar un tiempo inicial aleatorio dentro de ±12 horas
            long offsetMillis = (random.nextInt(25) - 12) * 3600L * 1000L; // ±12 horas
            long initialTime = currentTimeMillis + offsetMillis;

            ClockNode node = new ClockNode(i, initialTime);
            nodes.add(node);
        }
    }

    /**
     * Sincroniza los relojes de los nodos usando el Algoritmo de Berkeley.
     */
    public void synchronizeClocks() {
        if (nodes.isEmpty()) return;

        long totalTime = 0;
        for (ClockNode node : nodes) {
            totalTime += node.getAdjustedTime();
        }

        long averageTime = totalTime / nodes.size();

        for (ClockNode node : nodes) {
            node.setAdjustedTime(averageTime);
        }
    }

    public List<ClockNode> getNodes() {
        return nodes;
    }
}
