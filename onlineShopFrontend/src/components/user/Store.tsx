import { Col, Form, InputGroup, Row } from "react-bootstrap"
import { StoreItem } from "../StoreItem"
import storeItems from "../../data/items.json"
import { useState } from "react"

export function Store() {
  const [search, setSearch] = useState('')

  return (
    <>
      <h1>Store</h1>
      <Form>
        <InputGroup>
          <Form.Control onChange={(e) => setSearch(e.target.value)} placeholder="Search"/>
        </InputGroup>
      </Form>
      <Row md={2} xs={1} lg={3} className="g-3">
        {storeItems.filter((item) => {
          return search.toLowerCase() === '' ? item : item.name.toLowerCase().includes(search);
        }).map(item => (
          <Col key={item.id}>
            <StoreItem {...item} />
          </Col>
        ))}
      </Row>
    </>
  )
}