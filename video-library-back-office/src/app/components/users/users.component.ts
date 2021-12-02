import {Component, OnInit} from '@angular/core';
import {UserServiceService} from "../../services/user-service.service";
import {UserModel} from "../../models/user-model";
import {Router} from '@angular/router';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {UserEditComponent} from "../../user-edit/user-edit.component";

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {


  usersList: Array<UserModel> = [];

  constructor(private usersService: UserServiceService,
              private router: Router,
              private modalService: NgbModal) {
  }

  ngOnInit(): void {
    this.setUsersList();
  }


  editItem(userModel: UserModel) {
    // this.router.navigateByUrl(`EditUser/${userModel.id}`);

    const ref = this.modalService.open(UserEditComponent, {centered: true});
    ref.componentInstance.selectedUser = userModel;

    ref.result.then((yes) => {
        console.log("Yes Click");

        this.setUsersList();
      },
      (cancel) => {
        console.log("Cancel Click");

      })
  }

  deleteItem(userModel: UserModel) {
    this.usersService.delete(userModel.id)
      .subscribe(
        response => {
          console.log(response);
          this.setUsersList();
        },
        error => {
          console.log(error);
        });
  }

  private setUsersList() {
    this.usersService.getUsers().subscribe(x => {
      this.usersList = x;
    })
  }
}
