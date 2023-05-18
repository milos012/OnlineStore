import React, {Component} from 'react';
import Avatar from '@mui/material/Avatar';
import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';
import Grid from '@mui/material/Grid';
import Typography from '@mui/material/Typography';
import { makeStyles } from '@mui/material/styles';
import Alert from '@mui/material/Alert';
import { Paper } from '@mui/material';
import Radio from '@mui/material/Radio';
import RadioGroup from '@mui/material/RadioGroup';
import FormControlLabel from '@mui/material/FormControlLabel';
import FormControl from '@mui/material/FormControl';
import FormLabel from '@mui/material/FormLabel';
import Checkbox from '@mui/material/Checkbox';
import FormGroup from '@mui/material/FormGroup';
import axios from 'axios';

const paperStyle={padding: "30px 50px",width:370,margin:"20px auto"}
const avatarStyle={backgroundColor: "#1bbd7e"}
const headerStyle={margin:0}
const marginTop={marginTop:5}


function AlertSucc() {
  const [open, setOpen] = React.useState(true);
  return (
    //Popup.alert('You pressed the Save btn')
    <Alert
    >
      Close me!
    </Alert>
  );
}

const headerConfig = {
    headers:{
        'Access-Control-Allow-Origin': '*',
        'Content-Type': 'application/json'
    }
}

class UserDataEdit extends React.Component<any,any> {
    constructor(props: any){
        super(props)

        this.state = {
            name: '',
            price: '',
            category: '',
            description: '',
            availability: false,
            image:''

        }
    }

    changeHandler = (e: any) => {
        this.setState({[e.currentTarget.name]: e.currentTarget.value})
    }

    changeAvailabilityHandler = (e: any) => {
        const value = e.currentTarget.value === 'true';
        this.setState({
          availability: value
        });
      };

    submitHandler = (e: React.SyntheticEvent) => {
        e.preventDefault()
        console.log(this.state)
        axios.post('http://localhost:9000/api/v1/products',this.state,
        {
            headers: {'Content-Type': 'application/json','Access-Control-Allow-Origin': '*'},
            withCredentials: true
        }).then(response=>
        {
            console.log(response)
        })
        .catch(error =>{
            console.log(error)
        })
    }

    render() { 
        const {name,price,category,description, availability, image} = this.state
        return (
            <Grid>
            <Paper elevation={20} style={paperStyle}>
                <Grid>
                    {/* <Avatar style={avatarStyle}>
                        <EditIcon/>
                    </Avatar> */}
                    <h2 style={headerStyle}>Add new product</h2>
                </Grid>
                <form onSubmit={this.submitHandler}>
                    <TextField
                        name="name"
                        variant="outlined"
                        required
                        fullWidth
                        id="name"
                        label="Name"
                        autoFocus
                        style={marginTop}
                        value={name}
                        onChange={this.changeHandler}
                    />
                    <TextField
                        variant="outlined"
                        required
                        fullWidth
                        id="price"
                        label="Price"
                        name="price"
                        type="number"
                        style={marginTop}
                        value={price}
                        onChange={this.changeHandler}
                    />
                    <FormControl component="fieldset" style={marginTop}>
                        <FormLabel component="legend">Category*</FormLabel>
                        <RadioGroup aria-label="Category" name="category" style={{display:'initial'}} value={category} onChange={this.changeHandler}>
                            <FormControlLabel value="Pice" control={<Radio />} label="Pice" />
                            <FormControlLabel value="Mlecni" control={<Radio />} label="Mlecni" />
                            <FormControlLabel value="Voce" control={<Radio />} label="Voce" />
                            <FormControlLabel value="Povrce" control={<Radio />} label="Povrce" />
                            <FormControlLabel value="Suhomesnato" control={<Radio />} label="Suhomesnato" />
                        </RadioGroup>
                    </FormControl>

                    <FormControl component="fieldset" style={marginTop}>
                        <FormLabel component="legend">Availability*</FormLabel>
                        <RadioGroup aria-label="Availability" name="availability" style={{display:'initial'}} value={availability} onChange={this.changeAvailabilityHandler}>
                            <FormControlLabel value="true" control={<Radio />} label="In stock" />
                            <FormControlLabel value="false" control={<Radio />} label="Out of stock" />
                        </RadioGroup>
                    </FormControl>
                    <TextField
                        variant="outlined"
                        fullWidth
                        id="description"
                        label="Description"
                        name="description"
                        style={marginTop}
                        value={description}
                        onChange={this.changeHandler}
                    />
                    <label>Image</label>
                    <TextField
                        variant="outlined"
                        fullWidth
                        id="image"
                        name="image"
                        type="file"
                        style={marginTop}
                        value={image}
                        onChange={this.changeHandler}
                    />
                    <Button
                    type="submit"
                    fullWidth
                    variant="contained"
                    color="primary"
                    >
                        Submit
                    </Button>
                </form>
            </Paper>
        </Grid>
          );
    }
}
 
export default UserDataEdit;