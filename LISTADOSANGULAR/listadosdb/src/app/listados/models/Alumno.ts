import { Usuario } from "./Usuario";

export class Alumno{
    usuarioCodUsuario!:number;
    nombresApellidos!:string;
    estado!:string;
    diasInhabilitado!:number;
    usuario?:Usuario;
}