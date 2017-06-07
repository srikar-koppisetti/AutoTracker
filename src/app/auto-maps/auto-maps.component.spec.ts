import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AutoMapsComponent } from './auto-maps.component';

describe('AutoMapsComponent', () => {
  let component: AutoMapsComponent;
  let fixture: ComponentFixture<AutoMapsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AutoMapsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AutoMapsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
