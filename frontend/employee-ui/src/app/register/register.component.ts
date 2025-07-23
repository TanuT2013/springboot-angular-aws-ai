import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder,  Validators, ReactiveFormsModule,FormsModule } from '@angular/forms';
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon'


@Component({
  selector: 'app-register',
  standalone: true,
  imports: [CommonModule,
    FormsModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatCardModule,
  MatIconModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {
  hide = true;
  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) {}

  registerForm = this.fb.group({
    name: ['',[Validators.required]],
    password:['',[Validators.required, Validators.minLength(6)]],
  });

  onSubmit(){
    if(this.registerForm.valid){
       const name = this.registerForm.controls['name'].value ?? "";
       const password = this.registerForm.controls['password'].value??"";
      this.authService.register({name, password}).subscribe({
       next:(res: string) =>{
            console.log(res);
            this.router.navigate(["/login"]);
       },
       error: (error) => {
        console.log(error);
       }
      })
    }
  }
}
