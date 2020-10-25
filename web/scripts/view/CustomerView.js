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
                    return `<tr id = ${'td-'+ c.getId()} >
                        <td>${c.getCpf()}</td>
                        <td>${c.getName()}</td>
                        <td>${c.getBirthdate()}</td>
                        <td>${c.getActive()  ? 'Ativo' : 'Inativo'}</td>
                        <td><button rel=${c.getId()} class="btn-deletar">deletar</button></td>
                        <td><button rel=${c.getId()} class="btn-editar">editar</button></td>
                    </tr>`}).join('')}           
            </tbody>
        </table>`;   
    }
}