class MensagemView extends View{
    template(model){
        return model.msg ? `<p>${model.msg}</p>` : '';
    }
}