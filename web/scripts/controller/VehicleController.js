class VehicleController {
    constructor(){     

        const $ = document.querySelector.bind(document);

        this._marca = $('#marca');
        this._nome = $('#nome');
        this._ano = $('#ano');
        this._modelo = $('#modelo');
        this._combustivel = $('#combustivel');        
        this._valorPorDia = $('#valorPorDia');
        this._status = document.getElementsByName('status');

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
            
            //this._eventDelete();
            //this._eventEdit();
            this._cleanFields();  

            document.getElementById("container-modal").style.display = "none";

            this._mensagemView.update( new Mensagem("Veiculo inserido com sucesso!"));
            
        })
        .catch(err => {
            this._mensagemView.update( new Mensagem(err.response.data.error));
        })
    }
    
     findAll(){
        this._api.get('/vehicles')
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
            this._cleanFields();
            //this._eventDelete();
            //this._eventEdit();
 
        })
       .catch(err => {
           console.log(err);
           //this._mensagemView.update( new Mensagem(err.response.data.error) );
       })
   }

    _cleanFields(){
        this._nome.value = ''; 
        this._ano.value = 2000 
        this._modelo.value = 2000 
        this._combustivel.value = '' 
        this._valorPorDia.value = 0.0; 
        this._marca.value = '';
    }

    _newVehicle(){
        let status;

        for(let i = 0; i < this._status.length; i++){
            if (this._status[i].checked){
                status = this._status[i].value === "Alugado" ? true : false;
            }
        }
        
        return new Vehicle(null, 
            this._nome.value, 
            this._ano.value, 
            this._modelo.value, 
            this._combustivel.value, 
            this._valorPorDia.value, 
            status, 
            this._marca.value);
    }
}