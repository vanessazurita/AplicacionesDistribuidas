# Usa una imagen base de Python
FROM python:3.9-slim

# Establece el directorio de trabajo en /app
WORKDIR /app

# Copia el código fuente de la aplicación en el contenedor
COPY app /app

# Instala las dependencias del sistema necesarias para mysqlclient
RUN apt-get update && \
    apt-get install -y default-libmysqlclient-dev gcc pkg-config && \
    rm -rf /var/lib/apt/lists/*

# Instala las dependencias de Python
RUN pip install flask flask-mysqldb

# Expone el puerto 5000
EXPOSE 5000

# Define el comando de inicio
CMD ["python", "app.py"]
