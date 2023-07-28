import { Component, OnInit } from '@angular/core';
import { TreeNode } from 'primeng/api';
import { treeValues } from '../services/tree-service';
import { AdminService } from '../services/admin-service';
import { UserStatusEnum } from '../services/user-status-enum';
@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html'
})
export class AdminComponent implements OnInit {
  selectedFile: TreeNode = {};
  adminTree: TreeNode[] = treeValues;
  activeTask = 'waitingUser';

  constructor(
    private adminService: AdminService


  ) { }

  ngOnInit(): void {
  
  }

  treeSelectEvent(tree: any) {
    console.log('tree::', tree);

    this.activeTask = tree != null ? tree.node.data : ''

  }

}
