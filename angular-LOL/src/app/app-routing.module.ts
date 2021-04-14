import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CampeonDetailComponent } from './campeon-detail/campeon-detail.component';
import { CampeonModifyComponent } from './campeon-modify/campeon-modify.component';
import { CampeonesAddComponent } from './campeones-add/campeones-add.component';
import { CampeonesListComponent } from './campeones-list/campeones-list.component';

const routes: Routes = [
  {
    path: 'campeones',
    component: CampeonesListComponent,
  },
  {
    path: 'campeonesadd',
    component: CampeonesAddComponent,
  },
  {
    path: 'campeones/:id',
    component: CampeonDetailComponent,
  },
  {
    path: 'campeonesmodify/:id',
    component: CampeonModifyComponent,
  },
  { path: '', redirectTo: '/campeones', pathMatch: 'full' },
  { path: '**', redirectTo: '/campeones', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
