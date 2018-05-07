import {Component, OnInit} from '@angular/core';
import {TaskService} from '../../services/task.service';
import {user} from '../../models/user';
import {AuthenticationService} from '../../services/authentication.service';
import {task} from '../../models/task';
import {ModalDirective} from 'ng2-bootstrap/modal/modal.component';

@Component({
  selector: 'app-view',
  templateUrl: './view.component.html',
  styleUrls: ['./view.component.scss']
})
export class ViewComponent implements OnInit {
  employee: user[];
  error: string;
  username: string;
  todoLists: task[];
  sortBy = 'status';
  sortOrder = 'desc';
  rowsOnPage = 10;
  modalError: string;
  newEntry: string;
  todoItemUpdated = new task();
  selectedTodoList = new task();

  constructor(private todoService: TaskService, private authservice: AuthenticationService) {

  }

  ngOnInit() {
    this.username = this.authservice.getUsername();
    this.todoService.fetchAllEmployee().subscribe(
      (data) => {
        this.employee = data;
      },
      (error) => {
        this.error = 'Failed to fetch the list of all employee.';
      }
    );
  }

  fetchTask(todoItem: task, modalName: any) {
    this.todoService.getTask(todoItem.id).subscribe(
      (data) => {
        this.selectedTodoList = data;
        this.modalError = null;
        this.newEntry = '';
        modalName.show();
      },
      (error) => {
        this.error = 'Failed to fetch notes';
      }
    );
  }

  fetchEmployeeTasks() {
    this.todoService.fetchAllTaskEmp(this.username).subscribe(
      (tasks) => {
        this.todoLists = tasks;

      },
      (error) => {
        this.error = 'Failed to fetch tasks for ' + this.username;
      }
    );
  }

  saveEntry(modalName: any, isFeedback: boolean) {
    if (isFeedback) {
      this.selectedTodoList.feedback.push(this.newEntry);
    } else {
      this.selectedTodoList.notes.push(this.newEntry);
    }
    this.todoService.updateTask(this.selectedTodoList).subscribe(
      (data) => {
        // modalName.hide();
        this.selectedTodoList = data;
        this.newEntry = '';
        this.modalError = null;
      },
      (error) => {
        this.modalError = 'Failed to update';
      }
    )
  }
}
