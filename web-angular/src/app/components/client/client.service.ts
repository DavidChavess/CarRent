import { Injectable } from '@angular/core';
import { Client } from './client';

@Injectable({
  providedIn: 'root'
})
export class ClientService {

  constructor() { }

  getClients(): Client[]{
    return [
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
      }
    ]
  }

}
