import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'riskmanager';
  opened: boolean = true;

  onClickShowSidenav(): void {
    this.opened = !this.opened;
  }
}
