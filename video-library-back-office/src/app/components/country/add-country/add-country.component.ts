import { Component, OnInit } from '@angular/core';
import {CountryService} from "../../../services/country.service";
import {Country} from "../../../models/Countryl";



@Component({
  selector: 'app-add-country',
  templateUrl: './add-country.component.html',
  styleUrls: ['./add-country.component.css']
})
export class AddCountryComponent implements OnInit {

  country: Country = {
    name: '',
    enabled: false
  };

  submitted = false;
  constructor(private countryService: CountryService) { }

  ngOnInit(): void {
    console.log("HERE ***")
  }


  saveTutorial(): void {
    const data = {
      title: this.country.name,
      enabled: this.country.enabled
    };

    this.countryService.create(data)
      .subscribe({
        next: (res) => {
          console.log(res);
          this.submitted = true;
        },
        error: (e) => console.error(e)
      });
  }

  newTutorial(): void {
    this.submitted = false;
    this.country = {
      name: '',
      enabled: false
    };
  }
}
