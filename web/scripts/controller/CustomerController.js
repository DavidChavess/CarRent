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
            baseURL: "http://localhost:8080/locacao-veiculos-services",
        });

    }

    async insert(event){
        event.preventDefault();
        
        await this._api.post('/customers', this._newCustomer())
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
            this._eventDelete();
            this._eventEdit();
            this._cleanFields();  

            document.getElementById("container-modal").style.display = "none";

            this._mensagemView.update( new Mensagem("Cliente inserido com sucesso!"));
            
        })
        .catch(err => {
            this._mensagemView.update( new Mensagem(err.response.data.error));
        })
    }

    async findAll(){
         await this._api.get('/customers')
        .then(response => {    
            
            response.data.forEach(c => {
                this._customers.push( new Customer(c.id, c.cpf, c.name, c.birthdate, c.active) );
            })

            this._view.update(this._customers);
            this._cleanFields();
            this._eventDelete();
            this._eventEdit();

        })
        .catch(err => {
            this._mensagemView.update( new Mensagem(err.response.data.error) );
        })
    }

    _eventDelete(){
        const buttonsDelete = document.getElementsByClassName("btn-deletar");
         
        for ( let i=0; i < buttonsDelete.length; i++ ){

            buttonsDelete[i].addEventListener("click", async (event) => {

                event.preventDefault();
                const id = event.target.getAttribute('rel');

                await this._api.delete(`/customers/${id}`)
                .then(()=>{
                    this._mensagemView.update( new Mensagem("Cliente deletado com sucesso") );
                    document.getElementById('td-'+id).remove();
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
            
                this._nome.value = this._customers[i].getName();
                this._cpf.value = this._customers[i].getCpf();
                this._dtNascimento.value = this._customers[i].getBirthdate();

                const modal = document.getElementById("container-modal");
                modal.style.display = "flex";

                document.getElementById("formulario-cadastro").onsubmit = async (ev) => {
                    
                    ev.preventDefault();

                    await this._api.put(`/customers/${id}`, this._newCustomer())
                    .then( response =>{
                           
                        const customer = new Customer(
                            response.data.id, 
                            response.data.cpf, 
                            response.data.name, 
                            response.data.birthdate, 
                            response.data.active
                        );
            
                        this._customers[i] = customer;
                        this._view.update(this._customers); 
                        this._eventDelete();
                        this._cleanFields();  
                        this._mensagemView.update( new Mensagem("Cliente atualizado com sucesso") );
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