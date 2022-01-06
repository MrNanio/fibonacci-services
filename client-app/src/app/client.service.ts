import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {CalcPair} from "./calc-pair";
import {Observable} from "rxjs";
import {CalcComponent} from "./calc/calc.component";

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json'})
};
@Injectable({
  providedIn: 'root'
})
export class ClientService {

  constructor(private client: HttpClient) {

  }

  sendCalculateRequest(calcPair: CalcPair): Observable<CalcPair> {
    return this.client.post<CalcPair>('http://localhost:8080/api/calc', calcPair, httpOptions);
  }

  getAllPairs(): Observable<CalcPair[]> {
    return this.client.get<CalcPair[]>('http://localhost:8080/api/calc/all-data');
  }

  getAllIndex(): Observable<CalcPair[]> {
    return this.client.get<CalcPair[]>('http://localhost:8080/api/calc/all-index');
  }

}
