class VehicleController {
    constructor(){     

        const $ = document.querySelector.bind(document);

        this._marca = $('#marca');
        this._nome = $('#nome');
        this._ano = $('#ano');
        this._modelo = $('#modelo');
        this._combustivel = $('#combustivel');        
        this._valorPorDia = $('#valorPorDia');
        this._status = false;

        this._vehicles = []; 
        this._view = new VehicleView("tabela");
        this._mensagemView = new MensagemView("mensagem");

        this._api = axios.create({
            baseURL: "http://localhost:8080/locacao-veiculos-services",
        });

    }

    async insert(event){
        event.preventDefault();
        
        await this._api.post('/vehicles', this._newVehicle())
        .then(response => {    
            
            const vehicle = new Vehicle(
                response.data.id,
                response.data.name, 
                response.data.year, 
                response.data.model, 
                response.data.fuel, 
                response.data.valuePerDay, 
                response.data.rent, 
                response.data.brand);
            
            this._vehicles.push(vehicle);

            this._view.update(this._vehicles); 
            
            this._eventDelete();
            this._eventEdit();
            this._cleanFields();  

            document.getElementById("container-modal").style.display = "none";

            this._mensagemView.update( new Mensagem("Veiculo inserido com sucesso!"));
            
        })
        .catch(err => {
            this._mensagemView.update( new Mensagem(err.response.data.error));
        })
    }
    
    async findAll(){
        await this._api.get('/vehicles')
       .then(response => {    
           
           response.data.forEach(v => {
                this._vehicles.push( new Vehicle(
                    v.id,
                    v.name, 
                    v.year, 
                    v.model, 
                    v.fuel, 
                    v.valuePerDay, 
                    v.rent, 
                    v.brand));
            });

            this._view.update(this._vehicles);
            this._eventDelete();
            this._eventEdit();
 
        })
       .catch(err => {
           console.log(err);
           //this._mensagemView.update( new Mensagem(err.response.data.error) );
       })
   }

   _eventDelete(){
        const buttonsDelete = document.getElementsByClassName("btn-deletar");
        
        for ( let i=0; i < buttonsDelete.length; i++ ){

            buttonsDelete[i].addEventListener("click", async (event) => {

                event.preventDefault();
                const id = event.target.getAttribute('rel');

                await this._api.delete(`/vehicles/${id}`)
                .then(()=>{
                    this._mensagemView.update( new Mensagem("Veiculo deletado com sucesso") );
                    document.getElementById('td-'+id).remove();
                    this._vehicles.splice(i, 1);
                })
                .catch(err => {
                    this._mensagemView.update( new Mensagem(err.response.data.error));
                })                
            })
        }   
    }

    _eventEdit(){
        const buttonsEdit = document.getElementsByClassName("btn-editar");
         
        for ( let i=0; i < buttonsEdit.length; i++ ){

            buttonsEdit[i].addEventListener("click", (event => {

                event.preventDefault();
                const id = event.target.getAttribute('rel');
                
                this._nome.value = this._vehicles[i].getName(); 
                this._ano.value = this._vehicles[i].getYear(); 
                this._modelo.value = this._vehicles[i].getModel(); 
                this._combustivel.value = this._vehicles[i].getFuel();
                this._valorPorDia.value = this._vehicles[i].getValuePerDay(); 
                this._marca.value = this._vehicles[i].getBrand();
                this._status = this._vehicles[i].isRent();
            
                const modal = document.getElementById("container-modal");
                modal.style.display = "flex";

                const formulario =  document.getElementById("formulario-cadastro");

                formulario.onsubmit = async (ev) => {
                    
                    ev.preventDefault();

                    await this._api.put(`/vehicles/${id}`, this._newVehicle())
                    .then( response =>{
                           
                        const vehicle = new Vehicle(
                            response.data.id,
                            response.data.name, 
                            response.data.year, 
                            response.data.model, 
                            response.data.fuel, 
                            response.data.valuePerDay, 
                            response.data.rent, 
                            response.data.brand);
            
                        this._vehicles[i] = vehicle;
                        this._view.update(this._vehicles); 
                        this._eventDelete();
                        this._eventEdit();
                        this._cleanFields();  
                        this._mensagemView.update( new Mensagem("Veiculo atualizado com sucesso") );
                        modal.style.display = "none";

                    })
                    .catch(err => {
                        this._mensagemView.update( new Mensagem(err.response.data.error));
                    })
                }              
            }))
        }
    }

    _cleanFields(){
        this._nome.value = ''; 
        this._ano.value = 2000 
        this._modelo.value = 2000 
        this._combustivel.value = '' 
        this._valorPorDia.value = "0,0"; 
        this._marca.value = '';
        this._status = false;
    }

    _newVehicle(){       
        return new Vehicle(null, 
            this._nome.value, 
            this._ano.value, 
            this._modelo.value, 
            this._combustivel.value, 
            this._valorPorDia.value.replace(",", "."), 
            this._status, 
            this._marca.value);
    }
}