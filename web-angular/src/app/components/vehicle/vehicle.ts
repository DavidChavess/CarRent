export type Fuel = 'ETANOL' | 'GASOLINA' | 'FLEX' | 'GNV'; 

export interface Vehicle{
    id: number;
    nome: string;
    ano: number;
    modelo: number;
    tipoCombustivel: Fuel;
    valorPorDia: number;
    estaAlugado: boolean;
    marca: string;
}