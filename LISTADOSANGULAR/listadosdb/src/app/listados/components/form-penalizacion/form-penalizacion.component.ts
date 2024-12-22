import { Component, Input } from '@angular/core';
import { Prestamo } from '../../models/Prestamo';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { PenalizacionService } from '../../services/penalizacion.service';
import Swal from 'sweetalert2';
import { Router } from '@angular/router';

@Component({
  selector: 'app-form-penalizacion',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './form-penalizacion.component.html',
  styleUrl: './form-penalizacion.component.css',
})
export class FormPenalizacionComponent {
  @Input() prestamo!: Prestamo;
  descripcion!: string;
  penalizar() {
    let penaDTO = {
      prestamoId: this.prestamo.idPrestamo,
      fechaPenalizacion: new Date(),
      descripcion: this.descripcion,
    };
    this.servicePena.registrarPenalizacion(penaDTO).subscribe({
      next: (response) => {
        Swal.fire({
          position: 'center',
          icon: 'success',
          title: "Préstamo penalizado con éxito",
          showConfirmButton: false,
          timer: 1500,
        }).then(() => {
          this.router.navigate(['/listaprestamos']).then(() => {
            window.location.reload();
          });
        }); 
      },
      error: (error) => {
        Swal.fire({
          position: 'center',
          icon: 'error',
          title: 'Ocurrió un error al registrar la penalización',
          showConfirmButton: false,
          timer: 1500,
        });
      }
    });
  }
  constructor(private servicePena: PenalizacionService,
              private router: Router
  ) {}
}
