import { Routes } from '@angular/router';
import { SolicitudComponent } from './listados/components/solicitud/solicitud.component';
import { PenalizacionComponent } from './listados/components/penalizacion/penalizacion.component';
import { PrestamoComponent } from './listados/components/prestamo/prestamo.component';
import { RegistrarPrestamoComponent } from './listados/components/registrar-prestamo/registrar-prestamo.component';
import { AuthComponent } from './listados/components/auth/auth.component';
import { authGuard } from './guards/auth.guard';
import { ForbiddenComponent } from './listados/components/forbidden/forbidden.component';

export const routes: Routes = [
    { path: '', redirectTo: '/listasolicitudes', pathMatch: 'full' },  // Ruta para la página principal
    { path: 'listasolicitudes', component: SolicitudComponent}, 
    { path: '403', component: ForbiddenComponent}, 
    { path: 'listaprestamos', component: PrestamoComponent , canActivate: [authGuard]},
    { path: 'registrarPrestamo', component: RegistrarPrestamoComponent , canActivate: [authGuard]},
    { path: 'login', component: AuthComponent} 
];


