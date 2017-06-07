/*eslint-disable*/
import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';
import "antd/dist/antd.css";
import registerServiceWorker from './registerServiceWorker';
import './index.css';

ReactDOM.render(
    <div style={{width: '100%', height: '400px'}}>
    <App />
    </div>, 
    document.getElementById('root')
);

