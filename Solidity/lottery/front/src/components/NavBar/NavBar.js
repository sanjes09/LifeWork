import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import { Nav } from 'react-bootstrap';
import './navbar.css';

export class NavBar extends Component {

	render() {
		return (
			<Nav className="justify-content-end align-items-center NavBar" style={{height: '75px'}}>
				<Nav.Item>
					<Link className={`superCenter text-center nav-link ${this.props.home ? 'active' : ''}`} to="/" style={{height: '75px'}}>Home</Link>
				</Nav.Item>
			</Nav>  
		)
	}
}

export default NavBar;
