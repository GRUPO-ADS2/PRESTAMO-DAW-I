import { Component, OnInit } from '@angular/core';
import { RouterLink, RouterLinkActive, RouterOutlet } from '@angular/router';
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


@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, RouterLink,
    RouterLinkActive, FormsModule
  ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit {

  title = 'listadosdb';
  private client: Client = new Client();
  constructor(private sharingData: SharingDataService, private authServide: AuthService) { }

  ngOnInit(): void {
    this.handlerLogin();

    this.client = new Client();
    this.client.webSocketFactory = () => {
      return new SockJS("http://localhost:8081/chat-websocket") as unknown as IStompSocket;
    }

    this.client.onConnect = (frame) => {
      console.log('Conectados: ' + this.client.connected + ' : ' + frame);
      this.client.subscribe('/chat/mensaje', e => {
        console.log(e.body);
      });
    }

    this.client.activate();
  }

  handlerLogin() {
    this.sharingData.handlerLoginEventEmitter.subscribe(({username,contrasenia})=>{
    console.log(username,contrasenia)
    
    this.authServide.loginUser({username,contrasenia}).subscribe((data) => {
      console.log(data);
      next: (data: any) => {
        const token = data.token;
        console.log(token);
        const payload = JSON.parse(atob(token.split(".")[1]));
        console.log(payload);
        Swal.fire({
          position: "top-end",
          icon: "success",
          title: "Solicitud creada",
          showConfirmButton: false,
          timer: 1500
        });
      }
      error: (error: any) => {
        console.log(error.error);
          Swal.fire({
            position: "top-end",
            icon: "error",
            title: error.error,
            showConfirmButton: false,
            timer: 1500
          });        
        }
      })
  })
  }

}
