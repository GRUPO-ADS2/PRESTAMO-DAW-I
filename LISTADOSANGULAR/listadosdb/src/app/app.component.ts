import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, RouterLink, RouterLinkActive, RouterOutlet } from '@angular/router';
import { SolicitudComponent } from './listados/components/solicitud/solicitud.component';
import { PrestamoComponent } from './listados/components/prestamo/prestamo.component';
import { PenalizacionComponent } from './listados/components/penalizacion/penalizacion.component';
import { InfiniteScrollDirective } from 'ngx-infinite-scroll';
import { RegistrarPrestamoComponent } from './listados/components/registrar-prestamo/registrar-prestamo.component';
import SockJS from 'sockjs-client';
import { Client, IStompSocket } from '@stomp/stompjs';
import { FormsModule } from '@angular/forms';
import { SharingDataService } from './listados/services/sharing-data.service';
import { AuthService } from './listados/services/auth.service';
import { error } from 'console';
import Swal from 'sweetalert2';
import { CommonModule } from '@angular/common';
import { response } from 'express';


@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, RouterLink,
    RouterLinkActive, FormsModule, CommonModule
  ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit {

  title = 'listadosdb';
  private client!: Client;
  constructor(private sharingData: SharingDataService,
    private authServide: AuthService,
    private route: ActivatedRoute,
    private router: Router) { }

    ngOnInit(): void {
      this.handlerLogin();
      if (this.admin) {
        this.client = new Client();
        this.client.webSocketFactory = () => {        
          return new SockJS("http://localhost:8081/chat-websocket") as IStompSocket;
        }
        this.client.onConnect = (frame) => {
          console.log('Conectados: ' + this.client.connected + ' : ' + frame);
          this.client.subscribe('/chat/admin', mensaje => {
            console.log(mensaje.body);
            Swal.fire({
              position: "bottom-start",
              width: 200,
              heightAuto: true,
              icon: "warning",              
              title: mensaje.body,
              showConfirmButton: false,
              timer: 7500,
              customClass: {
                popup: 'small-alert', // Clase personalizada para el popup
                title: 'small-alert-title', // Clase personalizada para el título
                icon: 'small-alert-icon' // Clase personalizada para el icono
              },
              backdrop: false
            });
          });
        };
        this.client.onStompError = (frame) => {
          console.log('Error: ' + frame.headers['message'] + '\n' + frame.body);
        };
        this.client.activate();
      }
    }

  handlerLogin() {
    this.sharingData.handlerLoginEventEmitter.subscribe(({ username, contrasenia }) => {
      console.log(username, contrasenia)

      this.authServide.loginUser({ username, contrasenia }).subscribe({

        next: response => {
          const token = response.token;
          console.log(token);
          const payload = this.authServide.getPayLoad(token);

          const user = { username: payload.sub }
          const login = {
            user,
            isAuth: true,
            isAdmin: payload.isAdmin
          }
          this.authServide.user = login;
          this.authServide.token = token;

          console.log(payload);
          Swal.fire({
            position: "center",
            icon: "success",
            title: "Hola! " + user.username,
            showConfirmButton: false,
            timer: 1500
          });
          this.router.navigate(['/listasolicitudes']);
        },
        error: error => {
          Swal.fire({
            position: "center",
            icon: "error",
            title: error.error.message,
            showConfirmButton: false,
            timer: 1500
          });
        }
      })
    })
  }

  get admin(): boolean {
    return this.authServide.isAdmin();
  }

  get authenticated(): boolean {
    return this.authServide.authenticated();
  }

  handlerLogouot() {
    this.router.navigate(['/login']);
    this.client.deactivate();
    return this.authServide.logout();
  }
}
