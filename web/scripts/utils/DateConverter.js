class DateConverter{
    constructor(){
        throw new Error("esta classe não pode ser instanciada");
    }

    static dateFormatPtBr(data){ 
        return data.split('-').reverse().join('/'); 
    }
}
