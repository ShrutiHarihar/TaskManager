import {Component, OnInit} from '@angular/core';
import {task} from '../../models/task';
import {AuthenticationService} from '../../services/authentication.service';
import {TaskService} from '../../services/task.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-create',
  templateUrl: './create.component.html',
  styleUrls: ['./create.component.scss']
})
export class CreateComponent implements OnInit {

  taskItem: task = new task();
  error: string;

  constructor(private taskService: TaskService, private authService: AuthenticationService, private router: Router) {
  }

  ngOnInit() {
    this.error = null;

  }

  submit() {
    this.taskItem.createdBy = this.authService.getUsername();
    if (this.authService.isEmployee())
      this.taskItem.assignedTo = this.authService.getUsername();

    this.taskService.postTask(this.taskItem).subscribe(
      (data) => {
        this.router.navigate(['/dashboard']);
      },
      (error) => {
        this.error = 'Failed to create task.';
      });
  }

  reset() {
    this.taskItem = new task();
    this.error = null;
  }
}
