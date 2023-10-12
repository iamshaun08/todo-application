import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {TasksList} from "../model/tasks-list";
import {TaskData} from "../model/task-data";

@Injectable({
  providedIn: 'root'
})
export class TaskService {

    private apiServerUrl = 'http://localhost:9091/api/v1/task'
    constructor(private http: HttpClient) {}

    public addTask(name: string): Observable<TaskData> {
      return this.http.post<TaskData>(`${this.apiServerUrl}`,
          {
            name: name
          })
    }

    public getAllTasks(): Observable<TasksList> {
        return this.http.get<TasksList>(`${this.apiServerUrl}`)
    }

    public getTaskByUuid(uuid: string): Observable<TaskData> {
      return this.http.get<TaskData>(`${this.apiServerUrl}/${uuid}`)
    }

    public getTaskByName(name: string): Observable<TaskData> {
        return this.http.get<TaskData>(`${this.apiServerUrl}/name/${name}`)
    }

    public updateTaskByUuid(uuid: string, name: string, status: string): Observable<TaskData> {
      return this.http.post<TaskData>(`${this.apiServerUrl}/update`,
          {
              uuid: uuid,
              name: name,
              status: status
          })
    }

    public deleteTaskByUuid(uuid: string): Observable<void> {
      return this.http.delete<void>(`${this.apiServerUrl}/${uuid}`)
    }

    public deleteTaskByName(name: string): Observable<void> {
        return this.http.delete<void>(`${this.apiServerUrl}/name/${name}`)
    }
}
