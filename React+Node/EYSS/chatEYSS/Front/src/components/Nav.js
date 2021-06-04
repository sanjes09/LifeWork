import React, {Component} from "react";

class Nav extends Component {

    render(){
        return(
            <nav className="navbar navbar-light bg-dark p-0">
                
                <ul className="navbar-nav mr-auto">
                    <li className="nav-item active">
                        <img src="/logo.png" className="" alt="logo" width="80px"/>
                    </li>
                </ul>
                <ul className="navbar-nav ml-auto">
                    <li className="nav-item active">
                        <button className="btn btn-sm text-light" onClick={() => {this.props.logout()}}>Logout</button>
                    </li>
                </ul>
            </nav>
        )
    }
}

export default Nav;