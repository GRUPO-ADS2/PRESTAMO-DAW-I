import { Component, OnInit } from '@angular/core';
import { ListadoService } from '../../services/listado.service';
import { Prestamo } from '../../models/Prestamo';
import { CommonModule, DatePipe } from '@angular/common';
import { FormPrestamosComponent } from '../form-prestamos/form-prestamos.component';
import { PrestamoService } from '../../services/prestamo.service';
import { FormsModule } from '@angular/forms';
import { FormPenalizacionComponent } from '../form-penalizacion/form-penalizacion.component';

@Component({
  selector: 'app-prestamo',
  standalone: true,
  imports: [DatePipe,CommonModule, FormPrestamosComponent,FormsModule, FormPenalizacionComponent],
  templateUrl: './prestamo.component.html',
  styleUrl: './prestamo.component.css'
})
export class PrestamoComponent implements OnInit {
  prestamoEmit:Prestamo = new Prestamo();
  opcion:number = 1;
  prestamos: Prestamo[] = []
  constructor(private service : ListadoService, private servicePrestamo : PrestamoService){}
  ngOnInit(): void {
    this.servicePrestamo.findbyEstado('En Curso').subscribe(arg => {
      this.prestamos = arg;
      this.servicePrestamo.findbyEstado('Tardío').subscribe(args => {
          this.prestamos = [... this.prestamos , ... args];
      });
    });
  }
  emitir(id:number){
    this.servicePrestamo.GetPrestamoById(id).subscribe((prestamo)=>{
      this.prestamoEmit = prestamo;
      this.opcion=1;
    })
  }
  emitirDevolucion(id:number){
    this.servicePrestamo.GetPrestamoById(id).subscribe((prestamo)=>{
      this.prestamoEmit = prestamo;
      this.opcion=2;
    })
  }
  emitirPenalizacion(id:number){
    this.servicePrestamo.GetPrestamoById(id).subscribe((prestamo)=>{
      this.prestamoEmit = prestamo;
      this.opcion=3;
    })
  }

}
