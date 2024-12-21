import { Component, OnInit } from '@angular/core';
import { ListadoService } from '../../services/listado.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { SolicitudService } from '../../services/solicitud.service';
import { Material } from '../../models/Material';
import { InfiniteScrollDirective } from 'ngx-infinite-scroll';
import Swal from 'sweetalert2';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-solicitud',
  standalone: true,
  imports: [FormsModule, InfiniteScrollDirective],
  templateUrl: './solicitud.component.html',
  styleUrl: './solicitud.component.css'
})

export class SolicitudComponent implements OnInit {

  materiales: Material[] = []
  materialsSelected: Material[] = []
  pageable: any = {}
  page: number = 0
  searchMaterial: string = ""
  categoria: string = 'TODOS'
  constructor(private service: ListadoService, private SolicitudServ: SolicitudService, private authservice: AuthService) { }
  ngOnInit(): void {
    this.filtrarMateriales()
  }
  scrolledInfinite() {
    if (!this.pageable.last) {
      this.page += 1
      this.SolicitudServ.ListMateriales(this.page, this.categoria, this.searchMaterial).subscribe(pageable => {
        this.pageable = pageable
        let materiales = pageable.content as Material[]
        materiales.forEach(material => {
          this.materiales = [... this.materiales, { ...material }]
        })
      })
    }
  }
  filtrarMateriales() {
    this.page = 0
    let cat = this.categoria
    this.SolicitudServ.ListMateriales(this.page, cat, this.searchMaterial).subscribe(materiales => {
      console.log(materiales.pageable)
      this.pageable = materiales
      this.materiales = materiales.content as Material[]
    });
  }
  create() {
    this.materialsSelected.forEach(mat => {
      const soliDTO: any = {
        username: this.authservice.user.user.username,
        materialCod: mat.codMaterial,
        cantidad: 1
      }
      this.SolicitudServ.create(soliDTO).subscribe({
        next: response => {
          Swal.fire({
            position: "center",
            icon: "success",
            title: "Solitud enviada con éxito",
            showConfirmButton: false,
            timer: 1500
          });
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
      })
    });
    this.materialsSelected = []
  }


  seleccionarMaterial(materialToAdd: Material) {
    if (this.materialIsSelected(materialToAdd)) {
      this.materialsSelected = this.materialsSelected.filter(mat => mat.codMaterial != materialToAdd.codMaterial)
    } else {
      this.materialsSelected = [... this.materialsSelected, { ...materialToAdd }];
      console.log(this.materialsSelected)
    }
  }
  materialIsSelected(material: Material): boolean {
    return this.materialsSelected.some(mat => mat.codMaterial === material.codMaterial)
  }

}
