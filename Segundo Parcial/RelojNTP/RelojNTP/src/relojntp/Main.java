public class Main {
    public static void main(String[] args) {
        // Asegurarse de que la interfaz gráfica se ejecute en el Event Dispatch Thread
        javax.swing.SwingUtilities.invokeLater(() -> {
            try {
                // Intentar establecer el Look and Feel del sistema
                javax.swing.UIManager.setLookAndFeel(
                    javax.swing.UIManager.getSystemLookAndFeelClassName()
                );
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            // Crear y mostrar la ventana principal
            TimeZoneSelector selector = new TimeZoneSelector();
            selector.setVisible(true);
        });
    }
}