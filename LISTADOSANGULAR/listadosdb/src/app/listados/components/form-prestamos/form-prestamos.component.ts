import { Component,Input } from '@angular/core';
import { Prestamo } from '../../models/Prestamo';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { PrestamoService } from '../../services/prestamo.service';
import Swal from 'sweetalert2';
import { Router } from '@angular/router';

@Component({
  selector: 'app-form-prestamos',
  standalone: true,
  imports: [FormsModule,CommonModule],
  templateUrl: './form-prestamos.component.html',
  styleUrl: './form-prestamos.component.css'
})
export class FormPrestamosComponent {
  @Input() prestamo!: Prestamo 
  @Input() option!:number;
  aumentarDias(fecha: Date, diastoadd: number): string {
    const nuevaFecha = new Date(fecha); // Crear un nuevo objeto Date para evitar mutaciones
    nuevaFecha.setDate(nuevaFecha.getDate() + diastoadd); // Sumar días
    return nuevaFecha.toISOString().split('T')[0]; // Formatear como 'yyyy-MM-dd'
  }
  constructor(private servicePres: PrestamoService,
              private router : Router) {}
  gestionar(): void {
    switch (this.option) {
      case 1:
        this.servicePres.renovar(this.prestamo.idPrestamo).subscribe({
          next: (response) => {
            Swal.fire({
              position: 'center',
              icon: 'success',
              title: "Préstamo renovado con éxito",
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
              title: 'Ocurrió un error al renovar el préstamo',
              showConfirmButton: false,
              timer: 1500,
            });
          }
        });
        break;
      case 2:
        console.log("devolver");
        this.servicePres.devolver(this.prestamo.idPrestamo).subscribe(
          {
            next: (response) => {
              Swal.fire({
                position: 'center',
                icon: 'success',
                title: "Préstamo devuelto con éxito",
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
                title: 'Ocurrió un error al devolver el préstamo',
                showConfirmButton: false,
                timer: 1500,
              });
            }
          }
        );
        break;
    }
  }
  
}
