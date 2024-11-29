from zeep import Client

# Crear cliente SOAP
client = Client("http://www.dneonline.com/calculator.asmx?WSDL")

try:
    # Llamar a la función Add
    result = client.service.Add(intA=6, intB=8)
    print("Resultado de la suma:", result)

    # Llamar a la función Subtract
    result = client.service.Subtract(intA=6, intB=8)
    print("Resultado de la resta:", result)

    # Llamar a la función Multiply
    result = client.service.Multiply(intA=6, intB=8)
    print("Resultado de la multiplicación:", result)

except Exception as e:
    print("Error:", e)
