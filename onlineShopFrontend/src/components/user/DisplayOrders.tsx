import * as React from 'react';
import Button from '@mui/material/Button';
import CameraIcon from '@mui/icons-material/PhotoCamera';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import axios from 'axios';


const headerConfig = {
    headers:{
        'Access-Control-Allow-Origin': '*',
        'Content-Type': 'application/json'
    }
}

// Displays all user's previous orders
export default class UserOrders extends React.Component {

    state = {
        orders: []
    }

    componentDidMount(){
        //TODO treba proslediti i id ulogovanog korisnika
        // promeniti putunju koju gadja, sada prikazuje sve ordere iz baze
        axios.get('http://localhost:9000/api/orders').then(res => {
            const orders = res.data;
            this.setState({orders})
        })
    }

    render() {
        return(
            <ul>
                {/* {this.state.orders.map(order =>(
                    <li key={order.id}>{order.name}</li>
                    ))} */}
            </ul>
        )
        
    }
  
}