import { NgModule } from '@angular/core';
import { GroupToPackModule } from './components/group-list/group-to-pack.module';
import { AppComponent } from './app.component';
import { GroupToPackService } from './services/group-to-pack.service';
import { ItemToPackService } from './services/item-service/item.service';

@NgModule({
  imports: [
    GroupToPackModule,
    AppComponent,
  ],
  providers: [GroupToPackService, ItemToPackService],
})
export class AppModule {}
