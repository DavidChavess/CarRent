import { Component, OnInit } from '@angular/core';
import { TableColumn } from 'src/app/table/table';
import { Rent } from '../rent';
import { RentService } from '../rent.service';

@Component({
  selector: 'app-rent-grid',
  templateUrl: './rent-grid.component.html',
  styleUrls: ['./rent-grid.component.css']
})
export class RentGridComponent implements OnInit {

  columns: TableColumn[] = [
    { name: "Cliente", source: "cliente.nome"},
    { name: "Cpf do cliente", source: "cliente.cpf"},
    { name: "Inicio do aluguel", source: "inicioDoAluguel" },
    { name: "Final do aluguel", source: "finalDoAluguel" },
    { name: "Valor total", source: "valorTotal" },
    { name: "Já retornou", source: "jaRetornou" }
  ]

  content: Rent[];

  rowAction: Function = () => {};

  constructor(private _service: RentService) { }

  ngOnInit(): void {

    this.content = this._service.getRents();

    this.rowAction = ()=>{
      alert("funcionou")
    }
  }

}
