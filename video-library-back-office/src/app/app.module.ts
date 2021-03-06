import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
// import {FormsModule} from "@angular/forms";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import {RouterModule, Routes} from '@angular/router';
import {HttpClientModule} from "@angular/common/http";

import {AppComponent} from './app.component';
import {LoginComponent} from './components/login/login.component';
import {RegisterComponent} from './components/register/register.component';
import {HomeComponent} from './components/home/home.component';

import {LogService} from "./services/log.service";
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {AuthInterceptor} from "./helpers/auth.interceptor";
import {AppHeaderComponent} from './components/app-header/app-header.component';
import {AccountSettingsComponent} from './components/account-settings/account-settings.component';
import {AccountProfileComponent} from './components/account-profile/account-profile.component';
import {SidebarComponent} from './components/sidebar/sidebar.component';
import {UsersComponent} from './components/users/users.component';
import {CountryListComponent} from './components/country/country-list/country-list.component';
import {AddCountryComponent} from './components/country/add-country/add-country.component';
import {CountryDetailsComponent} from './components/country/country-details/country-details.component';
import {LibraryComponent} from './components/library/library.component';
import {FilmListComponent} from './components/films/film-list/film-list.component';
import {FilmDetailsComponent} from './components/films/film-details/film-details.component';
import { UserEditComponent } from './user-edit/user-edit.component';

const routes: Routes = [
  {path: 'home', component: HomeComponent},
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'account-setting', component: AccountSettingsComponent},
  {path: 'profile', component: AccountProfileComponent},
  {path: 'users', component: UsersComponent},
  {path: 'countries', component: CountryListComponent},
  {path: 'countries/edit/:id', component: CountryDetailsComponent},
  {path: 'countries/add', component: AddCountryComponent},
  {path: 'films', component: FilmListComponent},
  {path: 'films/:id', component: FilmDetailsComponent},
  {path: 'library', component: LibraryComponent},
  {path: '', redirectTo: 'home', pathMatch: 'full'}
];

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    HomeComponent,
    AppHeaderComponent,
    AccountSettingsComponent,
    AccountProfileComponent,
    SidebarComponent,
    UsersComponent,
    CountryListComponent,
    AddCountryComponent,
    CountryDetailsComponent,
    LibraryComponent,
    FilmListComponent,
    FilmDetailsComponent,
    UserEditComponent,

  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(routes),
    FormsModule,ReactiveFormsModule,
    HttpClientModule,
    NgbModule
  ],
  providers: [LogService, AuthInterceptor],
  exports: [RouterModule],
  bootstrap: [AppComponent]

})
export class AppModule {
}
