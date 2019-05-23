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
import { PostService } from './services/post.service';
import { PageProfilComponent } from './pages/profil/page-profil/page-profil.component';
import { PageAchatComponent } from './pages/page-achat/page-achat.component';
import { MessagerieComponent } from './pages/messagerie/messagerie.component';
import { AnnonceComponent } from './pages/annonce/annonce.component';
import { AjouterAnnonceComponent } from './pages/ajouter-annonce/ajouter-annonce.component';
import { InfoPersoComponent } from './pages/profil/info-perso/info-perso.component';
import { VentePersoComponent } from './pages/profil/vente-perso/vente-perso.component';
import { AchatPersoComponent } from './pages/profil/achat-perso/achat-perso.component';
import { AnnoncePersoComponent } from './pages/profil/annonce-perso/annonce-perso.component';
import { ReviewPersoComponent } from './pages/profil/review-perso/review-perso.component';



const appRoutes: Routes = [
    { path: '', component: BodyHomeComponent },
    { path: 'home', component: BodyHomeComponent },
    { path: 'auth', component: AuthComponent },
    { path: 'catalogue', component: BodyCatalogueComponent },
    { path: 'vente', component: PageVenteComponent },
    { path: 'profil', component: PageProfilComponent },
    { path: 'profil/info', component: InfoPersoComponent },
    { path: 'profil/achat', component: AchatPersoComponent },
    { path: 'profil/vente', component: VentePersoComponent },
    { path: 'profil/annonce', component: AnnoncePersoComponent },
    { path: 'profil/review', component: ReviewPersoComponent },
    { path: 'messagerie', component: MessagerieComponent },
    { path: 'annonce', component: AnnonceComponent},
    { path: 'ajouterAnnonce', component: AjouterAnnonceComponent},
    { path: 'catalogue/achat/:id', component: PageAchatComponent },
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
    PageVenteComponent,
    PageProfilComponent,
    PageAchatComponent,
    MessagerieComponent,
    AnnonceComponent,
    AjouterAnnonceComponent,
    InfoPersoComponent,
    VentePersoComponent,
    AchatPersoComponent,
    AnnoncePersoComponent,
    ReviewPersoComponent
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
    CatalogueService,
    PostService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
