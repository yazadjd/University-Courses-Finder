import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class HomeService {
  private url = 'http://localhost:8080/';

  constructor(private http: HttpClient) {
  }
  public getCountries(): Observable<any> {
    let endpoint = "country";
    return this.http.get(`${this.url}/${endpoint}`);
  }
  public getCities(countrySelected: string): Observable<any> {
    let endpoint = "city";
    return this.http.get(`${this.url}/${endpoint}`, {params: {'chosenCountry': countrySelected}});
  }

  public getHobbies(): Observable<any> {
    let endpoint = "hobby";
    return this.http.get(`${this.url}/${endpoint}`);
  }

  public getCourses(countrySelected: string, citySelected: string,
                    selectedHobbiesArray: Array<string>): Observable<any> {
    let endpoint = "submit";
    console.log(selectedHobbiesArray);
    return this.http.get(`${this.url}/${endpoint}`, {params: {'countrySelected': countrySelected,
        'citySelected': citySelected, 'hobbiesSelected': selectedHobbiesArray}});
  }
}

