class CustomerController {
    constructor(){      

        const $ = document.querySelector.bind(document);

        this._nome = $('#nome');
        this._dtNascimento = $('#dt-nascimento');
        this._cpf = $('#cpf');
        this._status = document.getElementsByName('status');

        this._customers = []; 
        this._view = new CustomerView("tabela");
        this._mensagemView = new MensagemView("mensagem");

        this._api = axios.create({
            baseURL: "http://localhost:8080/locacao-veiculos-services"
        });

    }

    insert(event){
        event.preventDefault();
        
        this._api.post('/customers', this._newCustomer())
        .then(response => {    
            
            const customer = new Customer(
                response.data.id, 
                response.data.cpf, 
                response.data.name, 
                response.data.birthdate, 
                response.data.active
            );

            this._customers.push(customer);
            this._view.update(this._customers); 
            this._cleanFields();  

            this._mensagemView.update( new Mensagem("Cliente inserido com sucesso!") );
            
        })
        .catch(err => {
            this._mensagemView.update( new Mensagem(err.response.data.error) );
        })
    }

    _cleanFields(){
        this._cpf.value = 0;
        this._nome.value = '',
        this._dtNascimento.value = '';

    }

    _newCustomer(){
        let status;

        for(let i = 0; i < this._status.length; i++){
            if (this._status[i].checked){
                status = this._status[i].value === "ativo" ? true : false;
            }
        }
        
        return new Customer(null, this._cpf.value, this._nome.value, this._dtNascimento.value, status);
        
    }
}