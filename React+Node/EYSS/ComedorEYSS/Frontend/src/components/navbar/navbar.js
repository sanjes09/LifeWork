import React, { Component } from "react";

export class Navbar extends Component {

    state = {
        correo: '',
    }

   

    handleChange = (e) => {
        this.setState({
            correo: e.target.value
        })
    }

    render() {
        return (
            <nav className="navbar navbar-expand-lg navbar-light bg-light position-absolute w-100">
                
                <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span className="navbar-toggler-icon"></span>
                </button>

                <div className="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul className="navbar-nav ml-auto">
                        <li className="nav-item mx-2">
                            <span className="nav-link" style={{cursor: 'pointer'}}>Descargar Reporte</span>
                        </li>
                        <li className="nav-item mx-2">
                            <span className="nav-link" style={{cursor: 'pointer'}} onClick={()=> {this.props.eliminar()}}>Eliminar Reserva</span>
                        </li>
                        <li className="nav-item dropdown mx-2">
                            <span className="nav-link dropdown-toggle" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style={{cursor: 'pointer'}}>
                            Agregar Espacio
                            </span>
                            <div className="dropdown-menu" aria-labelledby="navbarDropdown">
                                <span className="dropdown-item" style={{cursor: 'pointer'}} onClick={()=> {this.props.addEspacio('12')}}>12:00pm</span>
                                <span className="dropdown-item" style={{cursor: 'pointer'}} onClick={()=> {this.props.addEspacio('1230')}}>12:30pm</span>
                                <span className="dropdown-item" style={{cursor: 'pointer'}} onClick={()=> {this.props.addEspacio('1')}}>1:00pm</span>
                                <span className="dropdown-item" style={{cursor: 'pointer'}} onClick={()=> {this.props.addEspacio('145')}}>1:45pm</span>
                                <span className="dropdown-item" style={{cursor: 'pointer'}} onClick={()=> {this.props.addEspacio('2')}}>2:00pm</span>
                            </div>
                        </li>
                    </ul>
                    <div className="form-inline my-2 my-lg-0 mr-auto">
                        <input className="form-control mr-sm-2" type="text" id='correo' placeholder="Agregar nuevo correo" onChange={this.handleChange}/>
                        <button className="btn btn-outline-success my-2 my-sm-0" onClick={()=> {this.props.addCorreo(this.state.correo)}}>Agregar</button>
                    </div>
                </div>
            </nav>
        );
    }
}

export default Navbar;