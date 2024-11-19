using System;
using CalculatorService;  

namespace SoapCalculatorClient
{
    internal class Program
    {
        static async Task Main(string[] args)
        {
            try
            {
                // Crear instancia del cliente
                var calculatorClient = new CalculatorSoapClient(CalculatorSoapClient.EndpointConfiguration.CalculatorSoap);

                // Definir números para las operaciones
                int a = 9;
                int b = 36;

                // Realizar operaciones
                Console.WriteLine("Operaciones con la calculadora de SOAP");
                Console.WriteLine($"Números utilizados: {a} y {b}");
                Console.WriteLine();

                // Suma
                int resultadoSuma = await calculatorClient.AddAsync(a, b);
                Console.WriteLine($"Resultado de la suma: {resultadoSuma}");

                // Resta
                int resultadoResta = await calculatorClient.SubtractAsync(a, b);
                Console.WriteLine($"Resultado de la resta: {resultadoResta}");

                // Multiplicación
                int resultadoMultiplicacion = await calculatorClient.MultiplyAsync(a, b);
                Console.WriteLine($"Resultado de la multiplicación: {resultadoMultiplicacion}");

                // Cerrar el cliente
                await calculatorClient.CloseAsync();
            }
            catch (Exception ex)
            {
                Console.WriteLine($"Error al consumir el servicio: {ex.Message}");
            }

            Console.WriteLine("\nPresione cualquier tecla para salir...");
            Console.ReadKey();
        }
    }
}