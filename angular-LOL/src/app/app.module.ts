import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CampeonesListComponent } from './campeones-list/campeones-list.component';
import { CampeonItemComponent } from './campeon-item/campeon-item.component';
import { CampeonesAddComponent } from './campeones-add/campeones-add.component';
import { FormsModule } from '@angular/forms';
import { CampeonDetailComponent } from './campeon-detail/campeon-detail.component';
import { CampeonModifyComponent } from './campeon-modify/campeon-modify.component';
import { CampeonesFiltroPipe } from './pipes/campeones-filtro.pipe';
import { CampeonesFiltrorolPipe } from './pipes/campeones-filtrorol.pipe';

@NgModule({
  declarations: [
    AppComponent,
    CampeonesListComponent,
    CampeonItemComponent,
    CampeonesAddComponent,
    CampeonDetailComponent,
    CampeonModifyComponent,
    CampeonesFiltroPipe,
    CampeonesFiltrorolPipe
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
