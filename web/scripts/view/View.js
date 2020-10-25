class View{
    constructor(seletor){
        this._elemento = document.getElementById(seletor);
    }
  
    update(model, apagar, editar){
        this._elemento.innerHTML = this.template(model, apagar, editar);
    }
    
    template(model){
        throw new Error("o metodo template precisa ser implementado");
    }
}
