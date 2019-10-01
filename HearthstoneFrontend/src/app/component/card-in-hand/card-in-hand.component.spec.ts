import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CardInHandComponent } from './card-in-hand.component';

describe('CardInHandComponent', () => {
  let component: CardInHandComponent;
  let fixture: ComponentFixture<CardInHandComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CardInHandComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CardInHandComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
