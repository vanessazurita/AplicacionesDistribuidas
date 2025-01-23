import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TimeSynchronizer {
    private final NTPClient ntpClient;
    
    public TimeSynchronizer() {
        this.ntpClient = new NTPClient();
    }
    
    public LocalDateTime synchronizeTime(ZoneId zoneId) throws Exception {
        long networkTime = ntpClient.getCurrentNetworkTime();
        LocalDateTime dateTime = LocalDateTime.ofInstant(
            Instant.ofEpochMilli(networkTime),
            zoneId
        );
        
        try {
            String os = System.getProperty("os.name").toLowerCase();
            Process process;
            
            if (os.contains("win")) {
                // Iniciar el servicio de hora de Windows
                ProcessBuilder pbService = new ProcessBuilder(
                    "cmd",
                    "/c",
                    "net start w32time"
                );
                process = pbService.start();
                process.waitFor();
                
                // Establecer la fecha
                String dateCommand = String.format(
                    "cmd /c date %s",
                    dateTime.format(DateTimeFormatter.ofPattern("dd-MM-yy"))
                );
                process = Runtime.getRuntime().exec(dateCommand);
                process.waitFor();
                
                // Establecer la hora
                String timeCommand = String.format(
                    "cmd /c time %s",
                    dateTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"))
                );
                process = Runtime.getRuntime().exec(timeCommand);
                
                // Leer la respuesta del comando
                BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream())
                );
                String line;
                StringBuilder output = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    output.append(line).append("\n");
                    System.out.println("Salida del comando: " + line);
                }
                
                int exitCode = process.waitFor();
                if (exitCode != 0) {
                    throw new Exception("Error al ejecutar el comando: " + output.toString());
                }
                
            } else {
                // Para Linux/Mac
                String dateCmd = String.format("date -s \"%s\"",
                    dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                process = Runtime.getRuntime().exec(new String[]{"sudo", "-S", dateCmd});
                process.waitFor();
            }
            
        } catch (Exception e) {
            System.err.println("Error detallado: " + e.toString());
            e.printStackTrace();
            throw new Exception("Error al cambiar la hora del sistema. " +
                "Asegúrate de ejecutar como administrador: " + e.getMessage());
        }
        
        return dateTime;
    }
}