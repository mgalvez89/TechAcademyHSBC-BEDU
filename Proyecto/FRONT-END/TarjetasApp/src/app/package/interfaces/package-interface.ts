export interface Package {
    idPackage:   number;
    namePackage: string;
    numberCards: number;
    nameProduct: string;
    status:      string;
    location:    aLocation;
    request:     Solicitud;
    branch:      Branch;
}

export interface aLocation {
    idLocation:   number;
    nameLocation: string;
    capacity:     number;
    spaceUsed:    number;
    freeSpace:    number;
}


export interface Solicitud {
    idRequest:    number;
    typeRequest:  string;
    storageDate:  Date;
    deliveryDate: null;
    requestDate:  null;
    fileName:     string;
    filePath:     null;
    status:       string;
}

export interface Branch {
    idBranch:     number;
    nameBranch:   string;
    numberBranch: string;
    module:       Module;
    tipoSucursal: string;
    territorial:  string;
    regional:     string;
    calle:        string;
    colonia:      string;
    municipio:    string;
    ciudad:       string;
    estado:       string;
    cp:           number;
    horario:      string;
    director:     string;
    telefono:     string;
    email:        string;
    status:       string;
    idRuta:       number;
}

export interface Module {
    idModule:    number;
    modulo:      string;
    cve_mod:     number;
    nameModule:  string;
    responsable: string;
    calle:       string;
    colonia:     string;
    municipio:   string;
    ciudad:      string;
    cp:          number;
    estado:      string;
    telefono:    string;
    email:       string;
    estatus:     number;
}




