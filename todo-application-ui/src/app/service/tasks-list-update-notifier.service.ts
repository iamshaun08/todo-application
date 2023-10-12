import { Injectable } from '@angular/core';
import {BehaviorSubject, Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class TasksListUpdateNotifierService {
  newTaskList$: Observable<any>;
  private _newTaskListObj: BehaviorSubject<any>
  constructor() {
    this._newTaskListObj = new BehaviorSubject<any>(undefined);
    this.newTaskList$ = this._newTaskListObj.asObservable();
  }

  notify(): void {
    this._newTaskListObj.next(undefined);
  }
}
