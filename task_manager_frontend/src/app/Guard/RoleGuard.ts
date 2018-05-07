import { Injectable } from '@angular/core';
import { Router, CanActivate, ActivatedRouteSnapshot } from '@angular/router';
import { AuthenticationService as AuthService } from '../services/authentication.service';

@Injectable()
export class RoleGuard implements CanActivate {

  constructor(public auth: AuthService, public router: Router) { }

  canActivate(route: ActivatedRouteSnapshot): boolean {

    const expectedRole: string[] = route.data.expectedRole;

    let currentUser = this.auth.getUser();
    if (!this.auth.isAuthenticated()) {
      this.router.navigate(['/login']);
      return false;
    }
    else if (!expectedRole.includes(currentUser["role"])) {
      this.router.navigate(['']);
      return false;
    }
    return true;
  }
}
