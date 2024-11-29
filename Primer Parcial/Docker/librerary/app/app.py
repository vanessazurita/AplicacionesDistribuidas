from flask import Flask, jsonify, request
from flask_mysqldb import MySQL
import config

app = Flask(__name__)
app.config.from_object(config.Config)

mysql = MySQL(app)

# Endpoint para obtener todos los libros
@app.route('/books', methods=['GET'])
def get_books():
    cursor = mysql.connection.cursor()
    cursor.execute("SELECT id, title, author, year, country FROM books")
    books = cursor.fetchall()
    
    # Convertir cada libro a un diccionario
    books_list = []
    for book in books:
        books_list.append({
            "id": book[0],
            "title": book[1],
            "author": book[2],
            "year": book[3],
            "country": book[4]
        })
    
    return jsonify(books_list)

# Endpoint para agregar un libro
@app.route('/books', methods=['POST'])
def add_book():
    new_book = request.json
    title = new_book.get('title')
    author = new_book.get('author')
    year = new_book.get('year')
    country = new_book.get('country')

    cursor = mysql.connection.cursor()
    cursor.execute("INSERT INTO books (title, author, year, country) VALUES (%s, %s, %s, %s)", (title, author, year, country))
    mysql.connection.commit()

    return jsonify({"message": "Book added successfully!"}), 201

# Endpoint para modificar un libro
@app.route('/books/<int:id>', methods=['PUT'])
def update_book(id):
    updated_book = request.json
    title = updated_book.get('title')
    author = updated_book.get('author')
    year = updated_book.get('year')
    country = updated_book.get('country')

    cursor = mysql.connection.cursor()
    cursor.execute("UPDATE books SET title=%s, author=%s, year=%s, country=%s WHERE id=%s", (title, author, year, country, id))
    mysql.connection.commit()

    return jsonify({"message": "Book updated successfully!"})

# Endpoint para eliminar un libro
@app.route('/books/<int:id>', methods=['DELETE'])
def delete_book(id):
    cursor = mysql.connection.cursor()
    cursor.execute("DELETE FROM books WHERE id=%s", (id,))
    mysql.connection.commit()

    return jsonify({"message": "Book deleted successfully!"})

# Endpoint para consultar un solo libro por ID
@app.route('/books/<int:id>', methods=['GET'])
def get_book(id):
    cursor = mysql.connection.cursor()
    cursor.execute("SELECT id, title, author, year, country FROM books WHERE id = %s", (id,))
    book = cursor.fetchone()

    # Verifica si el libro existe
    if book:
        book_data = {
            "id": book[0],
            "title": book[1],
            "author": book[2],
            "year": book[3],
            "country": book[4]
        }
        return jsonify(book_data)
    else:
        return jsonify({"message": "Book not found"}), 404

if __name__ == "__main__":
    app.run(host="0.0.0.0", port=5000)
