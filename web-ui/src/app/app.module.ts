import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import {NgbModule} from '@ng-bootstrap/ng-bootstrap';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavBarComponent } from './nav-bar/nav-bar.component';
import { HighBodyComponent } from './high-body/high-body.component';
import { LowBodyComponent } from './low-body/low-body.component';
import { QuickSearchComponent } from './quick-search/quick-search.component';
import { FooterComponent } from './footer/footer.component';
import { CarouselItemComponent } from './carousel-item/carousel-item.component';
import { AnnonceBottomComponent } from './annonce-bottom/annonce-bottom.component';

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
