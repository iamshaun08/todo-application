import {Component, Inject, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {TaskService} from "../service/task.service";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {SnackbarService} from "../service/snackbar.service";
import {HttpErrorResponse} from "@angular/common/http";
import {MatSlideToggleChange} from "@angular/material/slide-toggle";

@Component({
  selector: 'app-update-task-dialog',
  templateUrl: './update-task-dialog.component.html',
  styleUrls: ['./update-task-dialog.component.css']
})
export class UpdateTaskDialogComponent implements OnInit {
  public updateTaskForm: FormGroup;
  public buttonState: boolean = false;

  constructor(private formBuilder: FormBuilder,
              private taskService: TaskService,
              private dialogRef: MatDialogRef<UpdateTaskDialogComponent>,
              private snackBarService: SnackbarService,
              @Inject(MAT_DIALOG_DATA) public data: any) {
    this.updateTaskForm = formBuilder.group({
      name: [data.name, [Validators.required]],
      status: [data.status === 'PENDING', [Validators.required]]
    });
  }

  ngOnInit(): void {
    this.updateTaskForm
      .statusChanges
      .subscribe({
        next: (value) => {
          this.buttonState = value === 'VALID';
        }
      })
  }

  updateTask() {
    let name = this.updateTaskForm.get('name')?.value!!;
    this.taskService.updateTaskByUuid(this.data.uuid, name, this.data.status)
      .subscribe(
        {
          next: (value) => {
            this.snackBarService.openSnackbar("Task Updated!");
            this.dialogRef.close(true);
          },
          error: (error: HttpErrorResponse) => {
            this.snackBarService.openSnackbar(error.error);
            this.dialogRef.close();
          }
        }
      );

  }

  onChange(value: MatSlideToggleChange) {
    if (value.checked)
      this.data.status = 'PENDING';
    else
      this.data.status = 'COMPLETED';
  }
}
