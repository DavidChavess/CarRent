class RentController {
    constructor(){      

        const $ = document.querySelector.bind(document);

        this._veiculos = $('#veiculos');
        this._clientes = $('#clientes');
        this._clientesPesquisa = $('#pesquisa-clientes');
       
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
            this._returnedRent();

            document.getElementById("container-modal").style.display = "none";

            this._mensagemView.update( new Mensagem("Aluguel realizado com sucesso!"));
            
        })
        .catch(err => {
            console.log(err);
            this._mensagemView.update( new Mensagem(err.response.data.error));
        })
    } 

    async findAll(){
        await this._api.get('/rents')
       .then(response => {    
           
            this._rents.length = 0;
             
           response.data.forEach(r => {
                this._rents.push( new Rent(
                    r.id,
                    r.customer,
                    r.vehicle,
                    r.startRent,
                    r.endRent,
                    r.valueTotal,
                    r.returned
                ));
            });

            this._view.update(this._rents);
            this._returnedRent();
        })
       .catch(err => {
           console.log(err);
       })
   }

   _returnedRent(){
        const btnAluguel = document.getElementsByClassName("btn-devolver-aluguel");
        
        for ( let i=0; i < btnAluguel.length; i++ ){

            btnAluguel[i].addEventListener("click", async (event) => {

                event.preventDefault();
                const id = event.target.getAttribute('rel');
                await this._api.patch(`/rents/${id}`, { returned : true })
                .then(response =>{

                    const rent = new Rent(
                        response.data.id,
                        response.data.customer,
                        response.data.vehicle,
                        response.data.startRent,
                        response.data.endRent,
                        response.data.valueTotal,
                        response.data.returned
                    );
        
                    this._rents[i] = rent;
                    this._view.update(this._rents); 
                })
                .catch(err => {
                    this._mensagemView.update( new Mensagem(err.response.data.error));
                })                
            })
        }   
    }

    async pesquisa(event){
        event.preventDefault();

        await this._api.get(`/customers/${this._clientesPesquisa.value}/rents`)
        .then(response => {    

            this._rents.length = 0;

            response.data.forEach(r => {
                 this._rents.push( new Rent(
                     r.id,
                     r.customer,
                     r.vehicle,
                     r.startRent,
                     r.endRent,
                     r.valueTotal,
                     r.returned
                 ));
             });


             this._view.update(this._rents);
             this._returnedRent();
         })
        .catch(err => {
            console.log(err);
        })
    }

    _newRent(){
        return { "customerId" : this._clientes.value, "vehicleId" : this._veiculos.value };
    }
}