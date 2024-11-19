import { Component, OnInit } from '@angular/core';
import { ListadoService } from '../../services/listado.service';
import { Solicitud } from '../../models/Solicitud';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { SolicitudService } from '../../services/solicitud.service';
import { Material } from '../../models/Material';
import { InfiniteScrollDirective } from 'ngx-infinite-scroll';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-solicitud',
  standalone: true,
  imports: [CommonModule,FormsModule,InfiniteScrollDirective],
  templateUrl: './solicitud.component.html',
  styleUrl: './solicitud.component.css'
})

export class SolicitudComponent implements OnInit {
  solicitudes: Solicitud[] = []
  materiales: Material[] = []
  materialesFiltrados: Material[]=[]
  materialsSelected: Material[]=[]
  pageable: any = {}
  page: number = 0
  searchMaterial: String = ""
  categoria : String = ""
  constructor(private service: ListadoService , private SolicitudServ : SolicitudService) {}
  ngOnInit(): void {
    this.service.listarSolicitudes().subscribe(arg => {
        this.solicitudes = arg
      });
    this.SolicitudServ.ListMateriales(this.page).subscribe(materiales =>   {
      this.pageable = materiales.content
      this.materiales =   materiales.content as Material[]
      this.materialesFiltrados = materiales.content as Material[]
    });
  }
  scrolledInfinite(){
    if (!this.pageable.last) {      
      this.page+=1
      this.SolicitudServ.ListMateriales(this.page).subscribe(pageable => {
        this.pageable = pageable
        let materiales = pageable.content as Material[]
        materiales.forEach(material => {
          this.materiales = [... this.materiales, {... material}]
        })
        this.filtrarMateriales()
        console.log(this.materiales)
      })
    }
  }
  create(){
    this.materialsSelected.forEach(mat => {
      let soliDTO = {
        username : 'jhon',
        materialCod : mat.codMaterial,
        cantidad: 1
      }
      this.SolicitudServ.create(soliDTO).subscribe() 
    });
    Swal.fire({
      position: "center",
      icon: "success",
      title: "Solitud enviada con éxito",
      showConfirmButton: false,
      timer: 1500
    });
    this.materialsSelected = []
  }

  filtrarMateriales(){
    this.materialesFiltrados = this.materiales.filter( mat => {
      const materialCoincide = mat.nombre.toLowerCase().includes(this.searchMaterial.toLowerCase());
      const CategoriaEquals = this.categoria? mat.tipo?.toLowerCase()===this.categoria.toLowerCase(): true;
      return  materialCoincide && CategoriaEquals;
    })  
  }

  seleccionarMaterial(materialToAdd: Material){
    if (this.materialIsSelected(materialToAdd)) {
      this.materialsSelected = this.materialsSelected.filter(mat => mat.codMaterial!=materialToAdd.codMaterial)
    }else{
      this.materialsSelected = [... this.materialsSelected, {... materialToAdd}];
      console.log(this.materialsSelected)
    }
  }
   materialIsSelected(material:Material) : boolean {    
    return this.materialsSelected.some(mat => mat.codMaterial===material.codMaterial)
   }


    
}
