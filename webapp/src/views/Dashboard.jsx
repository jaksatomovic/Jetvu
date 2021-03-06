import React, {Component, Fragment} from "react";
import axios from 'axios';
import {
  Button,
  Card,
  CardHeader,
  CardBody,
  CardTitle,
  FormGroup,
  Input, Table,
  Row,
  Col, Form, CardFooter
} from "reactstrap";
import ReactTable from "react-table";
import "react-table/react-table.css";
import Moment from 'react-moment';


class Dashboard extends Component {

    constructor(props) {
        super(props);

        this.state = {
            flights: [],
            origin: "MIL",
            destination: "LON",
            max: 10,
            adults: 2,
            departureDate: "2019-08-01",
            returnDate: "2019-08-28",
            nonStop: false,
            username: 'admin',
            password: 'admin',
            currency:  'EUR',
            loading: false
        }
    }

    handleChange = (event) => {
        let name = event.target.name
        let value = event.target.type === 'checkbox' ? event.target.checked : event.target.value

        this.setState(prevState => {
            prevState[name] = value
            return prevState
        })
    }

    login = async () => {
        let self = this

        await axios.post("http://localhost:5000/login", {
            username: self.state.username,
            password: self.state.password
        }).then(function (response) {
                localStorage.setItem('accessToken', response.data.token)
                window.location.reload()
        }).catch(function (error) {
            console.log(error)
        })
    }

    fetchFlights = async () => {
        let self = this
        this.setState({loading: true})

        await axios.post("http://localhost:9003/flights", {
            origin: self.state.origin,
            destination: self.state.destination,
            max: self.state.max,
            adults: self.state.adults,
            departureDate: self.state.departureDate,
            returnDate: self.state.returnDate,
            nonStop: self.state.nonStop,
            currency: self.state.currency
        }).then(function (response) {
            self.setState({
                flights: response.data,
                loading: false
            }, () => console.log(response.data))
        }).catch(function (error) {
            console.log(error)
        })
    }

  render() {

     const {flights, loading} = this.state

    return (
      <Fragment>
        <div className="content">
          <Row>
            <Col md="8">
              <Card>
                <CardHeader>
                  <h5 className="title">Search Low Fare Flights</h5>
                    <h7>Napomena: nisam našao gdje se prikazuje broj stajanja pa sam to izostavio <br/>
                    + frontend se može/treba još upicanit sa validacijama (formik & yup)</h7>
                </CardHeader>
                <CardBody>
                  <Form>
                    <Row>
                      <Col className="pr-md-1" md="5">
                        <FormGroup>
                          <label>Origin</label>
                          <Input
                              defaultValue="MIL"
                              placeholder="Origin"
                              name="origin"
                              type="text"
                              value={this.state.origin}
                              onChange={this.handleChange}
                          />
                        </FormGroup>
                      </Col>
                      <Col className="px-md-1" md="3">
                        <FormGroup>
                          <label>Destination</label>
                          <Input
                              defaultValue="LON"
                              placeholder="destination"
                              name="destination"
                              type="text"
                              value={this.state.destination}
                              onChange={this.handleChange}
                          />
                        </FormGroup>
                      </Col>
                      <Col className="pl-md-1" md="4">
                        <FormGroup>
                          <label htmlFor="exampleInputEmail1">
                              Departure Date
                          </label>
                          <Input placeholder="Departure Date"
                                 defaultValue="2019-08-01"
                                 name="departureDate"
                                 type="text"
                                 value={this.state.departureDate}
                                 onChange={this.handleChange}
                          />
                        </FormGroup>
                      </Col>
                    </Row>
                    <Row>
                      <Col className="pr-md-1" md="4">
                        <FormGroup>
                          <label>Return Date</label>
                            <Input placeholder="Return Date"
                                   defaultValue="2019-08-28"
                                   type="text"
                                   name="returnDate"
                                   value={this.state.returnDate}
                                   onChange={this.handleChange}
                            />
                        </FormGroup>
                      </Col>
                      <Col className="pl-md-1" md="4">
                        <FormGroup>
                          <label>Adults</label>
                          <Input placeholder="1"
                                 type="number"
                                 name="adults"
                                 value={this.state.adults}
                                 onChange={this.handleChange}
                          />
                        </FormGroup>
                      </Col>
                        <Col className="pl-md-1" md="4">
                            <FormGroup>
                                <label>Currency</label>
                                <select name="currency" value={this.state.currency} onChange={this.handleChange}>
                                    <option value="EUR">EUR</option>
                                    <option value="USD">USD</option>
                                    <option value="HRK">HRK</option>
                                </select>
                            </FormGroup>
                        </Col>
                    </Row>
                  </Form>
                </CardBody>
                <CardFooter>
                  <Button className="btn-fill" color="primary" type="submit" onClick={this.fetchFlights}>
                    Search
                  </Button>
                </CardFooter>
              </Card>
            </Col>
            <Col md="4">
              <Card className="card-user">
                <CardHeader>
                  <h5 className="title">Login as Admin</h5>
                </CardHeader>
                <CardBody>
                  <Form>
                    <Row>
                      <Col className="pr-md-1" md="11">
                        <FormGroup>
                          <label htmlFor="exampleInputEmail1">
                            Email address
                          </label>
                          <Input placeholder="admin@admin.com"
                                 type="email"
                                 name="username"
                                 value={this.state.username}
                                 onChange={this.handleChange}
                          />
                        </FormGroup>
                      </Col>
                    </Row>
                    <Row>
                      <Col className="pr-md-1" md="11">
                        <FormGroup>
                          <label>Password</label>
                          <Input
                              defaultValue="admin"
                              placeholder="admin"
                              type="text"
                              name="password"
                              value={this.state.password}
                              onChange={this.handleChange}
                          />
                        </FormGroup>
                      </Col>
                    </Row>
                    <br/>
                    <div className="button-container">
                      <Button className=" btn-round" color="facebook" onClick={this.login}>
                        Login
                      </Button>
                    </div>
                  </Form>
                </CardBody>
              </Card>
            </Col>
          </Row>
          <Row>
            <Col md="12">
              <Card>
                <CardHeader>
                  <CardTitle tag="h4">Search results</CardTitle>
                </CardHeader>
                <CardBody>
                    { loading === true &&
                    <CardHeader>
                        <CardTitle tag="h4">Loading...</CardTitle>
                    </CardHeader>}
                    {flights.length !== 0 && flights !== undefined &&
                    <Table className="tablesorter" responsive>
                        <thead className="text-primary">
                        <tr>
                            <th>Origin</th>
                            <th>Destination</th>
                            <th>Departure date</th>
                            <th>Return date</th>
                            <th>Available seats</th>
                            <th>Total price</th>
                        </tr>
                        </thead>
                        <tbody>
                        {flights.map((flight, i) =>
                            <tr key={i}>
                                <td key={i}>{flight.origin}</td>
                                <td key={i}>{flight.destination}</td>
                                <td key={i}>
                                    <Moment format="YYYY/MM/DD">{flight.departureDate}</Moment>
                                </td>
                                <td key={i}>
                                    <Moment format="YYYY/MM/DD">{flight.returnDate}</Moment>
                                </td>
                                <td key={i}>{flight.adults}</td>
                                <td key={i}>{flight.priceTotal}</td>
                            </tr>
                        )}
                        </tbody>
                    </Table>}
                    {flights.length !== 0 && flights !== undefined &&
                  <ReactTable
                      data={flights}
                      columns={[
                          {
                              Header: "REACT TABLE",
                              columns: [
                                  {
                                      Header: "Origin",
                                      accessor: "origin"
                                  },
                                  {
                                      Header: "Destination",
                                      accessor: "destination"
                                  },
                                  {
                                      Header: "Departure date",
                                      accessor: "departureDate"
                                  },
                                  {
                                      Header: "Return date",
                                      accessor: "returnDate"
                                  },
                                  {
                                      Header: "Free seats",
                                      accessor: "adults"
                                  },
                                  {
                                      Header: "Total  price",
                                      accessor: "priceTotal"
                                  }
                              ]
                          }
                      ]}
                      defaultPageSize={5}
                      className="table-responsive"
                  />}
                </CardBody>
              </Card>
            </Col>
          </Row>
        </div>
      </Fragment>
    );
  }
}

export default Dashboard;
