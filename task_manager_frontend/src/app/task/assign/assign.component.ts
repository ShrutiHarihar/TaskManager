import {Component, OnInit} from '@angular/core';
import {TaskService} from '../../services/task.service';
import {task} from '../../models/task';
import {user} from '../../models/user';
import {Router} from '@angular/router';

@Component({
  selector: 'app-assign',
  templateUrl: './assign.component.html',
  styleUrls: ['./assign.component.scss']
})
export class AssignComponent implements OnInit {

  error: string[];
  taskItem: task;
  taskList: task[];
  employee: user[];

  constructor(private taskService: TaskService, private router: Router) {
  }

  ngOnInit() {
    this.taskItem = new task();
    this.error = [];
    this.taskService.fetchAllUnassigned().subscribe(
      (data) => {
        this.taskList = data;
      },
      (error) => {
        this.error.push('Failed to fetch the list of tasks.');
      }
    );
    this.taskService.fetchAllEmployee().subscribe(
      (data) => {
        this.employee = data;
      },
      (error) => {
        this.error.push('Failed to fetch the list of all employee.');
      }
    );
  }

  submit() {
    this.taskService.postTask(this.taskItem).subscribe(
      (data) => {
        if (data)
          this.router.navigate(['/dashboard']);
        else
          this.error.push('Failed to assign task.');
      },
      (error) => {
        this.error.push('Failed to assign task.');
      });
  }

  selectTask(id: string) {
    for (let i = 0; i < this.taskList.length; i++) {
      if (this.taskList[i].id == id) {
        this.taskItem = this.taskList[i];
        return;
      }
    }
  }
}
