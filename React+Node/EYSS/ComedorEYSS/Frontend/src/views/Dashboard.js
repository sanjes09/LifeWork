import React, { Component } from "react";
import logo from "../resource/logo.png";
import Axios from "axios";
import toastr from 'toastr';
import Navbar from '../components/navbar/navbar'

export class Dashboard extends Component {

  state = {
    fecha: '',
    dia: '',
    mes: '',
    anio: '',
    diaString: '',
    mesString: '',
    horario: '',
    estoy: false,
    usuario: "",
    admin: false,
    eliminar: false
  };

  eliminar = () => {
    let x = this.state.horario;
    var nuevo = x["reserva"+this.state.selected[0]].map((puesto) => {
      if(puesto.posicion === this.state.selected[1]){
        puesto.usuario = "";
      }
      return puesto;
    });
    x["reserva"+this.state.selected[0]] = nuevo;
    this.setState({
      horario: x
    })
  }

  handleSelect = (hora,posicion) => {
    if(!this.state.estoy){
      if(this.state.selected){
        this.eliminar();
      }
      this.setState({
        selected: [hora,posicion]
      })
      let x = this.state.horario;
      var nuevo = x["reserva"+hora].map((puesto) => {
        if(puesto.posicion === posicion){
          puesto.usuario = this.state.usuario;
        }
        return puesto;
      });
      x["reserva"+hora] = nuevo;
      this.setState({
        horario: x
      })
    }
  }

  handleSave = () => {
    let data = {
      posicion: this.state.selected,
      fecha: this.state.fecha
    }
    Axios.post("/app/reservar", data).then(response => {
      if(response.data.ok){
        toastr.success("Anotado!")
        this.setState({
          horario: response.data.horario,
          estoy: true
        })
      }else{
        toastr.warning(response.data.error);
        this.setState({
          horario: response.data.horario,
          estoy: false
        })
      }
    });
  }

  handleAddCorreo = (correo) => {
    let data = {
      correo
    }

    Axios.post("/app/agregarCorreo", data).then(response => {
      if(response.data.ok){
        toastr.success("Correo agregado!")
      }else{
        toastr.warning(response.data.error);
      }
    });
  }
  
  handleAddEspacio = (espacio) => {
    let data = {
      espacio
    }

    Axios.post("/app/nuevoEspacio", data).then(response => {
      if(response.data.ok){
        toastr.success("Agregado!")
        this.setState({
          horario: response.data.horario,
        })
      }else{
        toastr.warning(response.data.error);
      }
    });
  }

  handleEliminar2 = (hora,posicion) => {
    let data = {
      hora, posicion
    }

    Axios.post("/app/eliminarReserva", data).then(response => {
      if(response.data.ok){
        toastr.success(response.data.borrado+" ha sido eliminado!")
        if(response.data.borrado === this.state.usuario){
          this.setState({
            horario: response.data.horario,
            eliminar: false,
            estoy: false
          })
        }else{
          this.setState({
            horario: response.data.horario,
            eliminar: false
          })
        }
        this.setState({
          horario: response.data.horario,
          eliminar: false
        })
      }else{
        toastr.warning(response.data.error);
      }
    });
  }

  handleEliminar = () => {
    this.setState({eliminar: true})
  }

  handleLogout = () => {
    Axios.get("/logout").then(response => {
        window.location = "/login"
    });
  }
  
  componentDidMount(){
    let x = '';
    Axios.get("/app/date").then(response => {
      if(response.data.ok){
        x = new Date(response.data.date);
        let dia = '';
        let mes = '';
        switch (x.getDay()) {
          case 0:
            dia = 'Domingo';
          break;
          case 1:
            dia = 'Lunes';
          break;
          case 2:
            dia = 'Martes';
          break;
          case 3:
            dia = 'Miércoles';
          break;
          case 4:
            dia = 'Jueves';
          break;
          case 5:
            dia = 'Viernes';
          break;
          case 6:
            dia = 'Sábado';
          break;
          default:
          break;
        }
    
        switch (x.getMonth()) {
          case 0:
            dia = 'Enero';
          break;
          case 1:
            dia = 'Febrero';
          break;
          case 2:
            dia = 'Marzo';
          break;
          case 3:
            dia = 'Abril';
          break;
          case 4:
            dia = 'Mayo';
          break;
          case 5:
            dia = 'Junio';
          break;
          case 6:
            dia = 'Julio';
          break;
          case 7:
            dia = 'Agosto';
          break;
          case 8:
            dia = 'Septiembre';
          break;
          case 9:
            dia = 'Octubre';
          break;
          case 10:
            dia = 'Noviembre';
          break;
          case 11:
            dia = 'Diciembre';
          break;
          default:
          break;
        }
        this.setState({
          fecha: x.getDate()+'-'+x.getMonth()+1+'-'+x.getFullYear(),
          dia: x.getDate(),
          mes: x.getMonth()+1,
          anio: x.getFullYear(),
          diaString: dia,
          mesString: mes,
          horario: response.data.horario,
          last: response.data.horario.last,
          usuario: response.data.usuario,
          estoy: response.data.estoy,
          admin: response.data.admin
        })
      }else{
        toastr.warning(response.data.error)
      }
    });
  }

  render() {
    return (
      <>
      {this.state.admin ? <Navbar addCorreo={this.handleAddCorreo} addEspacio={this.handleAddEspacio} eliminar={this.handleEliminar}/>:null}
      <div className="container-fluid fondo d-flex">
        <div className="mx-auto dashboard-card">
          <div className="text-right position-absolute">
            
          </div>
            <div className="row" style={{height:'20%'}}>
              <div className="col-12 d-flex" style={{height:'30%'}}>
                <button onClick={() => {this.handleLogout()}} className="btn btn-danger btn-sm ml-auto" style={{maxHeight:'35px',fontSize: '0.7rem'}}>Cerrar sesion</button>
              </div>
              <div className="col-12 d-flex" style={{height:'70%'}}>
                <img className="mx-auto" alt='logo' src={logo} style={{height: "60%", marginTop: "3%"}}/>
              </div>
            </div>
            {this.state.fecha ? 
              <>
                <div className="row" style={{height:'10%'}}>
                <p className="m-auto" style={{margin: '1% 0%', fontSize: '1.2rem'}}>{this.state.diaString} {this.state.dia} de {this.state.mesString} de {this.state.anio}</p>
                </div>
                <div className="row mx-auto" style={{width: "90%", height:"50%"}}>
                    <div className="col-1">
                      <div className="row" style={{height: '20%'}}>
                        <div className="col-12 d-flex px-0">
                          <p className="m-0 my-auto" style={{fontSize: '0.9rem'}}>12:00 pm</p>
                        </div>
                      </div>
                      <div className="row" style={{height: '20%'}}>
                        <div className="col-12 d-flex px-0">
                          <p className="m-0 my-auto" style={{fontSize: '0.9rem'}}>12:30 pm</p>
                        </div>
                      </div>
                      <div className="row" style={{height: '20%'}}>
                        <div className="col-12 d-flex px-0">
                          <p className="m-0 my-auto" style={{fontSize: '0.9rem'}}>01:00 pm</p>
                        </div>
                      </div>
                      <div className="row" style={{height: '20%'}}>
                        <div className="col-12 d-flex px-0">
                          <p className="m-0 my-auto" style={{fontSize: '0.9rem'}}>01:45 pm</p>
                        </div>
                      </div>
                      <div className="row" style={{height: '20%'}}>
                        <div className="col-12 d-flex px-0">
                          <p className="m-0 my-auto" style={{fontSize: '0.9rem'}}>02:00 pm</p>
                        </div>
                      </div>
                    </div>
                    <div className="col-11">
                      {this.state.horario ?
                        <>
                          <div className='row' style={{height: '20%'}}>
                          {this.state.horario.reserva12.map((arg, index) => (
                            <div key={index} className="col-2 px-0">
                              {arg.disponible ? 
                                this.state.estoy ? 
                                  <button disabled className="btn libre m-auto" style={{width:'90%', height:'85%',fontSize: '0.9rem'}}>{arg.usuario !== "" ? "@"+arg.usuario : null}</button> 
                                : 
                                  <button onClick={() => {this.handleSelect('12',index)}} className="btn libre m-auto" style={{width:'90%', height:'85%',fontSize: '0.9rem'}}>{arg.usuario !== "" ? "@"+arg.usuario : null}</button>
                              : 
                                this.state.eliminar ? 
                                  <button className="btn eliminar m-auto" style={{width:'90%', height:'85%',fontSize: '0.9rem'}} onClick={() => {this.handleEliminar2('12',index)}}>@{arg.usuario}</button>
                                :
                                  <button disabled className="btn ocupado m-auto" style={{width:'90%', height:'85%',fontSize: '0.9rem'}}>@{arg.usuario}</button>
                              }
                            </div>
                          ))}
                          </div>
                          <div className='row' style={{height: '20%'}}>
                          {this.state.horario.reserva1230.map((arg, index) => (
                            <div key={index} className="col-2 px-0">
                              {arg.disponible ? 
                                this.state.estoy ? 
                                  <button disabled className="btn libre m-auto" style={{width:'90%', height:'85%',fontSize: '0.9rem'}}>{arg.usuario !== "" ? "@"+arg.usuario : null}</button> 
                                : 
                                  <button onClick={() => {this.handleSelect('1230',index)}} className="btn libre m-auto" style={{width:'90%', height:'85%',fontSize: '0.9rem'}}>{arg.usuario !== "" ? "@"+arg.usuario : null}</button>
                              : 
                                this.state.eliminar ? 
                                  <button className="btn eliminar m-auto" style={{width:'90%', height:'85%',fontSize: '0.9rem'}} onClick={() => {this.handleEliminar2('1230',index)}}>@{arg.usuario}</button>
                                :
                                  <button disabled className="btn ocupado m-auto" style={{width:'90%', height:'85%',fontSize: '0.9rem'}}>@{arg.usuario}</button>
                              }
                            </div>
                          ))}
                          </div>
                          <div className='row' style={{height: '20%'}}>
                          {this.state.horario.reserva1.map((arg, index) => (
                            <div key={index} className="col-2 px-0">
                              {arg.disponible ? 
                                this.state.estoy ? 
                                  <button disabled className="btn libre m-auto" style={{width:'90%', height:'85%',fontSize: '0.9rem'}}>{arg.usuario !== "" ? "@"+arg.usuario : null}</button> 
                                : 
                                  <button onClick={() => {this.handleSelect('1',index)}} className="btn libre m-auto" style={{width:'90%', height:'85%',fontSize: '0.9rem'}}>{arg.usuario !== "" ? "@"+arg.usuario : null}</button>
                              : 
                                this.state.eliminar ? 
                                  <button className="btn eliminar m-auto" style={{width:'90%', height:'85%',fontSize: '0.9rem'}} onClick={() => {this.handleEliminar2('1',index)}}>@{arg.usuario}</button>
                                :
                                  <button disabled className="btn ocupado m-auto" style={{width:'90%', height:'85%',fontSize: '0.9rem'}}>@{arg.usuario}</button>
                              }
                            </div>
                          ))}
                          </div>
                          <div className='row' style={{height: '20%'}}>
                          {this.state.horario.reserva145.map((arg, index) => (
                            <div key={index} className="col-2 px-0">
                              {arg.disponible ? 
                                this.state.estoy ? 
                                  <button disabled className="btn libre m-auto" style={{width:'90%', height:'85%',fontSize: '0.9rem'}}>{arg.usuario !== "" ? "@"+arg.usuario : null}</button> 
                                : 
                                  <button onClick={() => {this.handleSelect('145',index)}} className="btn libre m-auto" style={{width:'90%', height:'85%',fontSize: '0.9rem'}}>{arg.usuario !== "" ? "@"+arg.usuario : null}</button>
                              : 
                                this.state.eliminar ? 
                                  <button className="btn eliminar m-auto" style={{width:'90%', height:'85%',fontSize: '0.9rem'}} onClick={() => {this.handleEliminar2('145',index)}}>@{arg.usuario}</button>
                                :
                                  <button disabled className="btn ocupado m-auto" style={{width:'90%', height:'85%',fontSize: '0.9rem'}}>@{arg.usuario}</button>
                              }
                            </div>
                          ))}
                          </div>
                          <div className='row' style={{height: '20%'}}>
                          {this.state.horario.reserva2.map((arg, index) => (
                            <div key={index} className="col-2 px-0">
                              {arg.disponible ? 
                                this.state.estoy ? 
                                  <button disabled className="btn libre m-auto" style={{width:'90%', height:'85%',fontSize: '0.9rem'}}>{arg.usuario !== "" ? "@"+arg.usuario : null}</button> 
                                : 
                                  <button onClick={() => {this.handleSelect('2',index)}} className="btn libre m-auto" style={{width:'90%', height:'85%',fontSize: '0.9rem'}}>{arg.usuario !== "" ? "@"+arg.usuario : null}</button>
                              : 
                                this.state.eliminar ? 
                                  <button className="btn eliminar m-auto" style={{width:'90%', height:'85%',fontSize: '0.9rem'}} onClick={() => {this.handleEliminar2('2',index)}}>@{arg.usuario}</button>
                                :
                                  <button disabled className="btn ocupado m-auto" style={{width:'90%', height:'85%',fontSize: '0.9rem'}}>@{arg.usuario}</button>
                              }
                            </div>
                          ))}
                          </div> 
                        </>
                      : null}
                    </div>
                </div>
                <div className="row"  style={{height:'20%'}}>
                  <div className="col-12 superCenter">
                    {this.state.horario.last.length ? 
                      <p className="my-auto" style={{fontSize:"0.9rem"}}><span style={{fontSize:'1.2rem'}}>¡Felicidades! </span><span style={{fontSize:'1.4rem'}}>@{this.state.horario.last[this.state.horario.last.length - 1]}</span>, has ganado el deber de dejar la cocina en orden</p>
                    : null}
                  </div>
                  <div className="col-12 superCenter">
                    {this.state.estoy ? 
                      <button disabled className="btn btn-lg mb-auto reservar-btn">Reservar</button>
                    : 
                      <button onClick={() => {this.handleSave()}} className="btn btn-lg mb-auto reservar-btn">Reservar</button>
                    }
                  </div>
                </div>
              </>
            : <div className="loader" style={{marginTop: "20%"}}></div> }
        </div>
      </div>
      </>
    );
  }
}

export default Dashboard;