import { Component, OnInit } from '@angular/core';
import { RouterLink, RouterLinkActive, RouterOutlet } from '@angular/router';
import { SolicitudComponent } from './listados/components/solicitud/solicitud.component';
import { PrestamoComponent } from './listados/components/prestamo/prestamo.component';
import { PenalizacionComponent } from './listados/components/penalizacion/penalizacion.component';
import { InfiniteScrollDirective } from 'ngx-infinite-scroll';
import { RegistrarPrestamoComponent } from './listados/components/registrar-prestamo/registrar-prestamo.component';
import SockJS from 'sockjs-client';
import { Client , IStompSocket} from '@stomp/stompjs';


@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet,RouterLink,
    RouterLinkActive, SolicitudComponent,
    PrestamoComponent, PenalizacionComponent ,
    RegistrarPrestamoComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit {

  title = 'listadosdb';
  private client : Client = new Client();
constructor() { } 

  ngOnInit(): void {
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

}
