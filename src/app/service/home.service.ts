/*

Author: Yazad Jamshed Davur <yazadjd@yahoo.com>

This Home Typescript Service provides services for the Home Typescript component.
The methods defined, pack the parameters into HTTP GET Requests and trigger
end points as defined in the Java Controller. It then listens for the Response
Entity from the Controller that is then sent back to the typescript component.

*/

import {Injectable} from '@angular/core';
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
    return this.http.get(`${this.url}/${endpoint}`, {
      params: {
        'countrySelected': countrySelected,
        'citySelected': citySelected, 'hobbiesSelected': selectedHobbiesArray
      }
    });
  }
}

