import { Component, Input, OnInit } from '@angular/core';
import { DomSanitizer, SafeHtml } from '@angular/platform-browser';

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

    linhas: SafeHtml;

    constructor( private s: DomSanitizer ) { }

    ngOnInit(): void {

        let html = '';

        this.content.forEach( (x, i) => {
            html += `<tr> <th>${i+1}</th> `;

            this.columns.forEach( y => {
                html += `<td> ${x[ y.atribute ]}</td>`;
            })

            html += '</tr>';
        })

        this.linhas = this.s.bypassSecurityTrustHtml( html );
    }

}
