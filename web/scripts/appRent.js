const controller = new RentController();
document.getElementById("formulario-cadastro").onsubmit = controller.insert.bind(controller);

popularCheckbox("vehicles", "veiculos")
popularCheckbox("customers", "clientes")


async function popularCheckbox (endpoint, idCheckbox){
    const checkbox = document.getElementById(idCheckbox);

    await controller._api.get(`/${endpoint}`)
    .then(response => {    
        
        if (idCheckbox === "veiculos"){
            /// filtra os veiculos disponiveis
            checkbox.innerHTML =  response.data.filter(x => !x.rent)
            .map(x => `<option value=${x.id} >${x.name}</option>`).join('');
        }else{
            // filtra os clientes ativos
            checkbox.innerHTML =  response.data.filter(x => x.active)
            .map(x => `<option value=${x.id} >${x.name}</option>`).join(''); 
        }
    })
    .catch(err => {
        console.log(err);
    })
}

function showModal(event){
    document.getElementById("container-modal").style.display = "flex";
}

function closeModal(event){
    document.getElementById("container-modal").style.display = "none";
}

//controller.findAll();
