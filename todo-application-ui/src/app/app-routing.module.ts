import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ViewTasksComponent} from "./view-tasks/view-tasks.component";

const routes: Routes = [
    {
        path: 'tasks',
        component: ViewTasksComponent
    },
    {
        path: '',
        redirectTo: 'tasks',
        pathMatch: 'full'
    }
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
