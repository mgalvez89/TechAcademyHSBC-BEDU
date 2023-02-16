import { Component, Input } from '@angular/core';
import { Solicitud } from 'src/app/package/interfaces/package-interface';

@Component({
  selector: 'app-delivery-table',
  templateUrl: './delivery-table.component.html',
  styles: [
  ]
})
export class DeliveryTableComponent {
  @Input() requests: Solicitud[] = [];
}
