import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable, catchError, throwError } from 'rxjs';
import { Bounty } from '../models/bounty';
import { Target } from '../models/target';

@Injectable({
  providedIn: 'root'
})
export class TargetService {
 private url = environment.baseUrl + 'api/targets'

  constructor(
    private http: HttpClient,
  ) { }

  index(): Observable<Target[]> {
      return this.http.get<Target[]>(this.url).pipe(
      catchError((err:any) => {
        console.log(err);
        return throwError(
          () => new Error('TargetService.index(): error retrieving targets: ' + err)
        )
      })
    );
  }

    create(bounty: Target): Observable<Target> {
    return this.http.post<Target>(this.url, bounty).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('TargetService.create(): error creating Target: ' + err)
        );
      })
    );
  }
    update(updatedTarget: Target, targetId: number): Observable<Target> {
    return this.http.put<Target>(this.url + '/' + targetId, updatedTarget).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('TargetService.update(): error updating Target: ' + err)
        );
      })
    );
  }

  destroy(targetId: number): Observable<void> {
    return this.http.delete<void>(this.url + '/' + targetId).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('TargetService.delete(): error deleting Target : ' + err)
        );
      })
    );
  }
}
