import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from '../../environments/environment';
import { Hunter } from '../models/hunter';


@Injectable({
  providedIn: 'root'
})
export class HunterService {
 private url = environment.baseUrl + 'api/hunters'

  constructor(
    private http: HttpClient,
  ) { }

  index(): Observable<Hunter[]> {
      return this.http.get<Hunter[]>(this.url).pipe(
      catchError((err:any) => {
        console.log(err);
        return throwError(
          () => new Error('HunterService.index(): error retrieving hunters: ' + err)
        )
      })
    );
  }

    create(hunter: Hunter): Observable<Hunter> {
    return this.http.post<Hunter>(this.url, hunter).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('HunterService.create(): error creating Hunter: ' + err)
        );
      })
    );
  }
    update(updatedHunter: Hunter, bountyId: number): Observable<Hunter> {
    return this.http.put<Hunter>(this.url + '/' + bountyId, updatedHunter).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('HunterService.update(): error updating Hunter: ' + err)
        );
      })
    );
  }

  destroy(hunterId: number): Observable<void> {
    return this.http.delete<void>(this.url + '/' + hunterId).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('HunterService.delete(): error deleting Hunter : ' + err)
        );
      })
    );
  }
}
