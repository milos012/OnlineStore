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

function removeProduct(id: number){

    axios.delete(`http://localhost:9000/api/v1/products/:${id}`)
      .then(res => {
        console.log(res)
        console.log(res.data)
      }).catch(error =>{
        console.log(error)
    })

}

function editProduct(id: number){
    //TODO
    console.log("TODO")

}

export default class AdminProducts extends React.Component {

    state = {
        products: []
    }

    componentDidMount(){
        axios.get('http://localhost:9000/api/products',headerConfig).then(res => {
            const products = res.data;
            this.setState({products})
        })
    }

    render() {
        return(
            <ul>
                {this.state.products.map(product =>
                    <li key={product.id}>
                        {product.name}
                        <Button  onClick={() => editProduct(product.id)}>Edit</Button>
                        <Button  onClick={() => removeProduct(product.id)}>Delete</Button>
                    </li>
                    )}
            </ul>
        )
        
    }
  
}