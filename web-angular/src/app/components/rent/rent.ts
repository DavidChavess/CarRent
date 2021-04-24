import { Client } from "../client/client";
import { Vehicle } from "../vehicle/vehicle";

export interface Rent{
    veiculo: Vehicle;
    cliente: Client,
    inicioDoAluguel: Date;
    finalDoAluguel: Date;
    valorTotal: number;
    jaRetornou: boolean;  
}