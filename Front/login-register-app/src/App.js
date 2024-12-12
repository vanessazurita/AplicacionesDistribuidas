import React, { useState } from 'react';
import Login from './Login';
import Register from './Register';
import './App.css';

const App = () => {
  const [route, setRoute] = useState('login');

  const onRouteChange = (route) => {
    setRoute(route);
  };

  return (
    <div className="App">
      {route === 'login' ? <Login onRouteChange={onRouteChange} /> : <Register onRouteChange={onRouteChange} />}
    </div>
  );
};

export default App;