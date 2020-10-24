class CustomerView extends View{
    template(customers){
        return `<table>
            <thead>
                <tr>
                    <th>CPF</th>
                    <th>NOME</th>
                    <th>DATA NASCIMENTO</th>
                    <th>STATUS</th>
                </tr>
            </thead>
            <tbody>
                ${customers.map( c => {
                    return `<tr>
                        <td>${c.getCpf()}</td>
                        <td>${c.getName()}</td>
                        <td>${c.getBirthdate()}</td>
                        <td>${c.getActive()}</td>
                        <td>
                            <form>
                                <input type="hidden" value=${c.getId()} />
                                <input type='submit' value = 'editar' />
                            <form/>
                        </td>
                        <td>
                            <form>
                                <input type="hidden" value=${c.getId()} />
                                <input type='submit' value = 'deletar' />
                            <form/>
                        </td>
                    </tr>`}).join('')}           
            </tbody>
        </table>`;   
    }

    details(){
        return document.getElementsByClassName('details');
    }
}