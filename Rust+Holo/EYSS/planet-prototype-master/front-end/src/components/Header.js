import React from "react"
import { connect } from 'react-redux'
import actions , { consts } from '../actions'

import "../css/header.css"

class Header extends React.Component {
    menuClick = e => {
        e.preventDefault()
        this.props.selectApp(e.target.getAttribute('app'))
    }
    render(){
        return (
            <header>
                <div>
                    <img
                        alt="Planet"
                        src='/img/Proposal of graphic identidy planet-09.png'
                        className="logo"
                    />
                </div>

                <div className="links">
                    <a href="/" className="link" app={consts.CHAT} onClick={this.menuClick}>
                        Chat
                    </a>
                    <a href="/" className="link">
                        Exchange
                    </a>
                    <a href="/" className="link" app={consts.PROFILE} onClick={this.menuClick}>
                        User
                    </a>
                </div>
            </header>
        )
    }
}
const mapStateToProps = () => ({})

const mapDispatchToProps = dispatch => ({
    selectApp : app => actions.selectApp( app, dispatch )
})

export default connect(
    mapStateToProps,
    mapDispatchToProps
)(Header)