class VehicleView extends View{
    template(vehicles){
        return `<table>
            <thead>
                <tr>
                    <th>Codigo</th>
                    <th>MARCA</th>
                    <th>NOME</th>
                    <th>ANO</th>
                    <th>MODELO</th>
                    <th>COMBUSTIVEL</th>
                    <th>VALOR POR DIA</th>
                    <th>STATUS</th>                    
                </tr>
            </thead>
            <tbody>
                ${vehicles.map( v => {
                    return `<tr id = ${'td-'+ v.getId()} >
                        <td>${v.getId()}</td>
                        <td>${v.getBrand()}</td>
                        <td>${v.getName()}</td>
                        <td>${v.getYear()}</td>
                        <td>${v.getModel()}</td>
                        <td>${v.getFuel()}</td>
                        <td>${v.getValuePerDay()}</td>
                        ${v.isRent() ? '<td style="color:red ">Alugado </td>' : '<td style="color:blue ">Disponivel</td>'}      
                        <td><button rel=${v.getId()} class="btn-deletar">deletar</button></td>
                        <td><button rel=${v.getId()} class="btn-editar">editar</button></td>
                    </tr>`
                }).join('')}           
            </tbody>
        </table>`;   
    }
}