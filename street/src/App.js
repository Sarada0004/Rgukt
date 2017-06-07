/*eslint-disable*/
import React, {PropTypes, Component } from 'react';
import logo from './logo.svg';
import './App.css';
import GoogleMapReact from 'google-map-react';
import { Button } from 'antd';
import PlacesAutocomplete, { geocodeByAddress, getLatLng } from 'react-places-autocomplete'
import MyGreatPlace from './my_great_place';
import {geolocated} from 'react-geolocated';


const AnyReactComponent = ({ text }) => <div>{text}</div>;

class App extends Component {
    
    static propTypes = {
        center: PropTypes.array,
        zoom: PropTypes.number,
        greatPlaceCoords: PropTypes.any
     };
     
    constructor(props){
        super(props);
        this.state = {
            lat: 59.95,
            lng: 30.33,
            zoom: 11,
            address: ''
        };
        this.onChange = (address) => this.setState({ address })
        
        this.handleFormSubmit = this.handleFormSubmit.bind(this);
        this.getLatLng = this.getLatLng.bind(this);
    }
     
     getLatLng(){
         console.log("Current Location is clicked.");
        if(this.props.isGeolocationAvailable){
            if(this.props.isGeolocationEnabled){
                if(this.props.coords){
                    console.log(this.props.coords.latitude);
                    console.log(this.props.coords.longitude);
                    this.setState({
                        lat: this.props.coords.latitude,
                        lng: this.props.coords.longitude
                    })
                    console.log("lat&lng are changed...", this.state.lat, this.state.lng);
                }
            }
        }
    }
    
    handleFormSubmit = (event) => {
        event.preventDefault()
    
        geocodeByAddress(this.state.address)
          .then(results => getLatLng(results[0]))
          .then(latLng => {console.log('Success', latLng);
                console.log("latitude is :", latLng.lat);
                console.log("Longitude is :", latLng.lng);
                this.setState({
                    lat: latLng.lat,
                    lng: latLng.lng,
                });
                console.log("lat&lng are changed...", this.state.lat, this.state.lng);
          })
          .catch(error => console.error('Error', error))
      }
    
  render() {
      
       const inputProps = {
        value: this.state.address,
         onChange: this.onChange,
         placeholder: 'Search Places...'
        }
      
      
    return (
     <div style={{width: '100%', height: '500px'}}>
     <br />
      <form onSubmit={this.handleFormSubmit}>
        <PlacesAutocomplete inputProps={inputProps} /><br /><br />
        <Button type="primary" htmlType="submit">Submit</Button>
      </form>
     <br />
     <button onClick={this.getLatLng()}>CurrentLocation</button><br />
     <br />
     <GoogleMapReact

        bootstrapURLKeys={{
            key: "AIzaSyAcS52skiqBCvLlpZ5Dkm1rh25WQ4-6CIM"
          }}
        center={[this.state.lat, this.state.lng]}
        defaultZoom={this.state.zoom}
        >
        
        <MyGreatPlace lat={this.state.lat} lng={this.state.lng} text={'C'}  />
      </GoogleMapReact>
    </div>
    );
  }
}


export default geolocated({
  positionOptions: {
    enableHighAccuracy: false,
  },
  userDecisionTimeout: 5000
})(App);
