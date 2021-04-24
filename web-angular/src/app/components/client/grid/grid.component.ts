import { Component, OnInit } from '@angular/core';

import { TableColumn } from 'src/app/table/table';
import { Client } from '../client';
import { ClientService } from '../client.service';

@Component({
  selector: 'app-grid',
  templateUrl: './grid.component.html',
  styleUrls: ['./grid.component.css']
})
export class GridComponent implements OnInit {

  columns: TableColumn[] = [
    {
      name: "nome",
      source: 'nome'
    },
    {
      name: "cpf",
      source: 'cpf'
    },
    {
      name: "Data de nascimento",
      source: 'dataNascimento'
    },
    {
      name: "Esta ativo",
      source: 'ativo'
    }
  ];

  content: Client[];

  rowAction: Function;

  constructor(private _service: ClientService) { }

  ngOnInit(): void {
    this.content = this._service.getClients();
    this.rowAction = () => {
      alert("Cliente");
    }
  }

}
