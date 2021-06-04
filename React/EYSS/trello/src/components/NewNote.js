import React, {Component} from "react";

class NewNote extends Component{
    constructor(){
        super();
        this.state = {
            title: "",
            responsable: "",
            description: "",
            priority: "low"
        };
        this.handleInput = this.handleInput.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleInput(e){
        const {value,name} = e.target;
        this.setState({
            [name]: value
        });
    }

    handleSubmit(e){
        e.preventDefault();
        this.props.onAddNew(this.state);
    }

    render(){
        return (
        <div className="col-3 pt-4 text-center">
            <div className="card">
                <form className="card-body" onSubmit={this.handleSubmit}>
                    <div className="form-group">
                        <input
                            type="text"
                            name="title"
                            onChange={this.handleInput}
                            className="form-control"
                            placeholder="Title"
                        />
                    </div>
                    <div className="form-group">
                        <input
                            type="text"
                            onChange={this.handleInput}
                            name="responsable"
                            className="form-control"
                            placeholder="Responsable"
                        />
                    </div>
                    <div className="form-group">
                        <input
                            onChange={this.handleInput}
                            type="text"
                            name="description"
                            className="form-control"
                            placeholder="Description"
                        />
                    </div>
                    <div className="form-group">
                        <select name="priority" className="form-control" onChange={this.handleInput}>
                            <option>high</option>
                            <option>medium</option>
                            <option>low</option>
                        </select>
                    </div>
                    <button type="submit" className="btn btn-success">Create</button>
                </form> 
            </div>
        </div>
        )
    }
}

export default NewNote;