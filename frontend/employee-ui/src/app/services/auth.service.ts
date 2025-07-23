import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private baseUrl = 'http://localhost:8080/api';

  constructor(private http: HttpClient) { }
  
  login(credentials: { name: string; password: string}): Observable<any>{
    return this.http.post(`${this.baseUrl}/login`, credentials, {responseType: 'text'})
  }

  register(credentials: { name: string,password: string}): Observable<any>{
    return this.http.post(`${this.baseUrl}/register`, credentials, {responseType: 'text'});
  }

  getUserData(): Observable<any>{
    const token = localStorage.getItem('jwtToken');
    const headers = new HttpHeaders({
      'Authorization' : `${token}`
    
    })
    return this.http.get(`${this.baseUrl}/users`,{headers});
  }
  
  storeToken(token: string){
    localStorage.setItem('jwtToken',token);
  }

  getToken(): string | null {
    return localStorage.getItem('jwtToken');
  }

}
