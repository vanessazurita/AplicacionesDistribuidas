import javax.swing.*;
import java.awt.*;
import java.time.ZoneId;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TimeZoneSelector extends JFrame {
    private final TimeSynchronizer synchronizer;
    private final JComboBox<String> timeZoneComboBox;
    private final JLabel currentTimeLabel;
    private Timer updateTimer;
    
    public TimeZoneSelector() {
        synchronizer = new TimeSynchronizer();
        
        setTitle("Selector de Zona Horaria");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        
        // Panel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Crear lista ordenada de zonas horarias
        List<String> zoneIds = new ArrayList<>(ZoneId.getAvailableZoneIds());
        Collections.sort(zoneIds);
        
        // Componentes de la interfaz
        timeZoneComboBox = new JComboBox<>(zoneIds.toArray(new String[0]));
        currentTimeLabel = new JLabel("Hora actual: ");
        JButton syncButton = new JButton("Sincronizar");
        
        // Configurar el ComboBox
        timeZoneComboBox.setMaximumSize(new Dimension(400, 30));
        timeZoneComboBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Configurar el botón
        syncButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        syncButton.setMaximumSize(new Dimension(200, 30));
        
        // Etiqueta de advertencia
        JLabel adminLabel = new JLabel("* Requiere permisos de administrador");
        adminLabel.setForeground(Color.RED);
        adminLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Añadir componentes al panel
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(new JLabel("Seleccione zona horaria:"));
        mainPanel.add(Box.createVerticalStrut(5));
        mainPanel.add(timeZoneComboBox);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(syncButton);
        mainPanel.add(Box.createVerticalStrut(5));
        mainPanel.add(adminLabel);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(currentTimeLabel);
        
        // Añadir el panel principal al frame
        add(mainPanel, BorderLayout.CENTER);
        
        // Configurar el timer para actualizar la hora actual
        updateTimer = new Timer(1000, e -> updateCurrentTime());
        updateTimer.start();
        
        // Configurar el evento del botón de sincronización
        syncButton.addActionListener(e -> synchronizeTime());
        
        // Configurar el tamaño y posición de la ventana
        setPreferredSize(new Dimension(450, 250));
        pack();
        setLocationRelativeTo(null);
    }
    
    private void updateCurrentTime() {
        String selectedZone = (String) timeZoneComboBox.getSelectedItem();
        if (selectedZone != null) {
            ZoneId zoneId = ZoneId.of(selectedZone);
            LocalDateTime now = LocalDateTime.now(zoneId);
            currentTimeLabel.setText("Hora actual: " + 
                now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        }
    }
    
    private void synchronizeTime() {
        try {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            String selectedZone = (String) timeZoneComboBox.getSelectedItem();
            ZoneId zoneId = ZoneId.of(selectedZone);
            LocalDateTime syncedTime = synchronizer.synchronizeTime(zoneId);
            
            JOptionPane.showMessageDialog(this,
                "Hora sincronizada exitosamente a: \n" + 
                syncedTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                "Éxito",
                JOptionPane.INFORMATION_MESSAGE);
                
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Error al sincronizar: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }
    
    @Override
    public void dispose() {
        updateTimer.stop();
        super.dispose();
    }
}