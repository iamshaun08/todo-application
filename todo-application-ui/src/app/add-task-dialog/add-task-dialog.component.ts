import {Component} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {TaskService} from "../service/task.service";
import {MatDialogRef} from "@angular/material/dialog";
import {TasksListUpdateNotifierService} from "../service/tasks-list-update-notifier.service";
import {SnackbarService} from "../service/snackbar.service";
import {TaskData} from "../model/task-data";
import {HttpErrorResponse} from "@angular/common/http";

@Component({
    selector: 'app-add-task-dialog',
    templateUrl: './add-task-dialog.component.html',
    styleUrls: ['./add-task-dialog.component.css']
})
export class AddTaskDialogComponent {
    public addTaskForm: FormGroup;

    constructor(private formBuilder: FormBuilder,
                private taskService: TaskService,
                private dialogRef: MatDialogRef<AddTaskDialogComponent>,
                private taskListUpdateNotifier: TasksListUpdateNotifierService,
                private snackbarService: SnackbarService) {
        this.addTaskForm = formBuilder.group({
            name: ['', [Validators.required]]
        })
    }

    addTask() {
        let name = this.addTaskForm.get('name')?.value;
        this.taskService.addTask(name)
            .subscribe({
                next: (value: TaskData) => {
                    this.snackbarService.openSnackbar("Task added!");
                    this.taskListUpdateNotifier.notify();
                },
                error: (error: HttpErrorResponse) => {
                    this.snackbarService.openSnackbar(error.error);
                    console.log(error);
                }
            })
        this.dialogRef.close();
    }
}
