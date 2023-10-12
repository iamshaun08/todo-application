import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {MatDialog} from "@angular/material/dialog";
import {AddTaskDialogComponent} from "../add-task-dialog/add-task-dialog.component";

@Component({
    selector: 'app-navbar',
    templateUrl: './navbar.component.html',
    styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {

    constructor(private dialog: MatDialog) {
    }

    add() {
        this.dialog.open(AddTaskDialogComponent).afterClosed()
            .subscribe({
                next: () => {
                }
            });
    }
}
