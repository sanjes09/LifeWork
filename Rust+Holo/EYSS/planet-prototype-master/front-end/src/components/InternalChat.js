import React from 'react'
import { connect } from 'react-redux'

import '../css/chat.css'
import hc from '../hc'
import actions from '../actions'

class InternalChat extends React.Component {
    inputRef = React.createRef()
    sendMessage = e => {
        let text = this.inputRef.current.value
        if (!text) {
            return
        }
        this.inputRef.current.value = ''
        hc({
            functionName:'send_message',
            params:{
                entry:{
                    content : text
                },
                channel_address : this.props.currentChannel.address
            },
            callback:()=>{
                setTimeout(
                    ()=>{
                        hc({
                            functionName:'get_messages_from_channel',
                            params : {
                                channel : this.props.currentChannel.address
                            },
                            callback : res => {
                                this.props.loadCurrentChat(res.Ok)
                            }
                        })
                    },
                    300
                )
            }
        })
    }
    render() {
        const cc = this.props.currentChannel,
            msjs = this.props.chat
        console.log('msjs', msjs)
        return (
            <div className='chat'>
                <div className='descriptor'>
                    <h4>{cc ? cc.entry.title : <>Select a chat</>}</h4>
                    <small>{cc ? cc.entry.description : <>Join the Planet!</>}</small>
                </div>
                <div className='messages'>
                    {
                        msjs &&
                        msjs.length > 0 &&
                        msjs.map((m,i)=>(
                            <div key={i} className={ m.entry.author === this.props.me.address && 'me'}>
                                {m.entry.content}
                            </div>
                        ))
                    }
                </div>
                <div className='message-input'>
                    <input ref={this.inputRef} onKeyDown={e=>e.keyCode===13?this.sendMessage():null} placeholder='Write something!' disabled={cc?'':true}/>
                    <button disabled={cc?'':true} onClick={this.sendMessage}>
                        Send
                        <span role='img' aria-label=''>➡️</span>
                    </button>
                </div>
            </div>
        )
    }
}


const mapStateToProps = ({chat,currentChannel,me}) => ({
    chat,
    currentChannel,
    me
})

const mapDispatchToProps = dispatch => ({
    loadCurrentChat: data => actions.loadCurrentChat(data, dispatch)
})

export default connect(mapStateToProps, mapDispatchToProps)(InternalChat)
