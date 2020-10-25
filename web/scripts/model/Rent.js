class Rent{
    constructor(id, customer, vehicle, startRent, endRent, valueTotal, returned){
        this.id = id;
        this.customer = customer;
        this.vehicle = vehicle;
        this.startRent = startRent;
        this.endRent = endRent;
        this.valueTotal = valueTotal;
        this.returned = returned;
    }

    getId (){
        return this.id;
    }

    setId (id){
        this.id = id;
    }

    getCustomer() {
        return this.customer;
    }

    setCustomer(customer) {
        this.customer = customer;
    }

    getVehicle() {
        return this.vehicle;
    }

    setVehicle(vehicle) {
        this.vehicle = vehicle;
    }

    getStartRent() {
        return this.startRent;
    }

    setStartRent(startRent) {
        this.startRent = startRent;
    }

    getEndRent() {
        return this.endRent;
    }

    setEndRent(endRent) {
        this.endRent = endRent;
    }

    getValueTotal() {
        return this.valueTotal;
    }

    setValueTotal(valueTotal) {
        this.valueTotal = valueTotal;
    }

    getReturned() {
        return this.returned;
    }

    setReturned(returned) {
        this.returned = returned;
    }
}