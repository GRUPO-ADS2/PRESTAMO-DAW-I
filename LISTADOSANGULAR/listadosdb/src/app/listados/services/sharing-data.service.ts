import { Injectable } from '@angular/core';
import { EventEmitter } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SharingDataService {
  private _handlerLoginEventEmitter = new EventEmitter();
  constructor() { }

  get handlerLoginEventEmitter() {
    return this._handlerLoginEventEmitter;
  }

}
