import React, { Component } from 'react'
import PropTypes from 'prop-types'

export default class ImgUploader extends Component {
    static propTypes = {
        url : PropTypes.string,
        onUpload : PropTypes.func,
    }
    dropRef = React.createRef()
    state = {
        dragging: false
    }
    componentDidMount() {
        let div = this.dropRef.current
        this.dragCounter = 0
        div.addEventListener('dragenter', this.handleDragIn)
        div.addEventListener('dragleave', this.handleDragOut)
        div.addEventListener('dragover', e=>{e.stopPropagation();e.preventDefault()})
        div.addEventListener('drop', this.handleDrop)
    }
    componentWillUnmount() {
        let div = this.dropRef.current
        div.removeEventListener('dragenter', this.handleDragIn)
        div.removeEventListener('dragleave', this.handleDragOut)
        div.removeEventListener('dragover', e=>{e.stopPropagation();e.preventDefault()})
        div.removeEventListener('drop', this.handleDrop)
    }
    // handleDrag = (e) => {}
    handleDragIn = (e) => {
        e.preventDefault()
        e.stopPropagation()
        this.dragCounter++  
        if (e.dataTransfer.items && e.dataTransfer.items.length > 0) {
            // this.displayImg(e.dataTransfer.items[0])
            this.setState({dragging: true})
        }
    }
    displayImg(file){
        const reader = new FileReader()
        reader.readAsDataURL(file)
        reader.onload =  () => {
            this.setState({myProfilePicture:reader.result})
        }
        reader.onerror = (error) => {
          console.log('Error: ', error)
        }
    }
    handleDragOut = (e) => {
        e.preventDefault()
        e.stopPropagation()
        this.dragCounter--
        if (this.dragCounter > 0) return
        this.setState({dragging: false})
    }
    handleDrop = e => {
        e.preventDefault()
        e.stopPropagation()
        this.setState({drag: false})
        if (e.dataTransfer.files && e.dataTransfer.files.length > 0) {
            // this.props.handleDrop(e.dataTransfer.files)
            this.displayImg(e.dataTransfer.files[0])
            e.dataTransfer.clearData()
            this.dragCounter = 0
            this.setState({dragging: false})
        }
    }
    render() {
        return (
            <div className='img-uploader'>
                <section className='drop-section' ref={this.dropRef} onClick={this.uploadZoneClick}>
                    {
                        this.state.dragging &&
                        <div 
                            style={{
                            border: 'dashed grey 3px',
                            backgroundColor: 'rgba(255,255,255,.8)',
                            position: 'absolute',
                            top: 0,
                            bottom: 0,
                            left: 0, 
                            right: 0,
                            zIndex: 9999,
                            height:'100%',
                            width:'100%',
                            }}
                        />
                    }
					<img src={this.state.myProfilePicture || "/img/upload.png" } alt="upload" />
				</section>
            </div>
        )
    }
}
