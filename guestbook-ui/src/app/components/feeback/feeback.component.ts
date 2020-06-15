import { Component, OnInit } from '@angular/core';
import { FeedbackService } from 'src/app/services/feedback.service';
import { Feedback } from 'src/app/model/feedback';
import { empty } from 'rxjs';
import { User } from 'src/app/model/user';

@Component({
  selector: 'app-feeback',
  templateUrl: './feeback.component.html',
  styleUrls: ['./feeback.component.css']
})
export class FeebackComponent implements OnInit {

  constructor(private feedbackService: FeedbackService) { }

  feedbacks: Feedback[];
  feebackToSave: Feedback;

  ngOnInit() {
    const emptyUser = new User();
    this.feebackToSave = new Feedback();
    this.feebackToSave.user = emptyUser;
    this.loadFeedbacks();
  }

  loadFeedbacks() {
    this.feedbackService.getFeedbacks().subscribe( data => {
      this.feedbacks = data;
    })
  }

  saveFeedback() {
    this.feedbackService.saveFeedback(this.feebackToSave).subscribe( data => {
      console.log(data);
      this.loadFeedbacks();
    }, error => {
      console.error(error);
    });
  }

  addLike(feedback: Feedback) {
    console.log('Liked ...')  
    this.feedbackService.likeFeedback(feedback).subscribe( data => {
      console.log(data);
      this.loadFeedbacks();
    }, error => {
      console.error(error);
    });;
  }

  removeLike(feedback: Feedback) {
      console.log('unLiked ...')
      this.feedbackService.unlikeFeedback(feedback).subscribe( data => {
        console.log(data);
        this.loadFeedbacks();
      }, error => {
        console.error(error);
      });;
  }


}
