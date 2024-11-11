import { Component, OnInit } from '@angular/core';
import { ListadoService } from '../../services/listado.service';
import { Prestamo } from '../../models/Prestamo';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-prestamo',
  standalone: true,
  imports: [DatePipe],
  templateUrl: './prestamo.component.html',
  styleUrl: './prestamo.component.css'
})
export class PrestamoComponent implements OnInit {

  prestamos: Prestamo[] = []
  constructor(private service : ListadoService){}
  ngOnInit(): void {
    this.service.listarPrestamos().subscribe(arg => {
      this.prestamos = arg;
    });
  }


}
