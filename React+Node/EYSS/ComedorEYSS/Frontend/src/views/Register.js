import React, { Component } from "react";
import Axios from "axios";
import toastr from 'toastr';
import logo from "../resource/logo.png";

export class Register extends Component {

  state = {
    nombre: '',
    usuario: '',
    correo: '',
    password: '',
  };

  handleSubmit = (e) => {
    e.preventDefault();
    if(this.state.correo.indexOf('@') !== -1){
      let host = this.state.correo.split('@');
      if(host[1] === 'eyss.io'){
        if(this.state.nombre !== '' && this.state.usuario !== '' && this.state.correo !== '' && this.state.password !== ''){
          if(this.state.usuario.indexOf(' ') === -1){
            const data = {
              nombre: this.state.nombre,
              usuario: this.state.usuario,
              correo: this.state.correo,
              password: this.state.password,
            }
            Axios.post("/signup", data).then(response => {
              if(response.data.ok){
                toastr.success('Registro exitoso!')
                setTimeout(() => {
                  window.location = "/login"
                }, 2000);
              }else{
                toastr.warning(response.data.error)
              }
            });
          }else{
            toastr.warning("El usuario no puede tener espacios");
          }
        }else{
          toastr.warning("No pueden haber campos vacios");
        }
      }else{
        toastr.warning("Correo no válido, solo puede ser @eyss.io");
      }
    }else{
      toastr.warning("Correo no válido")
    }
  }
  
  handleChange = (e) => {
    this.setState({
      [e.target.name]: e.target.value
    })
  }

  render() {
    return (
      <div className="container-fluid fondo">
        <div className="row">
          <div className="mx-auto reg-card">
            <div style={{maxHeight:"100%"}}> 
              <div className="row mb-3">
                  <img className="mx-auto" alt='logo' src={logo} style={{height: "75px"}}/>
              </div>
              <div className="row mb-3">
                  <span className="mx-auto text-center w-75" style={{fontSize:"0.7rem"}}>Recuerda marcar tu dedo en el control de acceso y anotarte en la lista del break al llegar para almozar en el horario de tu preferencia.</span>
              </div>
              <form onSubmit={this.handleSubmit}>
                <div className="row my-3">
                  <input onChange={this.handleChange} className="mx-auto w-75 my-2 login-input form-control" type="text" name="nombre" placeholder="Nombre y Apellido"/>
                  <input onChange={this.handleChange} className="mx-auto w-75 my-2 login-input form-control" type="text" name="usuario" placeholder="Usuario"/>
                  <input onChange={this.handleChange} className="mx-auto w-75 my-2 login-input form-control" type="text" name="correo" placeholder="Correo electronico"/>
                  <input onChange={this.handleChange} className="mx-auto w-75 my-2 login-input form-control" type="password" name="password" placeholder="Contraseña"/>
                </div>
                <div className="row my-2">
                  <button className="col-4 btn btn-md mx-auto mr-2" type="submit" style={{borderRadius:"7px", fontSize:"0.7rem", borderColor:"#20C178", color:"#20C178"}}>Crear Cuenta</button>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

export default Register;