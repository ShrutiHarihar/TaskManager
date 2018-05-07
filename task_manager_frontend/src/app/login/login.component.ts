import {Component, OnInit} from '@angular/core';
import {user} from '../models/user';
import {Router, ActivatedRoute} from '@angular/router';
import {AuthenticationService} from '../services/authentication.service';

@Component({
  selector: 'app-login',
  templateUrl: 'login.component.html'
})
export class LoginComponent implements OnInit {

  ngOnInit(): void {
    if (this.authService.isAuthenticated()) {
      this.router.navigate(['/dashboard']);
    }
    this.userData = new user();
  }

  error: string;
  userData: user;

  constructor(private router: Router, private activatedRoute: ActivatedRoute, private authService: AuthenticationService) {
  }

  login() {
    this.authService.login(this.userData).subscribe(
      (data) => {
        let userData = data as user;
        if (userData.username) {
          localStorage.setItem('user', JSON.stringify(userData));
          let returnUrl = this.activatedRoute.snapshot.queryParams['returnUrl'];
          this.router.navigate([returnUrl ? returnUrl : '']);
        } else {
          this.error = 'Failed to Login.';
        }
      },
      (error) => {
        this.error = 'Failed to Login.';
      }
    );

  }
}
