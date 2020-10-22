package com.shx.locacao.veiculos.model.enumeration;

// essa clase é um tipo unumerado onde você tem todas as opções de combustivel
public enum Fuel {

    ETANOL(1),
    GASOLINA(2),
    FLEX(3),
    GNV(4);

    private Integer cod;

    private Fuel(Integer cod ){
        this.cod = cod;
    }

    public Integer getCod() {
        return cod;
    }

    public static Fuel toEnum(Integer cod){
        if(cod == null) return null;

        for(Fuel c : Fuel.values()){
            if (c.getCod().equals(cod)){
                return c;
            }
        }

        throw new IllegalArgumentException("Não foi possivel achar o combustivel para o codigo "+ cod);
    }
}
