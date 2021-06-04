import React, { Component } from "react";
import Axios from "axios";
import toastr from 'toastr';
import logo from "../resource/logo.png";

export class Login extends Component {

  state = {
    usuario: '',
    password: '',
  };

  handleLogin = (e) => {
    e.preventDefault();
      if(this.state.usuario !== '' && this.state.password !== ''){
        const data = {
          usuario: this.state.usuario,
          password: this.state.password,
        }
        Axios.post("/login", data).then(response => {
          if(response.data.ok){
            toastr.success('Sesion iniciada!')
            setTimeout(() => {
              window.location = "/dashboard"
            }, 2000);
          }else{
            toastr.warning(response.data.error)
          }
        });
      }else{
        toastr.warning("No pueden haber campos vacios");
      }
  }

  handleChange = (e) => {
    this.setState({
      [e.target.name]: e.target.value
    })
  }

  handleRegister = () => {
    window.location = "/register"
  }

  render() {
    return (
      <div className="container-fluid fondo">
        <div className="row">
          <div className="mx-auto login-card">
            <div style={{maxHeight:"100%"}}> 
              <div className="row mb-3">
                  <img className="mx-auto" alt='logo' src={logo} style={{height: "75px"}}/>
              </div>
              <div className="row mb-3">
                  <span className="mx-auto text-center w-75" style={{fontSize:"0.7rem"}}>Recuerda marcar tu dedo en el control de acceso y anotarte en la lista del break al llegar para almozar en el horario de tu preferencia.</span>
              </div>
              <form onSubmit={this.handleLogin} id="formulario">
                <div className="row my-3">
                  <input onChange={this.handleChange} className="mx-auto w-75 my-2 login-input form-control" type='text' name="usuario" placeholder="Usuario"/>
                  <input onChange={this.handleChange} className="mx-auto w-75 my-2 login-input form-control" type='password' name="password" placeholder="Contraseña"/>
                </div>
              </form>
                <div className="row my-2">
                  <button onClick={this.handleRegister} className="col-4 btn btn-sm ml-auto mr-2" style={{borderRadius:"7px", fontSize:"0.7rem", borderColor:"#20C178", color:"#20C178"}}>Crear Cuenta</button>
                  <button form="formulario" className="col-4 btn btn-sm mr-auto ml-2" type="submit" style={{borderRadius:"7px", fontSize:"0.7rem", backgroundColor:"#20C178", color:"#fff"}}>Ingresar</button>
                </div>
                <div className="row mt-3">
                  <a href="/#" className="mx-auto" style={{fontSize:"0.7rem", color:"#20C178"}}>¿Olvidaste la contraseña?</a>
                </div>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

export default Login;