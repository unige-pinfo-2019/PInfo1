import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';

import { AppRoutingModule } from './app-routing.module';
import { Routes } from '@angular/router';
import { RouterModule } from '@angular/router';
import { AppComponent } from './app.component';

import { NavBarComponent } from './pages/nav-bar/nav-bar.component';
import { HighBodyComponent } from './pages/home/high-body/high-body.component';
import { LowBodyComponent } from './pages/home/low-body/low-body.component';
import { QuickSearchComponent } from './pages/home/quick-search/quick-search.component';
import { FooterComponent } from './pages/footer/footer.component';
import { CarouselItemComponent } from './pages/home/carousel-item/carousel-item.component';
import { AnnonceBottomComponent } from './pages/home/annonce-bottom/annonce-bottom.component';
import { FiltersComponent } from './pages/catalogue/filters/filters.component';
import { BodyHomeComponent } from './pages/home/body-home/body-home.component';

const appRoutes: Routes = [
    { path: '', component: BodyHomeComponent },
    { path: 'home', component: BodyHomeComponent },
    { path: 'catalogue', component: FiltersComponent }
];

@NgModule({
  declarations: [
    AppComponent,
    NavBarComponent,
    HighBodyComponent,
    LowBodyComponent,
    QuickSearchComponent,
    FooterComponent,
    CarouselItemComponent,
    AnnonceBottomComponent,
    FiltersComponent,
    BodyHomeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    RouterModule.forRoot(appRoutes),
    NgbModule.forRoot()
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
