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
            airports: []
        }
    }

    fetchAirports = () => {
        let self = this

        axios.get("http://localhost:9001/airports", {
            headers: {
                'Access-Control-Allow-Origin': 'http://localhost:3000',
                // 'Access-Control-Allow-Methods': 'DELETE, POST, GET, OPTIONS',
                'Content-Type': 'application/json',
                'Access-Control-Allow-Credentials': 'true'
            },
            routes: {
                "cors": true
            }
        }).then(function (response) {
            self.setState({
                airports: response.data
            }, () => console.log(response.data))
        }).catch(function (error) {
            console.log(error)
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
                                                    />
                                                </FormGroup>
                                            </Col>
                                        </Row>
                                    </Form>
                                </CardBody>
                                <CardFooter>
                                    <Button className="btn-fill" color="primary" type="submit">
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