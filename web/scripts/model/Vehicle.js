class Vehicle{
    constructor(id, name, year, model, fuel, valuePerDay, rent, brand){
        this.id = id;
        this.name = name;
        this.year = year;
        this.model = model;
        this.fuel = fuel;
        this.valuePerDay = valuePerDay;
        this.rent = rent;
        this.brand = brand;
    }

     getId() {
        return this.id;
    }

     setId(id) {
        this.id = id;
    }

    getName() {
        return this.name;
    }

    setName(name) {
        this.name = name;
    }

    getYear() {
        return this.year;
    }

    setYear(year) {
        this.year = year;
    }

    getModel() {
        return this.model;
    }

    setModel(model) {
        this.model = model;
    }

    getFuel() {
        return this.fuel;
    }

    setFuel(fuel) {
        this.fuel = fuel;
    }

    getValuePerDay() {
        return this.valuePerDay;
    }

    setValuePerDay(valuePerDay) {
        this.valuePerDay = valuePerDay;
    }

    isRent() {
        return this.rent;
    }

    setRent(rent) {
        this.rent = rent;
    }

    getBrand() {
        return this.brand;
    }

    setBrand(brand) {
        this.brand = brand;
    }

}