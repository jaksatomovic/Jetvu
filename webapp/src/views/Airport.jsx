import React, {Component, Fragment} from "react";
// reactstrap components
import {
    Button,
    Card,
    CardHeader,
    CardBody,
    FormGroup,
    Input,
    Table,
    Row,
    Col,
    UncontrolledTooltip, Form, CardFooter, CardText
} from "reactstrap";
import axios from "axios";



class Airport extends Component {
    constructor(props) {
        super(props);

        this.state = {
            airports: [],
            name: '',
            iata: ''
        }
    }

    fetchAirports = async () => {
        let self = this

        await axios.get("http://localhost:9001/airports").then(function (response) {
            self.setState({
                airports: response.data
            }, () => console.log(response.data))
        }).catch(function (error) {
            console.log(error)
        })
    }

    addAirport = async () => {
        await axios.post("http://localhost:9001/airports", {
            name: this.state.name,
            iataCode: this.state.iata
        }).then(function (response) {
            this.setState({
                name: '',
                iata: ''
            }, () => {
                this.fetchAirports();
            })
        }).catch(function (error) {
            console.log(error)
        })
        await window.location.reload();
    }

    handleChange = (event) => {
        let name = event.target.name
        let value = event.target.type === 'checkbox' ? event.target.checked : event.target.value

        this.setState(prevState => {
            prevState[name] = value
            return prevState
        })
    }

    componentDidMount() {
        this.fetchAirports()
    }

    render() {
        const { airports } = this.state
        return (
            <Fragment>
                <div className="content">
                    <Row>
                        <Col md="8">
                            <Card>
                                <CardHeader>
                                    <h5 className="title">All Airports</h5>
                                </CardHeader>
                                <CardBody>
                                    {airports.length !== 0 && airports !== undefined &&
                                    <Table className="tablesorter" responsive>
                                        <thead className="text-primary">
                                        <tr>
                                            <th key={1}>Name</th>
                                            <th key={2}>IATA Code</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        {airports.map((airport, i) =>
                                            <tr key={i}>
                                                <td key={airport.id}>{airport.name}</td>
                                                <td key={airport.id}>{airport.iata}</td>
                                            </tr>
                                        )}
                                        </tbody>
                                    </Table>}
                                </CardBody>
                            </Card>
                        </Col>
                        <Col md="4">
                            <Card className="card-user">
                                <CardHeader>
                                    <h5 className="title">Add new airport</h5>
                                </CardHeader>
                                <CardBody>
                                    <Form>
                                        <Row>
                                            <Col className="pr-md-1" md="11">
                                                <FormGroup>
                                                    <label>Airport name</label>
                                                    <Input
                                                        defaultValue="London"
                                                        placeholder="Airport name"
                                                        type="text"
                                                        name="name"
                                                        value={this.state.name}
                                                        onChange={this.handleChange}
                                                    />
                                                </FormGroup>
                                            </Col>
                                        </Row>
                                        <Row>
                                            <Col className="pr-md-1" md="11">
                                                <FormGroup>
                                                    <label>IATA Code</label>
                                                    <Input
                                                        defaultValue="LON"
                                                        placeholder="IATA Code"
                                                        type="text"
                                                        name="iata"
                                                        value={this.state.iata}
                                                        onChange={this.handleChange}
                                                    />
                                                </FormGroup>
                                            </Col>
                                        </Row>
                                    </Form>
                                </CardBody>
                                <CardFooter>
                                    <Button className="btn-fill" color="primary" type="submit" onClick={this.addAirport}>
                                        Save
                                    </Button>
                                </CardFooter>
                            </Card>
                        </Col>
                    </Row>
                </div>
            </Fragment>
        );
    }
}

export default Airport;