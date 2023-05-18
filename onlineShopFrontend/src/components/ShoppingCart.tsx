import { Offcanvas, Stack } from "react-bootstrap"
import { useShoppingCart } from "../context/ShoppingCartContext"
import { formatCurrency } from "../util/FormatCurrency"
import { CartItem } from "./CartItem"
import storeItems from "../data/items.json"
import axios from 'axios'
import Button from '@mui/material/Button';

type ShoppingCartProps = {
  isOpen: boolean
}

function createOrder(){

  const newOrder1 = {
    totalSum: '',
    dateMade: '',
    itemsOrdered: ''  //Names of ordered items
  };

  newOrder1.dateMade = new Date().toString()
  // newOrder1.totalSum = cartItems.reduce((total:number, cartItem) => {
  //   const item = storeItems.find(i => i.id === cartItem.id)
  //   return total + (item?.price || 0) * cartItem.quantity
  // }, 0)


  // axios.post(`http://localhost:9000/api/v1/orders/`,newOrder1)
  //   .then(res => {
  //     console.log(res)
  //     console.log(res.data)
  //   }).catch(error =>{
  //     console.log(error)
  // })
  console.log('TODO')
  console.log(newOrder1)

}

export function ShoppingCart({ isOpen }: ShoppingCartProps) {
  const { closeCart, cartItems } = useShoppingCart()
  return (
    <Offcanvas show={isOpen} onHide={closeCart} placement="end">
      <Offcanvas.Header closeButton>
        <Offcanvas.Title>Cart</Offcanvas.Title>
      </Offcanvas.Header>
      <Offcanvas.Body>
        <Stack gap={3}>
          {cartItems.map(item => (
            <CartItem key={item.id} {...item} />
          ))}
          <div className="ms-auto fw-bold fs-5">
            Total{" "}
            {formatCurrency(
              cartItems.reduce((total, cartItem) => {
                const item = storeItems.find(i => i.id === cartItem.id)
                return total + (item?.price || 0) * cartItem.quantity
              }, 0)
            )}
          </div>
          <div>
            <Button>
              Order
            </Button>
          </div>
        </Stack>
      </Offcanvas.Body>
    </Offcanvas>
  )
}