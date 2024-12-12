import React, { useState } from 'react';
import './App.css';

const Login = ({ onRouteChange }) => {
  const [user, setUser] = useState('');
  const [password, setPassword] = useState('');
  const [securityCode, setSecurityCode] = useState('');

  const handleSubmit = (e) => {
    e.preventDefault();
    // Aquí iría la lógica de autenticación
  };

  return (
    <div className="login-container">
      <h2>Login</h2>
      <form onSubmit={handleSubmit}>
        <input
          type="text"
          placeholder="Usuario"
          value={user}
          onChange={(e) => setUser(e.target.value)}
        />
        <input
          type="password"
          placeholder="Contraseña"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />
        <input
          type="text"
          placeholder="Código de Seguridad"
          value={securityCode}
          onChange={(e) => setSecurityCode(e.target.value)}
        />
        <button type="submit">Iniciar Sesión</button>
      </form>
      <p>
        ¿No tienes una cuenta? <span onClick={() => onRouteChange('register')}>Regístrate</span>
      </p>
    </div>
  );
};

export default Login;