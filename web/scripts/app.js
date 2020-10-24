const btnMostrar = document.getElementById('adicionar');

const formulario = document.forms[0];


const controller = new CustomerController();
formulario.onsubmit = controller.insert.bind(controller);

//const controller = new SpentController();
//form.addEventListener('submit', controller.insert.bind(controller));
//formSearch.addEventListener('submit', controller.findById.bind(controller))
//btnFindAll.addEventListener('click',  controller.findAll.bind(controller));

