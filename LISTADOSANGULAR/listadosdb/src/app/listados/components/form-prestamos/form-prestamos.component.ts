import { Component,Input } from '@angular/core';
import { Prestamo } from '../../models/Prestamo';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-form-prestamos',
  standalone: true,
  imports: [FormsModule,CommonModule],
  templateUrl: './form-prestamos.component.html',
  styleUrl: './form-prestamos.component.css'
})
export class FormPrestamosComponent {
  @Input() prestamo!: Prestamo 
  
  aumentarDias(fecha: Date, diastoadd: number): string {
    const nuevaFecha = new Date(fecha); // Crear un nuevo objeto Date para evitar mutaciones
    nuevaFecha.setDate(nuevaFecha.getDate() + diastoadd); // Sumar días
    return nuevaFecha.toISOString().split('T')[0]; // Formatear como 'yyyy-MM-dd'
  }
}
