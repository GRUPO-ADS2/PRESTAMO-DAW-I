import { Component } from '@angular/core';
import { Usuario } from '../../models/Usuario';
import Swal from 'sweetalert2';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-auth',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './auth.component.html'
})
export class AuthComponent {

  user : Usuario;

  constructor() {
    this.user = new Usuario();
  }

  OnSubmit(){
    if(!this.user.username || !this.user.password){
      Swal.fire({
        title: 'Error',
        text: 'Username o password incorrectos',
        icon: 'error'
      })
    }else{
      Swal.fire({
        title: 'Bienvenido',
        text: 'Usuario logueado correctamente',
        icon: 'success'
      })
    }
  }
}
