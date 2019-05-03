import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

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
import { AuthComponent } from './pages/auth/auth.component';
import { FourOhFourComponent } from './pages/four-oh-four/four-oh-four.component';
import { BodyCatalogueComponent } from './pages/catalogue/body-catalogue/body-catalogue.component';
import { ListItemsComponent } from './pages/catalogue/list-items/list-items.component';
import { SeekItemButtonComponent } from './pages/catalogue/seek-item-button/seek-item-button.component';
import { PageVenteComponent } from './pages/page-vente/page-vente.component';

import { AuthService } from './services/auth.service';
import { CatalogueService } from './services/catalogue.service';



const appRoutes: Routes = [
    { path: '', component: BodyHomeComponent },
    { path: 'home', component: BodyHomeComponent },
    { path: 'auth', component: AuthComponent },
    { path: 'catalogue', component: BodyCatalogueComponent },
    { path: 'vente', component: PageVenteComponent },
    { path: 'not-found', component: FourOhFourComponent },
    { path: '**', redirectTo: 'not-found' }
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
    BodyHomeComponent,
    AuthComponent,
    FourOhFourComponent,
    BodyCatalogueComponent,
    ListItemsComponent,
    SeekItemButtonComponent,
    PageVenteComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    RouterModule.forRoot(appRoutes),
    NgbModule.forRoot(),
    HttpClientModule
  ],
  providers: [
    AuthService,
    CatalogueService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
