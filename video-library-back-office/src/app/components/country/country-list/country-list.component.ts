import {Component, OnInit} from '@angular/core';
import {Country} from "../../../models/Countryl";
import {CountryService} from "../../../services/country.service";


@Component({
  selector: 'app-country-list',
  templateUrl: './country-list.component.html',
  styleUrls: ['./country-list.component.css']
})
export class CountryListComponent implements OnInit {

  countries?: Country[];
  currentCountry: Country = {};
  currentIndex = -1;
  data = '';

  constructor(private countryService: CountryService) {
  }

  ngOnInit(): void {
    console.log("here==")
    this.retrieveCountries();
  }

  retrieveCountries(): void {
    this.countryService.getAll()
      .subscribe(
        data => {
          this.countries = data;
          console.log(data);
        },
        error => {
          console.log(error);
        });
  }

  refreshList(): void {
    this.retrieveCountries();
    this.currentCountry = {};
    this.currentIndex = -1;
  }

  setActiveCountry(country: Country, index: number): void {
    this.currentCountry = country;
    this.currentIndex = index;
  }

  removeAllCountries(): void {
    this.countryService.deleteAll()
      .subscribe(
        response => {
          console.log(response);
          this.refreshList();
        },
        error => {
          console.log(error);
        });
  }

  searchByTitle(): void {
    this.currentCountry = {};
    this.currentIndex = -1;

    this.countryService.findByTitle(this.data)
      .subscribe(
        data => {
          this.countries = data;
          console.log(data);
        },
        error => {
          console.log(error);
        });
  }

  delete(id: any) {
    this.countryService.delete(id)
      .subscribe(
        response => {
          console.log(response);
          this.refreshList();
        },
        error => {
          console.log(error);
        });
  }
}
