import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from '../listados/services/auth.service';

export const authGuard: CanActivateFn = (route, state) => {
  const router = inject(Router);
  const service = inject(AuthService);
  if (service.user.isAuth) {
    if(isTokenExpired()){
      service.logout();
      router.navigate(['/login']);
      return false;
    }
    if(!service.isAdmin()){
      router.navigate(['/403']);
      return false;
    }
    return true;
  }
  router.navigate(['/login']);
  return false;
};

const isTokenExpired = () => {
  const token = inject(AuthService).token;
   const payload = inject(AuthService).getPayLoad(token);
   const now = new Date().getTime() / 1000;
   const exp = payload.exp;
   return(exp < now);
}
