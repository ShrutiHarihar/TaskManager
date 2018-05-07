import {Component} from '@angular/core';
import {Router} from '@angular/router';
import {AuthenticationService} from '../services/authentication.service';

import {user} from '../models/user';

@Component({
  templateUrl: 'register.component.html'
})
export class RegisterComponent {

  error: string;
  registeruser: user = new user();

  constructor(private authenticService: AuthenticationService, private router: Router) {
  }

  ngOnInit() {
    this.error = null;

  }

  submit() {
    this.authenticService.createUser(this.registeruser).subscribe(
      (data) => {
        this.router.navigate(['/login']);
      },
      (error) => {
        this.error = 'Failed to register user. Please try again after sometime';
      }
    )
  }
}
