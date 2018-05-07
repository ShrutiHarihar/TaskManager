import {Component, OnInit} from '@angular/core';
import {AuthenticationService} from '../services/authentication.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-dashboard',
  templateUrl: './full-layout.component.html',
  styleUrls: ['./full-layout.component.css']
})
export class FullLayoutComponent implements OnInit {


  public disabled = false;
  public status: { isopen: boolean } = {isopen: false};

  constructor(private authService: AuthenticationService, private router: Router) {

  }

  public toggled(open: boolean): void {
    console.log('Dropdown is now: ', open);
  }

  public toggleDropdown($event: MouseEvent): void {
    $event.preventDefault();
    $event.stopPropagation();
    this.status.isopen = !this.status.isopen;
  }

  isManager() {
    return this.authService.isManager();
  }

  logout() {
    localStorage.removeItem('user');
    this.router.navigate(['/login']);

  }

  ngOnInit(): void {
  }
}
