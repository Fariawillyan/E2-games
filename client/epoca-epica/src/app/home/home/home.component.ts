import { Component } from '@angular/core';
import { SharedModule } from "../../shared/shared.module";

@Component({
    selector: 'app-home',
    standalone: true,
    templateUrl: './home.component.html',
    styleUrl: './home.component.scss',
    imports: [SharedModule]
})
export class HomeComponent {
    gold: any = 24582;
    wood: any = 15648;
    stone: any = 4451;
    food: any = 1482;
    iron: any = 1452;
openSettings() {
throw new Error('Method not implemented.');
}
openMap() {
throw new Error('Method not implemented.');
}
openStrategies() {
throw new Error('Method not implemented.');
}
openReports() {
throw new Error('Method not implemented.');
}
openResources() {
throw new Error('Method not implemented.');
}
openMissions() {
throw new Error('Method not implemented.');
}

}
