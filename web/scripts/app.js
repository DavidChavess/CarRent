const controller = new CustomerController();
document.forms[0].onsubmit = controller.insert.bind(controller);

function showModal(event){
    document.getElementById("container-modal").style.display = "flex";
}

function closeModal(event){
    document.getElementById("container-modal").style.display = "none";
}

controller.findAll();
