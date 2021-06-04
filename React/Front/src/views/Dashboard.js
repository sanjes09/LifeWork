import React, { Component } from "react";
import { Modal } from "react-bootstrap";
import Modules from "../../components/Modules/Modules";
import ProgressBar from '../../components/ProgressBar/ProgressBar';
import { Row, Col } from "react-grid-system";
import "./dashboard.css";
import Sidebar from "../../components/Sidebar/Sidebar";
import HowTo from '../../components/How-to/HowTo';
import Axios from "axios";
import toastr from 'toastr';

export class Dashboard extends Component {

  state = {
    chart: {},
    title: "1. Organisational Problems",
    progress: 0,
    displayChart: 0,
    showModal: false,
    option: 1,
    project: "",
  };

  changeOption = (val) => {
    let titulo = "";
    if(val >= 1 && val <= 8){
        titulo = "1. Organisational Problems";
    }
    if(val >= 9 && val <= 11){
        titulo = "2. Eco System Problems";
    }
    if(val >= 12 && val <= 13){
        titulo = "3. Pain Points";
    }
    if(val >= 14 && val <= 17){
        titulo = "4. A Typical Scenario";
    }
    if(val >= 18 && val <= 19){
        titulo = "5. What If Capability";
    }
    if(val >= 20 && val <= 22){
        titulo = "6. A Good Idea";
    }
    if(val >= 23 && val <= 25){
        titulo = "7. Value Measurments";
    }
    if(val >= 26 && val <= 27){
        titulo = "8. Value Measurments";
    }
    this.setState({ option: val, displayChart: 0, title: titulo });
    if(val === 30){
        window.location = "/projects";
    }
  };

  changeProgress = (val) => {
    this.setState({ progress: val});
  };

  handleClose = () => {
    this.setState({ showModal: false });
  };

  closeModal = () => {
    this.setState({showModal:false})
  }

  handleModal = () => {
    this.setState({ showModal: true });
  };

  componentDidMount(){
    let data = {
      id: this.props.match.params.id
    }
    Axios.post("/app/getProject", data).then(response => {
      if(response.data.ok){
        this.setState({
          project: response.data.name
        })
      }else{
        toastr.warning(response.data.error)
      }
    });
  }

  render() {
    return (
      this.state.option < 30 ? 
        <>
          <div className="dashboard">
            <Sidebar project={this.state.project} changeOption={this.changeOption} option={this.state.option}/>
            <div className="main">
              {/* <Row>
                <Col md={12}>
                  <div className="how-to-div dash-div">
                    <HowTo />
                  </div>
                </Col>
                <Col md={6}>
                  <div className="chart-div dash-div">
                    <RadarChart val={this.state.chart} />
                  </div>
                </Col>
              </Row> */}

              <Row className="mt-3">
                <Col md={12}>
                  <div className="question-div dash-div">
                    <div
                      className="question-div-header"
                      style={{
                        color: "#c6ad75",
                        borderBottom: "1px solid #a1a1a1",
                        padding: "0 20px",
                        height: "30px",
                        background:"#fff",
                        position:"sticky",
                        top:"0",
                        zIndex:"99"
                      }}
                    >
                      <p style={{ fontWeight: "600", paddingTop: "2px" }}>
                        {this.state.title}{" "}
                        
                        {/* <span style={{ float: "right", fontSize: "20px", margin:"0 5px" }}>
                          <OverlayTrigger
                            placement={"left"}
                            overlay={
                              <Tooltip id={`tooltip-left`}>
                                Lorem ipsum dolor sit amet consectetur adipisicing elit. Ad corrupti ipsam est voluptatem fuga hic facilis consectetur ut at numquam odio accusantium quaerat cumque reprehenderit sapiente optio, vero, voluptatum, tempore adipisci nisi? Dolorem voluptatum itaque, molestiae non atque, dolores possimus officiis beatae molestias, et commodi. Labore maiores animi porro accusamus.
                              </Tooltip>
                            }
                          >
                            <i className="fas fa-info-circle"></i>
                          </OverlayTrigger>
                        </span> */}

                        <span style={{ float: "right", fontSize: "20px" }} onClick={this.handleModal}>
                          <i className="fas fa-play-circle"></i> 
                          {/* <VideoModal show={this.state.showModal} onHide={this.handleClose}/>  */}
                        </span>
                        <Modal show={this.state.showModal} size="lg" aria-labelledby="contained-modal-title-vcenter" centered onHide={this.handleClose}>
                            <Modal.Header closeButton>
                            </Modal.Header>
                            <Modal.Body  style={{height:"50vh", width:"100%"}}>
                                <HowTo />
                            </Modal.Body>
                        </Modal>
                      </p>
                    </div>
                    <div className="progressBar">
                      <ProgressBar number={this.state.progress} />
                    </div>
                    <div className="question-content">
                      <Modules id={this.props.match.params.id} progress={this.changeProgress} changeOption={this.changeOption} option={this.state.option}/>
                    </div>
                  </div>
                </Col>
              </Row>
            </div>
          </div>
        </>
      :
        null
    );
  }
}

export default Dashboard;