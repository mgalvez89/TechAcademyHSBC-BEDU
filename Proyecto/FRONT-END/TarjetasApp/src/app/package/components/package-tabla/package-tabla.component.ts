import { Component, Input } from '@angular/core';
import { Solicitud } from '../../interfaces/package-interface';


@Component({
  selector: 'app-package-tabla',
  templateUrl: './package-tabla.component.html',
  styles: [
  ]
})
export class PackageTablaComponent {
  @Input() requests: Solicitud[] = [];
}
