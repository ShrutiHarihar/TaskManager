import {Injectable} from '@angular/core';
import {task} from '../models/task';
import {Http, Headers, Response, RequestOptions} from '@angular/http';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import {user} from '../models/user';
import {AuthenticationService} from './authentication.service';
import {forEach} from '@angular/router/src/utils/collection';

@Injectable()
export class TaskService {
  private baseUrl = 'http://localhost:8080';

  constructor(private http: Http, private authService: AuthenticationService) {
  }

  postTask(taskItem: task): Observable<Boolean> {
    let url = this.baseUrl + '/api/create';
    return this.http.post(url, taskItem).map((res: Response) => (res.status === 200));
  }

  updateTask(taskItem: task) {
    let url = this.baseUrl + '/api/tasks/' + taskItem.id;
    return this.http.put(url, taskItem).map((res: Response) => (res.json()));
  }

  getTask(taskItem: string) {
    let url = this.baseUrl + '/api/tasks/' + taskItem;
    return this.http.get(url).map((res: Response) => (res.json()));
  }

  fetchAllUnassigned() {
    let url = this.baseUrl + '/api/tasks';
    return this.http.get(url).map((res: Response) => (res.json()));
  }

  fetchAllTaskEmp(username: string) {
    let url = this.baseUrl + '/api/view/' + username;
    return this.http.get(url).map((res: Response) => (res.json()));
  }

  fetchAllTask() {
    let url = this.baseUrl + '/api/tasks';
    return this.http.get(url).map((res: Response) => (res.json()));
  }

  fetchAllEmployee() {
    let url = this.baseUrl + '/user/employees';
    return this.http.get(url).map((res: Response) => (res.json()));
  }

  deepCopy(origTask: task) {
    let taskItem = new task();
    let keys = Object.keys(origTask);
    for (let key of keys) {
      taskItem[key] = origTask[key];
    }
    return taskItem;
  }
}
