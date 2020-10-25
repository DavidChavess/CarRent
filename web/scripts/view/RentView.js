class RentView extends View{
    template(rents){
        return `<table>
            <thead>
                <tr>
                    <th>Nome DO CLIENTE</th>
                    <th>STATUS</th>
                    <th>VEICULO ALUGADO</th>
                    <th>DATA DA LOCAÇÃO</th>
                    <th>DATA DA ENTREGA</th>
                    <th>VALOR TOTAL DO ALUGUEL</th>
                </tr>
            </thead>
            <tbody>
                ${rents.map( r => {
                    return `<tr id = ${'td-'+ r.getId()} >
                        <td>${r.getCustomer().name}</td>
                        ${r.getReturned() ? '<td style="color:blue ">Concluido </td>' : '<td style="color:red ">Aberto</td>'}      
                        <td>${r.getVehicle().name}</td>
                        <td>${r.getStartRent()}</td>
                        <td>${r.getEndRent() === null ? '' : r.getEndRent()}</td>
                        <td>${r.getValueTotal() === null ? '' : r.getValueTotal()}</td>
                        <td><button rel=${r.getId()} class="btn-devolver-aluguel">devolver aluguel</button></td>
                    </tr>`}).join('')}           
            </tbody>
        </table>`;   
    }
}