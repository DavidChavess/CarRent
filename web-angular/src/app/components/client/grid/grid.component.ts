import { Component, OnInit } from '@angular/core';

import { TableColumn } from 'src/app/table/table';
import { Client } from '../client';

@Component({
  selector: 'app-grid',
  templateUrl: './grid.component.html',
  styleUrls: ['./grid.component.css']
})
export class GridComponent implements OnInit {

  constructor() { }

  columns: TableColumn[] = [
    {
      atribute: 'nome'
    },
    {
      atribute: 'cpf'
    },
    {
      atribute: 'dataNascimento'
    },
    {
      atribute: 'ativo'
    }
  ];

  content: Client[];

  ngOnInit(): void {
    this.content = [
      {
        nome: "teste",
        cpf: "484.855.226-50",
        dataNascimento: new Date(),
        ativo: true       
      },
      {
        nome: "teste 2",
        cpf: "999.855.226-50",
        dataNascimento: new Date(),
        ativo: false       
      },
    ]
  }

}
