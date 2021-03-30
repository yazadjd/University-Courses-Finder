/*

Author: Yazad Jamshed Davur <yazadjd@yahoo.com>

This Home Typescript component works with the HTML component to call services
on demand and listen for responses to populate the User Interface.

ngOnInit(): The initialization function called on opening the web-page. It
requests the home service for an array of countries and hobbies that will
be displayed on the web page.

loadCities(): This method is called as soon as a country is selected. It
requests the home service for an array of cities that will then be
displayed on the web page.

updateHobby(): This method changes the Boolean value for the corresponding
hobby for every time that the hobby is checked or unchecked.

getCourses(): This method is called when the user submits his/her preferences.
It sends a request to the home service along with the selected values of
country, city and hobbies if any and listens for a response in the form of an
array of courses that will then be displayed on the web page in the form of an
HTML Table.

closeAlert(): This method simply closes any alerts when the user hits the cross
sign.

reset(): This method resets the boolean values for certain class variables in
case of a crash or during initialization.

*/

import {Component, OnInit} from '@angular/core';
import {HomeService} from "../service/home.service";
import {FormBuilder, FormGroup} from "@angular/forms";
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
    // Constructor Initializations
    this.countryArray = [];
    this.cityArray = [];
    this.hobbyArray = [];
    this.hobbyInterfaceArray = [];
    this.selectedHobbyArray = [];
    this.coursesArray = [];
  }

  ngOnInit(): void {
    this.reset();
    this.newRecordForm = this.fb.group({countrySelected: ''});
    this.homeService.getCountries().subscribe(data => {
      this.countryArray = data;
    });

    this.homeService.getHobbies().subscribe(data => {
        this.hobbyArray = data;
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
