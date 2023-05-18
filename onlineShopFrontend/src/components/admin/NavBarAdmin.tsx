import { Button, Container, Nav, Navbar as NavbarBs } from "react-bootstrap"
import { NavLink } from "react-router-dom"

export function NavbarAdmin() {
  return (
    <NavbarBs sticky="top" className="bg-white shadow-sm mb-3">
      <Container>
        <Nav className="me-auto">
          <Nav.Link to="/adminProducts" as={NavLink}>
            Products
          </Nav.Link>
          <Nav.Link to="/addProduct" as={NavLink}>
            Add Product
          </Nav.Link>
          <Nav.Link to="/" as={NavLink}>
            Logout
          </Nav.Link>
        </Nav>
      </Container>
    </NavbarBs>
  )
}