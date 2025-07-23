import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { MatCardModule } from '@angular/material/card';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [MatCardModule],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent implements OnInit{
  constructor(private authService: AuthService){}
   ngOnInit(){
    this.authService.getUserData().subscribe({
      next: (resp: string)=>{
        console.log('user data', resp);
      },
      error: (err)=>{
        console.error('Error getting data', err);
      }

    });
  }

}
