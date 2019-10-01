import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CardOnBoardComponent } from './card-on-board.component';

describe('CardOnBoardComponent', () => {
  let component: CardOnBoardComponent;
  let fixture: ComponentFixture<CardOnBoardComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CardOnBoardComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CardOnBoardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
