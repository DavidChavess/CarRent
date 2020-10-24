class DateConverter{
    constructor(){
        throw new Error("esta classe não pode ser instanciada");
    }

    static dateToString(data){ 
        return `${data.getDate()}/${data.getMonth() + 1}/${data.getFullYear()} ${data.getHours()}:${data.getMinutes()}:${data.getSeconds()}`; 
    }
}
