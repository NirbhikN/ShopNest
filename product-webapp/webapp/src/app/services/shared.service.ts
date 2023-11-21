import {EventEmitter, Injectable, Output} from '@angular/core';
import {BehaviorSubject, Subject} from 'rxjs';

@Injectable({
    providedIn: 'root',
})
export class SharedService {
    // Define a BehaviorSubject to store and broadcast shared data
    data$ = new Subject<any>();

    sendData(data: any) {
        this.data$.next(data);
    }


}
