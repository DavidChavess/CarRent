class Customer{
    constructor(id, cpf, name, birthdate, active){
        this.id = id;
        this.cpf = cpf;
        this.name = name;
        this.birthdate = birthdate;
        this.active = active;
    }

    getId (){
        return this.id;
    }

    setId (id){
        this.id = id;
    }

    getCpf (){
        return this.cpf;
    }

    setCpf (cpf){
        this.cpf = cpf;
    }

    getName (){
        return this.name;
    }

    setName (name){
        this.name = name;
    }

    
    getBirthdate (){
        return this.birthdate;
    }

    setBirthdate (birthdate){
        this.birthdate = birthdate;
    }   

    getActive (){
        return this.active;
    }

    setActive (active){
        this.active = active;
    } 
   

}