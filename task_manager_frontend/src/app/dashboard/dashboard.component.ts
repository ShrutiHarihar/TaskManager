import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {task} from '../models/task';
import {AuthenticationService} from '../services/authentication.service';
import {user} from '../models/user';
import {TaskService} from '../services/task.service';
import {ModalDirective} from 'ng2-bootstrap/modal/modal.component';

@Component({
  templateUrl: 'dashboard.component.html'
})
export class DashboardComponent implements OnInit {

  error: string;
  modalError: string;
  newEntry: string;
  taskLists: task[];
  sortBy = 'status';
  sortOrder = 'asc';
  rowsOnPage = 5;
  taskItemUpdated = new task();
  selectedTaskList = new task();

  ngOnInit(): void {
    let request = (this.isManager()) ? this.taskService.fetchAllTask() : this.taskService.fetchAllTaskEmp(this.authService.getUsername());
    request.subscribe(
      (data) => {
        this.taskLists = data;
      },
      (error) => {
        this.error = 'Failed to fetch To-Do List';
      }
    );
  }

  constructor(private authService: AuthenticationService, private taskService: TaskService, private router: Router) {
  }

  isManager() {
    return this.authService.isManager();
  }

  addRank(rank) {
    switch (rank) {
      case 0:
        return 'Low';
      case 1:
        return 'Medium';
      case 2:
        return 'High';
    }
  }

  updatestatus(taskItem: task) {
    this.taskService.updateTask(taskItem).subscribe(
      (data) => {
        location.reload();
      },
      (error) => {
        this.error = 'Failed to update status';
      });
  }

  fetchTask(taskItem: task, modalName: any) {
    this.taskService.getTask(taskItem.id).subscribe(
      (data) => {
        this.selectedTaskList = data;
        this.modalError = null;
        this.newEntry = '';
        modalName.show();
      },
      (error) => {
        this.error = 'Failed to fetch notes';
      }
    );
  }

  saveEntry(modalName: any, isFeedback: boolean) {
    if (isFeedback) {
      this.selectedTaskList.feedback.push(this.newEntry);
    } else {
      this.selectedTaskList.notes.push(this.newEntry);
    }
    this.taskService.updateTask(this.selectedTaskList).subscribe(
      (data) => {
        // modalName.hide();
        this.selectedTaskList = data;
        this.newEntry = '';
        this.modalError = null;
      },
      (error) => {
        this.modalError = 'Failed to update';
      }
    )
  }
}
