import {Component, OnInit} from '@angular/core';
import {TaskService} from "../service/task.service";
import {TasksListUpdateNotifierService} from "../service/tasks-list-update-notifier.service";
import {TaskData} from "../model/task-data";
import {TasksList} from "../model/tasks-list";
import {HttpErrorResponse} from "@angular/common/http";
import {MatDialog} from "@angular/material/dialog";
import {UpdateTaskDialogComponent} from "../update-task-dialog/update-task-dialog.component";
import {DeleteDialogComponent} from "../delete-dialog/delete-dialog.component";
import {SnackbarService} from "../service/snackbar.service";

@Component({
  selector: 'app-view-tasks',
  templateUrl: './view-tasks.component.html',
  styleUrls: ['./view-tasks.component.css']
})
export class ViewTasksComponent implements OnInit {
  constructor(private taskService: TaskService,
              private taskListUpdateNotifier: TasksListUpdateNotifierService,
              private dialog: MatDialog,
              private snackbarService: SnackbarService) {
  }

  public tasks: TaskData[] = [];

  ngOnInit(): void {
    this.taskListUpdateNotifier
      .newTaskList$
      .subscribe({
        next: () => {
          this.getAllTasks()
        }
      });
  }

  public getAllTasks(): void {
    this.taskService.getAllTasks()
      .subscribe({
        next: (response: TasksList) => {
          this.tasks = response.taskResponses
        },
        error: (error: HttpErrorResponse) => {
          alert(error.message)
        }
      });
  }

  displayedColumns: string[] = ['status', 'name', 'creationDate', 'update', 'delete'];

  updateTask(uuid: string, name: string, status: string) {
    this.dialog.open(UpdateTaskDialogComponent, {
      data: {
        uuid: uuid,
        name: name,
        status: status
      }
    }).afterClosed()
      .subscribe({
        next: () => {
          this.getAllTasks();
        }
      })
  }

  deleteTask(uuid: string) {
    let ifDelete = this.dialog.open(DeleteDialogComponent);
    ifDelete.afterClosed()
      .subscribe({
          next: (result) => {
            if (`${result}` === 'true') {
              this.taskService.deleteTaskByUuid(uuid)
                .subscribe({
                  next: (response) => {
                    this.snackbarService.openSnackbar('Task deleted!');
                    this.taskListUpdateNotifier.notify();
                  },
                  error: (error: HttpErrorResponse) => {
                    this.snackbarService.openSnackbar(error.error);
                  }
                });
            }
          },
          error: (error: HttpErrorResponse) => {
            this.snackbarService.openSnackbar(error.error);
          }
        }
      )
  }
}
