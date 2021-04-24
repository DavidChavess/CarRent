import { Injectable } from '@angular/core';
import { Rent } from './rent';

@Injectable({
  providedIn: 'root'
})
export class RentService {

  constructor() { }

  getRents() : Rent[]{
    return [
      {
        cliente:{
          nome: "teste de nome grandde",
          cpf: "484.855.226-50",
          dataNascimento: new Date(),
          ativo: true       
        },
        veiculo: {
          id: 1,
          nome: "vectra",
          modelo: 2000,
          ano: 1999,
          tipoCombustivel: 'GASOLINA',
          valorPorDia: 5.89,
          estaAlugado: true,
          marca: 'chevrolet'
        },
        finalDoAluguel: new Date(),
        inicioDoAluguel: new Date(),
        jaRetornou: false,
        valorTotal: 0.0
      },
      {
        cliente:{
          nome: "Fulano 2 teste",
          cpf: "484.855.226-50",
          dataNascimento: new Date(),
          ativo: true       
        },
        veiculo: {
          id: 1,
          nome: "vectra",
          modelo: 2000,
          ano: 1999,
          tipoCombustivel: 'GASOLINA',
          valorPorDia: 5.89,
          estaAlugado: true,
          marca: 'chevrolet'
        },
        finalDoAluguel: new Date(),
        inicioDoAluguel: new Date(),
        jaRetornou: false,
        valorTotal: 0.0
      }
    ]
  }
}
