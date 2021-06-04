import React, { Component } from 'react'
import { connect } from 'react-redux'

import Chat from './Chat'
import Header from './components/Header'
import hc from './hc'
import actions , { consts } from './actions'
import Profile from './components/Profile'

class App extends Component {
    constructor(props){
        super(props)
        hc({
            functionName: 'check_register',
            callback: data=>{
                if (data && data.Ok && data.Ok.registered) {
                    let me = JSON.parse(data.Ok.me.App[1])
                    this.props.updateProfile(me)
                    this.setState({logged:true})
                }
            }
        })
    }
    state = {
        logged : false,
        username : null
    }
    handleUsernameChange = e => {
        this.setState({
            username: e.target.value
        })
        if ( e.keyCode === 13 ){
            this.join(e.target.value)
        }
    }
    join = username => {
        hc({
            functionName: 'create_user',
            params: { username },
            callback: (result) =>{
                if(result.Ok){
                    this.props.updateProfile({ username })
                    this.setState({logged:true})
                    // hc({
                    //     functionName: 'check_register',
                    //     callback: data=>{
                    //         if (data && data.Ok && data.Ok.registered) {
                    //             let me = JSON.parse(data.Ok.me.App[1])
                    //             this.props.updateProfile(me)
                    //             this.setState({logged:true})
                    //         }
                    //     }
                    // })
                }else{
                    alert('error')
                    console.log(result)
                }
            }
        })
    }
    render() {
        if(!this.state.logged){
            return (
                <div className='register-section'>
                    <h3>Welcome to Planet Chat!</h3>
                    <input onKeyDown={this.handleUsernameChange} placeholder="Insert your username"/>
                    <button onClick={e=>this.join(this.state.username)}>Join</button>
                </div>
            )
        }
        const { currentApp } = this.props
        return (
            <>
                <Header />
                {
                    currentApp === consts.CHAT &&
                    <Chat />  
                }
                {
                    currentApp === consts.PROFILE &&
                    <Profile />  
                }
            </>
        )
    }
}

const mapStateToProps = ({currentApp}) => ({
    currentApp
})

const mapDispatchToProps = dispatch => ({
    updateProfile: data => actions.updateProfile(data, dispatch),  
})

export default connect(mapStateToProps, mapDispatchToProps)(App)
