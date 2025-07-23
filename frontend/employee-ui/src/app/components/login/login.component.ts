import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatCardModule,
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
})
export class LoginComponent {
  loginForm: FormGroup;
  constructor(private fb: FormBuilder, private authService: AuthService, private router: Router) {
    this.loginForm = this.fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required],
    });
  }

  onSubmit() {
    if (this.loginForm.valid) {
      const loginPayload = {
        name: this.loginForm.value.username,
        password: this.loginForm.value.password
      }
      this.authService.login(loginPayload).subscribe({
        next: (token: string) => {
          console.log('Token recieved', token);
          this.authService.storeToken(token);
          this.router.navigate(["/users"]);
        },
        error: (error) => {
          console.error('Login Failed', error);
        },
      });
    } else {
      console.log('inside conole');
      this.loginForm.markAllAsTouched();
    }
  }

  navigateToRegister(){
    this.router.navigate(["/register"]);
  }
}
