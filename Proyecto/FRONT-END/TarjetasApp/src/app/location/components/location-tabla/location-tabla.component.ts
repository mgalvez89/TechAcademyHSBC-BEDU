import { Component, Input } from '@angular/core';
import { aLocation } from '../../../package/interfaces/package-interface';

@Component({
  selector: 'app-location-tabla',
  templateUrl: './location-tabla.component.html',
  styles: [
  ]
})
export class LocationTablaComponent {
  @Input() locations: aLocation[] = [];
}
