package com.synchronization;

public class Main {
    public static void main(String[] args) {
        // Crear simulación y visualización
        BerkeleyAlgorithm simulation = new BerkeleyAlgorithm();
        ClockVisualizer visualizer = new ClockVisualizer();

        // Mostrar la interfaz gráfica
        visualizer.show();

        // Iniciar simulación
        simulation.startSimulation(5);

        // Mostrar tiempos iniciales
        visualizer.updateInitialClocks(simulation.getNodes());

        // Sincronizar relojes
        simulation.synchronizeClocks();

        // Mostrar tiempos sincronizados
        visualizer.updateSynchronizedClocks(simulation.getNodes());
    }
}
