import { enableProdMode } from '@angular/core';
import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';

import { AppModule } from './app/app.module';
import { environment } from './environments/environment';
import { KeycloakService } from './app/services/keycloak/keycloak.service';

if (environment.production) {
  enableProdMode();
}
platformBrowserDynamic().bootstrapModule(AppModule)
  .catch(err => console.error(err));
/*
KeycloakService.init()
  .then(() => platformBrowserDynamic().bootstrapModule(AppModule))
.catch(e => window.location.reload());
*/
