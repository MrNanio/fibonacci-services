import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {ClientService} from "../client.service";
import {CalcPair} from "../calc-pair";

@Component({
  selector: 'app-calc',
  templateUrl: './calc.component.html',
  styleUrls: ['./calc.component.css']
})
export class CalcComponent implements OnInit {

  constructor(private clientService: ClientService) {
  }

  ngOnInit(): void {
    this.getAllPairs();
  }

  fibonacciForm = new FormGroup({
    number: new FormControl('', Validators.required),
  });

  result?: string = undefined;
  resultTable?: CalcPair[];
  resultIndexTable?: CalcPair[];

  calculateFibonacci() {
    let keyIndex = Number(this.fibonacciForm.get('number')?.value);

    const model: CalcPair = {
      key: keyIndex.toString(),
      value: ""
    }

    if (Number.isInteger(keyIndex) && keyIndex >= 0) {
      this.clientService.sendCalculateRequest(model).subscribe(data => {
        setTimeout(() => {
          this.getAllPairs()
        }, 2000);
        setTimeout(() => {
          this.getAllIndex()
        }, 2000);
      });
    } else {
      this.result = "Złe dane wejściowe";
    }
  }

  getAllPairs() {
    this.clientService.getAllPairs().subscribe(data => {
      this.resultTable = data;
    })
  }

  getAllIndex() {
    this.clientService.getAllIndex().subscribe(data => {
      this.resultIndexTable = data;
    })
  }
}
