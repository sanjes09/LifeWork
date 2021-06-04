import React, { Component } from 'react'
import { connect } from 'react-redux'
import ImgUploader from './Utils/ImgUploader';

import '../sass/profile.scss'

const lorem = 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.'

const Trust = ({confidence}) => (
    <div className='trust'>
        <div>
            Trust level: {confidence}%
        </div>
        <div className='confidence'>
            <div className='inner-progress' style={{width:confidence + '%'}}></div>
        </div>
    </div>
)

class Profile extends Component {
    state = {
        confidence: 60,
        tags: [
            'programming',
            'design',
            'holochain',
            'community',
        ]
    }
    addTag = e => {
        let tag = prompt('insert tag')
        if(tag){
            if (this.state.tags.includes(tag)){
                alert('repeated tag!')
            }else{
                this.setState({tags:this.state.tags.concat([tag])})
            }
        }
    }
    render() {
        return (
            <div className='profile'>
                <section className='top-part'>
                    <section className='img-holder'>
                        <h3>{this.props.me.username}</h3>
                        <ImgUploader />
                    </section>
                    <section className='top-text'>
                        <Trust confidence={this.state.confidence} />
                        <section className='tags'>
                            {
                                this.state.tags.map((t,i)=>(
                                    <span key={i}>
                                        {t}
                                    </span>
                                ))
                            }
                            <span onClick={this.addTag}>+ Add tag</span>
                        </section>
                        <div className='description'>
                            <textarea value={lorem} onChange={e=>{}} />
                        </div>
                    </section>
                </section>
                <section className='bottom-section'>
                </section>
            </div>
        )
    }
}

const mapStateToProps = ({me}) => ({
    me
})

const mapDispatchToProps = {
    
}

export default connect(mapStateToProps, mapDispatchToProps)(Profile)
