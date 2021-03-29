import {Component, OnInit} from '@angular/core';
import {HomeService} from "../service/home.service";
import {FormBuilder, FormGroup} from "@angular/forms";
import {count} from "rxjs/operators";
import {Course, HobbyInterface} from "./interfaces";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  countryArray: Array<string>;
  newRecordForm: FormGroup;
  cityArray: Array<string>;
  hobbyArray: Array<string>;
  isCityVisible: Boolean;
  hobbyInterfaceArray: Array<HobbyInterface>;
  selectedHobbyArray: Array<string>;
  coursesArray: Array<Course>;
  isTableVisible: Boolean;
  isAlertVisible: Boolean;
  noCountryAlert: Boolean;

  constructor(public homeService: HomeService, private fb: FormBuilder) {
    this.countryArray = [];
    this.cityArray = [];
    this.hobbyArray = [];
    this.isCityVisible = false;
    this.hobbyInterfaceArray = [];
    this.selectedHobbyArray = [];
    this.coursesArray = [];
    this.isTableVisible = false;
    this.isAlertVisible = false;
    this.noCountryAlert = false;
  }

  ngOnInit(): void {
    this.reset();
    this.newRecordForm = this.fb.group({countrySelected: ''});
    this.homeService.getCountries().subscribe(data => {
      this.countryArray = data;
    });

    this.homeService.getHobbies().subscribe(data => {
        this.hobbyArray = data;
        console.log(data)
        for (var val of this.hobbyArray) {
          this.hobbyInterfaceArray.push({hobbyName: val, isChecked: false});
        }
      }
    );
  }

  loadCities(): void {
    this.isCityVisible = true;
    const countrySelected = this.newRecordForm.controls.countrySelected.value;
    if (countrySelected == "All Countries") {
      this.isCityVisible = false;
    }
    this.newRecordForm = this.fb.group({
      countrySelected: countrySelected,
      citySelected: 'All Cities',
    });


    this.homeService.getCities(countrySelected).subscribe(data => {
      this.cityArray = data;
    });
  }

  updateHobby(changedHobby: HobbyInterface): void {
    changedHobby.isChecked = !changedHobby.isChecked;
  }

  getCourses(): void {

    this.coursesArray = [];
    this.selectedHobbyArray = [];
    const countrySelected = this.newRecordForm.controls.countrySelected.value;
    if (countrySelected == '') {
      this.noCountryAlert = true;
    } else {
      const citySelected = this.newRecordForm.controls.citySelected.value;
      for (var val of this.hobbyInterfaceArray) {
        if (val.isChecked) {
          this.selectedHobbyArray.push(val.hobbyName);
        }
      }
      this.homeService.getCourses(countrySelected, citySelected, this.selectedHobbyArray).subscribe(
        data => {
          this.coursesArray = data;
          if (this.coursesArray.length == 0) {
            this.isAlertVisible = true;
          } else {
            this.isTableVisible = true;
          }
        });

    }

  }

  closeAlert(): void {
    this.isAlertVisible = false;
    this.noCountryAlert = false;
  }

  reset(): void {
    this.isAlertVisible = false;
    this.noCountryAlert = false;
    this.isTableVisible = false;
    this.isCityVisible = false;
  }
}
