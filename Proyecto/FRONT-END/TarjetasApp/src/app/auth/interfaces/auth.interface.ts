export interface AuthResponse{
    ok:       boolean;    
    idUser?:   string;
    userName?: string;
    token?:    string;
    role?:      string;
}

export interface Usuario{
    idUser:   string;
    userName: string;
    ok:       boolean;
    rol:      string;    
}