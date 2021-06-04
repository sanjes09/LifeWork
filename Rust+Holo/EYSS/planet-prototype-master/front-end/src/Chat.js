import React from "react"
import { connect } from 'react-redux'

import actions from './actions'
import Sidebar from "./components/Sidebar"


import "./css/profile_popup.css"
import InternalChat from "./components/InternalChat";
import Modal from "./components/Modal";

class Chat extends React.Component {
    closeModal = () => {
        this.props.closeModal()
    }
    render(){
        return (
            <>
                <main>
                    <Sidebar />
                    <InternalChat />
                </main>
                {
                    this.props.modal === 'DISPLAY_CHANNELS' &&
                    <Modal title="Channels" close={this.closeModal} type="channels" data={[]}/>
                }
                {
                    this.props.modal === 'DISPLAY_CONVERSATIONS' &&
                    <Modal title="Members" close={this.closeModal} type="members" data={[]}/>
                }
            </>
        )
    }
}

const mapStateToProps = ({modal}) => ({
    modal
})

const mapDispatchToProps = dispatch => ({
    closeModal: ()=>actions.closeModal(dispatch)
})

export default connect(
    mapStateToProps,
    mapDispatchToProps
)(Chat)
