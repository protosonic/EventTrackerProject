import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { catchError, Observable, throwError } from 'rxjs';
import { Bounty } from '../models/bounty';

@Injectable({
  providedIn: 'root'
})
export class BountyService {
  private url = environment.baseUrl + 'api/bounties'

  constructor(
    private http: HttpClient,
  ) { }

  index(): Observable<Bounty[]> {
      return this.http.get<Bounty[]>(this.url).pipe(
      catchError((err:any) => {
        console.log(err);
        return throwError(
          () => new Error('BountyService.index(): error retrieving bounties: ' + err)
        )
      })
    );
  }

    create(bounty: Bounty): Observable<Bounty> {
    return this.http.post<Bounty>(this.url, bounty).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('BountyService.create(): error creating Bounty: ' + err)
        );
      })
    );
  }
    update(updatedBounty: Bounty, bountyId: number): Observable<Bounty> {
    return this.http.put<Bounty>(this.url + '/' + bountyId, updatedBounty).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('BountyService.update(): error updating Bounty: ' + err)
        );
      })
    );
  }

  destroy(bountyId: number): Observable<void> {
    return this.http.delete<void>(this.url + '/' + bountyId).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('BountyService.delete(): error deleting Bounty : ' + err)
        );
      })
    );
  }
}
