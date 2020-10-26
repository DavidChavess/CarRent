class RentView extends View{
    template(rents){
        return `<table>
            <thead>
                <tr>
                    <th>CODIGO</th>
                    <th>CLIENTE</th>
                    <th>COD CLIENTE</th>
                    <th>VEICULO</th>
                    <th>COD VEICULO</th>
                    <th>DATA DA LOCAÇÃO</th>
                    <th>DATA DA ENTREGA</th>
                    <th>TOTAL</th>
                    <th>STATUS</th>
                </tr>
            </thead>
            <tbody>
                ${rents.map( r => {
                    return `<tr id = ${'td-'+ r.getId()} >
                        <td>${r.getId()}</td>
                        <td>${r.getCustomer().name}</td>
                        <td>${r.getCustomer().id}</td>
                        <td>${r.getVehicle().name}</td>
                        <td>${r.getVehicle().id}</td>
                        <td>${ DateConverter.dateFormatPtBr(r.getStartRent()) }</td>
                        <td>${ r.getEndRent() === null ? '' : DateConverter.dateFormatPtBr(r.getEndRent()) }</td>
                        <td>${r.getValueTotal() === null ? '' : r.getValueTotal()}</td>
                        ${r.getReturned() ? '<td style="color:blue ">Concluido </td>' : '<td style="color:red ">Aberto</td>'}      
                        <td><button rel=${r.getId()} class="btn-devolver-aluguel">devolver aluguel</button></td>
                    </tr>`
                }).join('')}           
            </tbody>
        </table>`;   
    }
}