class RentController {
    constructor(){      

        const $ = document.querySelector.bind(document);

        this._veiculos = $('#veiculos');
        this._clientes = $('#clientes');
       
        this._rents = []; 
        this._view = new RentView("tabela");
        this._mensagemView = new MensagemView("mensagem");

        this._api = axios.create({
            baseURL: "http://localhost:8080/locacao-veiculos-services",
        });

    }

    async insert(event){
        event.preventDefault();
        
        await this._api.post('/rents', this._newRent())
        .then(response => {    
            
            const rent = new Rent(
                response.data.id,
                response.data.customer,
                response.data.vehicle,
                response.data.startRent,
                response.data.endRent,
                response.data.valueTotal,
                response.data.returned
            );

            this._rents.push(rent);
            this._view.update(this._rents); 
           // this._eventEdit();

            document.getElementById("container-modal").style.display = "none";

            this._mensagemView.update( new Mensagem("Aluguel realizado com sucesso!"));
            
        })
        .catch(err => {
            console.log(err);
            this._mensagemView.update( new Mensagem(err.response.data.error));
        })
    } 

    _newRent(){
        return { "customerId" : this._clientes.value, "vehicleId" : this._veiculos.value };
    }
}