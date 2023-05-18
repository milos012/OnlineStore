import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import SignUp from './components/registration/SignUp'
import SignIn from './components/registration/SignIn'
import Products from './components/Products'
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';
//import Navbar from './components/NavBar'
import AddProduct from './components/admin/AddProduct'
import { NavbarNew } from './components/NavBarNew'
import { Store } from './components/user/Store'
import { ShoppingCartProvider } from './context/ShoppingCartContext'
import AdminProducts from './components/admin/AdminDisplayProducts'
import { NavbarAdmin } from './components/admin/NavBarAdmin'
import { AuthProvider } from './context/AuthContext'

// const withNavBar = (WrappedComponent:any, NavBarComponent:any) => () => (
//   <>
//     <NavBarComponent/>
//     <WrappedComponent/>
//   </>
// );

// const AddProductWithNav = withNavBar(AddProduct,NavbarAdmin)
// const StoreWithNav = withNavBar(Store,NavbarNew)

function App() {
  const [count, setCount] = useState(0)

  return (
    <AuthProvider>
      <ShoppingCartProvider>
        <Router>
          <div className="App">
            <NavbarNew/>
            <div className="content">
              <Routes>
                <Route path="/" element={<Store/>}/>
                <Route path="/login" element={<SignIn/>}/>
                <Route path="/register" element={<SignUp/>}/>
                <Route path="/products" element={<Products/>}/>
                <Route path="/store" element={<Store/>}/>

                {/* Protected admin routes  */}
                <Route path="/addProduct" element={<AddProduct/>}/>
                <Route path="/adminProducts" element={<AdminProducts/>}/>

                {/* <Route path="/addProduct" component={AddProductWithNav} />
                <Route path="/store" component={StoreWithNav} /> */}

              </Routes>
            </div>
          </div>
        </Router>
      </ShoppingCartProvider>
    </AuthProvider>
  )
}

export default App
