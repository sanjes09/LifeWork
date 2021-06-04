import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import Comedor from './Comedor';
import * as serviceWorker from './serviceWorker';

ReactDOM.render(<Comedor />, document.getElementById('root'));

serviceWorker.unregister();
