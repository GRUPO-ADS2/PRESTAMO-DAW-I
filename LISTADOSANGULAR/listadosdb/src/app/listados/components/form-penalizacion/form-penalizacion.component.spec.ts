import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormPenalizacionComponent } from './form-penalizacion.component';

describe('FormPenalizacionComponent', () => {
  let component: FormPenalizacionComponent;
  let fixture: ComponentFixture<FormPenalizacionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FormPenalizacionComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FormPenalizacionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
