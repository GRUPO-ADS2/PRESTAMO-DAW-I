import { Component, Input } from '@angular/core';
import { Prestamo } from '../../models/Prestamo';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-form-prestamo',
  standalone: true,
  imports: [FormsModule,CommonModule],
  templateUrl: './form-prestamo.component.html',
  styleUrl: './form-prestamo.component.css'
})
export class FormPrestamoComponent {
  @Input() prestamo!: Prestamo 

  aumentarDias(fecha: Date,diastoadd: number): Date{
    const f = fecha
    f?.setDate(diastoadd)
    return f
  }

}
