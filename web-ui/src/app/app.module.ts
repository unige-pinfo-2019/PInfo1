import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import {NgbModule} from '@ng-bootstrap/ng-bootstrap';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { NavBarComponent } from './pages/nav-bar/nav-bar.component';
import { HighBodyComponent } from './pages/high-body/high-body.component';
import { LowBodyComponent } from './pages/low-body/low-body.component';
import { QuickSearchComponent } from './pages/quick-search/quick-search.component';
import { FooterComponent } from './pages/footer/footer.component';
import { CarouselItemComponent } from './pages/carousel-item/carousel-item.component';
import { AnnonceBottomComponent } from './pages/annonce-bottom/annonce-bottom.component';

@NgModule({
  declarations: [
    AppComponent,
    NavBarComponent,
    HighBodyComponent,
    LowBodyComponent,
    QuickSearchComponent,
    FooterComponent,
    CarouselItemComponent,
    AnnonceBottomComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule.forRoot()
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
