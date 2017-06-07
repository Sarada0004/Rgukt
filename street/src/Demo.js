import React from 'react';
import {geolocated} from 'react-geolocated';
 
class Demo extends React.Component {
    
    constructor(props){
        super(props);
        this.state = {
            lat: 0,
            lng: 0
        }
        this.getLatLng = this.getLatLng.bind(this);
    }
    
    getLatLng(){      
        if(this.props.isGeolocationAvailable){
            if(this.props.isGeolocationEnabled){
                if(this.props.coords){
                    this.setState({
                        lat: this.props.coords.latitude,
                        lng: this.props.coords.longitude
                    })
                }
            }
        }
    }

    
    
    
  render() {
    return (
        <div>
        <button onclick={this.getLatLng()}>click</button>
        {this.state.lat}
        {this.state.lng}
        </div>
    );
  }
}
 
export default geolocated({
  positionOptions: {
    enableHighAccuracy: false,
  },
  userDecisionTimeout: 5000
})(Demo);