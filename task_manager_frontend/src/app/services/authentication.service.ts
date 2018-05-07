import {Injectable} from '@angular/core';
import {user} from '../models/user';
import {Headers, Http, Response} from '@angular/http';
import 'rxjs/add/operator/toPromise';
import {Observable} from 'rxjs/Observable';


@Injectable()
export class AuthenticationService {

  private baseUrl = 'http://localhost:8080';

  constructor(private http: Http) {
  }

  getUser() {
    let userStr = localStorage.getItem('user');
    return userStr ? JSON.parse(userStr) : '';
  }

  isAuthenticated() {
    let userData = this.getUser();
    return (userData) ? true : false;
  }

  getRole() {
    let userData = this.getUser();
    return (userData !== null) ? userData.role : '';
  }

  getUsername() {
    let userData = this.getUser();
    return (userData !== null) ? userData.username : '';
  }

  isManager() {
    return this.getRole() == 'Manager';
  }

  isEmployee() {
    return !this.isManager();
  }

  login(userData: user): Observable<user> {
    return this.http.post(this.baseUrl + '/user/login/', userData)
      .map((response) => response.json());
  }

  createUser(registeruser: user) {
    let url = this.baseUrl + '/user/register';
    return this.http.post(url, registeruser).map((res: Response) => (res.status === 200));
  }


  private handleError(error: any): Promise<any> {
    console.error('Some error occured', error);
    return Promise.reject(error.message || error);
  }
}
