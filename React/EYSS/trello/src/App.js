import React, {Component} from 'react';
import './App.css';
import Nav from "./components/Nav";
import NewNote from "./components/NewNote";
import {list} from "./list.json";


class App extends Component {
  constructor(){
    super();
    this.state = {
      todo: list,
    }
    this.handleNew = this.handleNew.bind(this);
  }

  handleNew(note){
    this.setState({
      todo: [...this.state.todo, note]
    });
  }

  handleRemove(index){
    if(window.confirm("Delete?")){
      this.setState({
        todo: this.state.todo.filter((e,i)=>{
          return i !== index
        })
      })
    }
  }

  render (){
    const x = this.state.todo.map((todo, i) => {
      return (
        <div className="col-3 pt-4 text-center">
          <div className="card">
            <div className="card-header">
              <h5 className="card-title">{todo.title}</h5>
              <span className="badge badge-pill badge-danger">{todo.priority}</span>
            </div>
            <div className="card-body">
              <p className="card-text">{todo.responsable}</p>
              <p className="card-text">{todo.description}</p>
            </div>
            <div className="card-footer">
              <button className="btn btn-danger" onClick={this.handleRemove.bind(this,i)}>Delete</button>
            </div>
          </div>
        </div>
      )
    });

    return (
      <div>
        <Nav/>
        <h1 className="text-center text-light pt-3">To-do List <span className="badge badge-pill badge-light">{this.state.todo.length}</span></h1>
        <div className="row container mx-auto">
            <NewNote onAddNew={this.handleNew}/>
            {x}
        </div>
      </div>
    );
  }
}

export default App;
