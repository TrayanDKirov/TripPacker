import { ApplicationConfig, provideBrowserGlobalErrorListeners, provideZonelessChangeDetection } from '@angular/core';
import { provideRouter } from '@angular/router';
import { GroupToPackService } from './services/group-to-pack.service';
import { provideHttpClient } from '@angular/common/http';

import { routes } from './app.routes';
import { GroupOverviewList } from './components/group-list/group-list.component';

export const appConfig: ApplicationConfig = {
  providers: [
    provideBrowserGlobalErrorListeners(),
    provideZonelessChangeDetection(),
    provideRouter(routes),
    provideHttpClient(),
    GroupToPackService,
    GroupOverviewList
  ]
};