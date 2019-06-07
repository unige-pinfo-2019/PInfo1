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
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { AuthService } from './services/auth.service';
import { CatalogueService } from './services/catalogue.service';
import { PostService } from './services/post.service';
import { PutService } from './services/put.service';

import { PageProfilComponent } from './pages/profil/page-profil/page-profil.component';
import { ModifItemComponent } from './pages/modif-item/modif-item.component';
import { PageAchatComponent } from './pages/page-achat/page-achat.component';
import { MessagerieComponent } from './pages/messagerie/messagerie.component';
import { AnnonceComponent } from './pages/annonce/annonce.component';
import { AjouterAnnonceComponent } from './pages/ajouter-annonce/ajouter-annonce.component';
import { KeycloakService } from './services/keycloak/keycloak.service';
import { KeycloakInterceptorService } from './services/keycloak/keycloak.interceptor.service';
import { InfoPersoComponent } from './pages/profil/info-perso/info-perso.component';
import { VentePersoComponent } from './pages/profil/vente-perso/vente-perso.component';
import { AchatPersoComponent } from './pages/profil/achat-perso/achat-perso.component';
import { AnnoncePersoComponent } from './pages/profil/annonce-perso/annonce-perso.component';
import { ReviewPersoComponent } from './pages/profil/review-perso/review-perso.component';
import { PageCreerUtilisateurComponent } from './pages/page-creer-utilisateur/page-creer-utilisateur.component';
import { PageDiscussionComponent } from './pages/page-discussion/page-discussion.component';
import { ModifyProfilComponent } from './pages/profil/modify-profil/modify-profil.component';
import { LoadImageComponent } from './pages/load-image/load-image.component';
import { ModifyItemComponent } from './pages/profil/modify-item/modify-item.component';


const appRoutes: Routes = [
    { path: '', component: BodyHomeComponent },
    { path: 'home', component: BodyHomeComponent },
    { path: 'auth', component: AuthComponent },
    { path: 'catalogue', component: BodyCatalogueComponent },
    { path: 'vente', component: PageVenteComponent },
    { path: 'profil', component: PageProfilComponent },
    { path: 'profil/info', component: InfoPersoComponent },
    { path: 'profil/achat', component: AchatPersoComponent },
    { path: 'profil/vente/itemmodif/:id', component: ModifItemComponent },

    { path: 'profil/vente', component: VentePersoComponent },
    { path: 'profil/annonce', component: AnnoncePersoComponent },
    { path: 'profil/review', component: ReviewPersoComponent },
    { path: 'profil/modif' , component: ModifyProfilComponent },
    { path: 'messagerie', component: MessagerieComponent },
    { path: 'annonce', component: AnnonceComponent},
    { path: 'ajouterAnnonce', component: AjouterAnnonceComponent},
    { path: 'creerUtilisateur', component: PageCreerUtilisateurComponent},
    { path: 'discussion/:id', component: PageDiscussionComponent },
    { path: 'profil/modifitem/:id', component: ModifyItemComponent },

    { path: 'img', component: LoadImageComponent },
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
    PageCreerUtilisateurComponent,
    InfoPersoComponent,
    VentePersoComponent,
    AchatPersoComponent,
    AnnoncePersoComponent,
    ReviewPersoComponent,
    PageDiscussionComponent,
    ModifItemComponent,

    ModifyProfilComponent,
    LoadImageComponent,
    ModifyItemComponent
  ],
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    AppRoutingModule,
    FormsModule,
    RouterModule.forRoot(appRoutes),
    NgbModule.forRoot(),
    HttpClientModule
  ],
  providers: [
    AuthService,
    CatalogueService,
    PostService,
    PutService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: KeycloakInterceptorService,
      multi: true,
    },
    KeycloakService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
