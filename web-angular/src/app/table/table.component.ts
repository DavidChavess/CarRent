import { Component,Input, OnInit } from '@angular/core';

import { TableColumn } from './table';

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css']
})
export class TableComponent implements OnInit {

    @Input()
    columns: TableColumn[];

    @Input()
    content: any[];

    @Input()
    rowAction: Function = ()=>{};

    constructor() { }

    ngOnInit(): void {
        
    }

   
}
