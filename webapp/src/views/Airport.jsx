import React, {Component, Fragment} from "react";
import classNames from "classnames";
// reactstrap components
import {
    Button,
    ButtonGroup,
    Card,
    CardHeader,
    CardBody,
    CardTitle,
    DropdownToggle,
    DropdownMenu,
    DropdownItem,
    UncontrolledDropdown,
    Label,
    FormGroup,
    Input,
    Table,
    Row,
    Col,
    UncontrolledTooltip, Form, CardFooter, CardText
} from "reactstrap";



class Airport extends Component {
    constructor(props) {
        super(props);
    }
    render() {
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
                                {/*   Table*/}
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
                                                    <label>Username</label>
                                                    <Input
                                                        defaultValue="michael23"
                                                        placeholder="Username"
                                                        type="text"
                                                    />
                                                </FormGroup>
                                            </Col>
                                        </Row>
                                        <Row>
                                            <Col className="pr-md-1" md="11">
                                                <FormGroup>
                                                    <label>Country</label>
                                                    <Input
                                                        defaultValue="Andrew"
                                                        placeholder="Country"
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