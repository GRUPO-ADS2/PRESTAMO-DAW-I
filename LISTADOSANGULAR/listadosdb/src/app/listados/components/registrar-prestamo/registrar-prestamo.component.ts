import { Component, OnInit } from '@angular/core';
import { Solicitud } from '../../models/Solicitud';
import { SolicitudService } from '../../services/solicitud.service';
import { Material } from '../../models/Material';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Prestamo } from '../../models/Prestamo';
import { PrestamoService } from '../../services/prestamo.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-registrar-prestamo',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './registrar-prestamo.component.html',
  styleUrl: './registrar-prestamo.component.css'
})
export class RegistrarPrestamoComponent implements OnInit {
  constructor(private SolicitudServ: SolicitudService, private PrestamoServ: PrestamoService) { }
  solicitudes: Solicitud[] = []
  prestamoRegistered: Prestamo = new Prestamo()
  idMaterialSolicitude: string = ''
  idAlumno: String = ''
  solicitudfinded?: Solicitud
  ngOnInit(): void {
    this.SolicitudServ.ListSolicitudes('Generado').subscribe(solicitudes =>
      this.solicitudes = solicitudes
    )
  }
  registrarPrestamo() {
    if (this.idMaterialSolicitude.length >=4 && this.verificarSolicitud()) {
      const PressDTO = {
        solicitudId: this.solicitudfinded?.idSolicitud,
        fechaPrestamo: new Date()
      }
      this.PrestamoServ.RegistrarPrestamo(PressDTO).subscribe(
        {
          next: response => {
            Swal.fire({
              position: "center",
              icon: "success",
              title: "registrar Prestamo",
              showConfirmButton: false,
              timer: 1500
            });
            this.solicitudes = this.solicitudes.filter(soli => soli.idSolicitud != this.solicitudfinded?.idSolicitud);
            this.idMaterialSolicitude = ""            
          },
          error: error => {
            Swal.fire({
              position: "center",
              icon: "error",
              title: "Error al enviar la solicitud",
              showConfirmButton: false,
              timer: 1500
            });
          }
        }
      );

    }
  }

  private verificarSolicitud(): boolean {
    this.solicitudfinded = this.solicitudes.find(soli =>
      soli.alumno.usuarioCodUsuario.toString() === this.idAlumno && soli.material.codMaterial.toString() === this.idMaterialSolicitude)
    if (this.solicitudfinded != null) {
      return true
    }
    return false
  }
}
