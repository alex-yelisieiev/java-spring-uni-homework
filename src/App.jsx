import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import HomePage from './components/HomePage';
import './styles/HomePage.css';

function App() {
    return (
        <Router>
            <div className="app">
                <Routes>
                    <Route path="/" element={<HomePage />} />
                    {/* ...existing routes... */}
                </Routes>
            </div>
        </Router>
    );
}

export default App;
