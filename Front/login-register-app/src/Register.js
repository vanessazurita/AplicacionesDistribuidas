import React, { useState } from 'react';
import './App.css';

const Register = ({ onRouteChange }) => {
  const [formData, setFormData] = useState({
    nombre: '',
    apellido: '',
    genero: '',
    estadoCivil: 'Soltero',
    ocupacion: '',
    telefono: '',
    email: '',
    usuario: '',
    contraseña: ''
  });

  const [errors, setErrors] = useState({});

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });

    // Validaciones
    let error = '';
    if (name === 'nombre' || name === 'apellido') {
      if (!/^[a-zA-Z\s]{1,12}$/.test(value)) {
        error = 'Solo letras y máximo 12 caracteres';
      }
    } else if (name === 'ocupacion') {
      if (!/^[a-zA-Z\s]{1,30}$/.test(value)) {
        error = 'Solo letras y máximo 30 caracteres';
      }
    } else if (name === 'telefono') {
      if (!/^\d{10}$/.test(value)) {
        error = 'Debe ser un número de 10 dígitos';
      }
    } else if (name === 'email') {
      if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(value)) {
        error = 'Email no válido';
      }
    } else if (name === 'usuario') {
      if (!/^[a-zA-Z]{1,15}$/.test(value)) {
        error = 'Solo letras y máximo 15 caracteres';
      }
    } else if (name === 'contraseña') {
      if (!/^[a-zA-Z0-9*#,.\s]{1,15}$/.test(value)) {
        error = 'Máximo 15 caracteres y solo * # , . permitidos';
      }
    }

    setErrors({ ...errors, [name]: error });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    if (Object.values(errors).every((error) => error === '') && Object.values(formData).every((value) => value !== '')) {
      alert('Se ha enviado un código de seguridad al correo electrónico registrado');
      onRouteChange('login');
    } else {
      alert('Por favor, corrige los errores en el formulario');
    }
  };

  return (
    <div className="register-container">
      <h2>Registro</h2>
      <form onSubmit={handleSubmit}>
        <input
          type="text"
          name="nombre"
          placeholder="Nombre"
          value={formData.nombre}
          onChange={handleChange}
          className={errors.nombre ? 'error' : 'success'}
        />
        {errors.nombre && <span className="error-text">{errors.nombre}</span>}
        <input
          type="text"
          name="apellido"
          placeholder="Apellido"
          value={formData.apellido}
          onChange={handleChange}
          className={errors.apellido ? 'error' : 'success'}
        />
        {errors.apellido && <span className="error-text">{errors.apellido}</span>}
        <div className="radio-group">
          <label>
            <input
              type="radio"
              name="genero"
              value="Hombre"
              checked={formData.genero === 'Hombre'}
              onChange={handleChange}
            />
            Hombre
          </label>
          <label>
            <input
              type="radio"
              name="genero"
              value="Mujer"
              checked={formData.genero === 'Mujer'}
              onChange={handleChange}
            />
            Mujer
          </label>
          <label>
            <input
              type="radio"
              name="genero"
              value="Otros"
              checked={formData.genero === 'Otros'}
              onChange={handleChange}
            />
            Otros
          </label>
        </div>
        <div className="radio-group">
          <label>
            <input
              type="radio"
              name="estadoCivil"
              value="Soltero"
              checked={formData.estadoCivil === 'Soltero'}
              onChange={handleChange}
            />
            Soltero
          </label>
          <label>
            <input
              type="radio"
              name="estadoCivil"
              value="Casado"
              checked={formData.estadoCivil === 'Casado'}
              onChange={handleChange}
            />
            Casado
          </label>
          <label>
            <input
              type="radio"
              name="estadoCivil"
              value="Divorciado"
              checked={formData.estadoCivil === 'Divorciado'}
              onChange={handleChange}
            />
            Divorciado
          </label>
          <label>
            <input
              type="radio"
              name="estadoCivil"
              value="Viudo"
              checked={formData.estadoCivil === 'Viudo'}
              onChange={handleChange}
            />
            Viudo
          </label>
        </div>
        <input
          type="text"
          name="ocupacion"
          placeholder="Ocupación"
          value={formData.ocupacion}
          onChange={handleChange}
          className={errors.ocupacion ? 'error' : 'success'}
        />
        {errors.ocupacion && <span className="error-text">{errors.ocupacion}</span>}
        <input
          type="text"
          name="telefono"
          placeholder="Teléfono"
          value={formData.telefono}
          onChange={handleChange}
          className={errors.telefono ? 'error' : 'success'}
        />
        {errors.telefono && <span className="error-text">{errors.telefono}</span>}
        <input
          type="email"
          name="email"
          placeholder="Email"
          value={formData.email}
          onChange={handleChange}
          className={errors.email ? 'error' : 'success'}
        />
        {errors.email && <span className="error-text">{errors.email}</span>}
        <input
          type="text"
          name="usuario"
          placeholder="Usuario"
          value={formData.usuario}
          onChange={handleChange}
          className={errors.usuario ? 'error' : 'success'}
        />
        {errors.usuario && <span className="error-text">{errors.usuario}</span>}
        <input
          type="password"
          name="contraseña"
          placeholder="Contraseña"
          value={formData.contraseña}
          onChange={handleChange}
          className={errors.contraseña ? 'error' : 'success'}
        />
        {errors.contraseña && <span className="error-text">{errors.contraseña}</span>}
        <span className="info-text">La extensión máxima de la contraseña será de 15 caracteres y los únicos caracteres especiales que se pueden usar son * # , .</span>
        <button type="submit">Registrarse</button>
      </form>
    </div>
  );
};

export default Register;