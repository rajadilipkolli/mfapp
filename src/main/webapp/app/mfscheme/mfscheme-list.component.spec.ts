import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MfschemeListComponent } from './mfscheme-list.component';

describe('MfschemeListComponent', () => {
  let component: MfschemeListComponent;
  let fixture: ComponentFixture<MfschemeListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MfschemeListComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(MfschemeListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
