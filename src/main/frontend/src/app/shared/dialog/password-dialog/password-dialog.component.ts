import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {Subject} from 'rxjs';
import {MDBModalRef} from 'angular-bootstrap-md';

@Component({
  selector: 'app-password-dialog',
  templateUrl: './password-dialog.component.html',
  styleUrls: ['./password-dialog.component.scss']
})
export class PasswordDialogComponent implements OnInit {

  action: Subject<any> = new Subject();

  validatingForm: FormGroup;

  errorText = '';

  constructor(public modalRef: MDBModalRef) {
  }

  ngOnInit(): void {
    this.validatingForm = new FormGroup({
      modalFormAvatarPassword: new FormControl('', [Validators.required, Validators.minLength(6)])
    });
  }

  passBack() {
    if (!this.validatingForm.invalid) {
      this.action.next(this.modalFormAvatarPassword.value);
      this.modalRef.hide();
    } else {
      this.errorText = 'Campos Erroneos';
    }
  }

  get modalFormAvatarPassword() {
    return this.validatingForm.get('modalFormAvatarPassword');
  }
}
