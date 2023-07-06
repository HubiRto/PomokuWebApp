import { Component } from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import ValidateForm from "../../helpers/validateform";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  type: string = "password";
  isText: boolean = false;
  eyeIcon: string = "fa-eye-slash";

  registerForm!: FormGroup;
  constructor(private fb: FormBuilder) {
    this.registerForm = this.fb.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      email: ['', Validators.required],
      password: ['', Validators.required],
      repeatPassword: ['', Validators.required]
    });
  }
  hideShowPass() {
    this.isText = !this.isText;
    this.isText ? this.eyeIcon = "fa-eye" : this.eyeIcon = "fa-eye-slash";
    this.isText ? this.type = "text" : this.type = "password";
  }

  onSubmitRegister() {
    if(this.registerForm.valid){
      console.log(this.registerForm.value);
    }else {
      ValidateForm.validateAllFormFields(this.registerForm);
    }
  }
}
